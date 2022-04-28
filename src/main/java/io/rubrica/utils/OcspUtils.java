/*
 * Copyright (C) 2020 
 * Authors: Ricardo Arguello, Misael Fernández
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.*
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.rubrica.utils;

import io.rubrica.certificate.CertEcUtils;
import io.rubrica.certificate.ec.anfac.AnfAc18332CaCert20162036;
import io.rubrica.certificate.ec.anfac.AnfAc37442CaCert20192039;
import io.rubrica.certificate.ec.bce.BceCaCert;
import io.rubrica.certificate.ec.cj.ConsejoJudicaturaCaCert;
import io.rubrica.certificate.ec.securitydata.SecurityDataCaCert;
import io.rubrica.exceptions.EntidadCertificadoraNoValidaException;
import java.net.SocketTimeoutException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorException.Reason;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import io.rubrica.exceptions.RubricaException;

/**
 * Utilidades para OCSP
 * 
 * @author jdc
 */
public class OcspUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static boolean isValidCertificate(X509Certificate certificate) throws RubricaException, EntidadCertificadoraNoValidaException {
        List<X509Certificate> certs = new ArrayList<X509Certificate>();
        certs.add(certificate);
        certs.add(CertEcUtils.getRootCertificate(certificate));

        // init certification path
        CertificateFactory cf;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
        CertPath cp;
        try {
            cp = cf.generateCertPath(certs);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }

        // load the root CA certificates
        X509Certificate rootCACert1 = new SecurityDataCaCert();
        X509Certificate rootCACert2 = new BceCaCert();
        X509Certificate rootCACert3 = new ConsejoJudicaturaCaCert();
        X509Certificate rootCACert4 = new AnfAc18332CaCert20162036();
        X509Certificate rootCACert5 = new AnfAc37442CaCert20192039();

        // init root trusted certs
        TrustAnchor ta1 = new TrustAnchor(rootCACert1, null);
        TrustAnchor ta2 = new TrustAnchor(rootCACert2, null);
        TrustAnchor ta3 = new TrustAnchor(rootCACert3, null);
        TrustAnchor ta4 = new TrustAnchor(rootCACert4, null);
        TrustAnchor ta5 = new TrustAnchor(rootCACert5, null);

        Set<TrustAnchor> trustedCertsSet = new HashSet<TrustAnchor>();
        trustedCertsSet.add(ta1);
        trustedCertsSet.add(ta2);
        trustedCertsSet.add(ta3);
        trustedCertsSet.add(ta4);
        trustedCertsSet.add(ta5);

        // init PKIX parameters
        PKIXParameters params;
        try {
            params = new PKIXParameters(trustedCertsSet);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        params.setRevocationEnabled(false);

        // enable OCSP
        // Security.setProperty("ocsp.enable", "true");
        // Activate CRLDP
        // System.setProperty("com.sun.security.enableCRLDP", "true");
        // perform validation
        CertPathValidator validator;
        try {
            validator = CertPathValidator.getInstance("PKIX");
        } catch (NoSuchAlgorithmException e) {
            throw new RubricaException(e);
        }

        try {
            CertPathValidatorResult result = validator.validate(cp, params);
            return true;
        } catch (InvalidAlgorithmParameterException e) {
            throw new RubricaException(e);
        } catch (CertPathValidatorException e) {
            Reason reason = e.getReason();
            int index = e.getIndex();
            System.out.println("reason=" + reason + "; index=" + index);

            Throwable t = e.getCause();

            if (t != null) {
                System.out.println("Cause=" + t.getClass());

                if (t instanceof SocketTimeoutException) {
                    System.out.println("Timeout al ir al OCSP server!");
                    return false;
                }
            }

            return false;
        }
    }
}
