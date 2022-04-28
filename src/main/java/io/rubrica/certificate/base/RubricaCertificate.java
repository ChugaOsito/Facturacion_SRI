/*
 * Copyright (C) 2021 
 * Authors: Edison Lomas Almeida
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
package io.rubrica.certificate.base;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/**
 * Clase base para un certificado manejado por Rubrica
 * <code>X509Certificate</code>.
 *
 * @author Edison Lomas Almeida <elomas@appshandler.com>
 */
public abstract class RubricaCertificate extends X509Certificate {

    private X509Certificate x509Certificate;

    public RubricaCertificate(InputStream inputStream) {
        super();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            this.x509Certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RubricaCertificate(StringBuilder stringBuilder) {
        super();
        try {
            InputStream is = new ByteArrayInputStream(stringBuilder.toString().getBytes("UTF-8"));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.x509Certificate = (X509Certificate) cf.generateCertificate(is);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean hasUnsupportedCriticalExtension() {
        return x509Certificate.hasUnsupportedCriticalExtension();
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        return x509Certificate.getCriticalExtensionOIDs();
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        return x509Certificate.getNonCriticalExtensionOIDs();
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return x509Certificate.getExtensionValue(oid);
    }

    @Override
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        x509Certificate.checkValidity();
    }

    @Override
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        x509Certificate.checkValidity(date);
    }

    @Override
    public int getVersion() {
        return x509Certificate.getVersion();
    }

    @Override
    public BigInteger getSerialNumber() {
        return x509Certificate.getSerialNumber();
    }

    @Override
    public Principal getIssuerDN() {
        return x509Certificate.getIssuerDN();
    }

    @Override
    public Principal getSubjectDN() {
        return x509Certificate.getSubjectDN();
    }

    @Override
    public Date getNotBefore() {
        return x509Certificate.getNotBefore();
    }

    @Override
    public Date getNotAfter() {
        return x509Certificate.getNotAfter();
    }

    @Override
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return x509Certificate.getTBSCertificate();
    }

    @Override
    public byte[] getSignature() {
        return x509Certificate.getSignature();
    }

    @Override
    public String getSigAlgName() {
        return x509Certificate.getSigAlgName();
    }

    @Override
    public String getSigAlgOID() {
        return x509Certificate.getSigAlgOID();
    }

    @Override
    public byte[] getSigAlgParams() {
        return x509Certificate.getSigAlgParams();
    }

    @Override
    public boolean[] getIssuerUniqueID() {
        return x509Certificate.getIssuerUniqueID();
    }

    @Override
    public boolean[] getSubjectUniqueID() {
        return x509Certificate.getSubjectUniqueID();
    }

    @Override
    public boolean[] getKeyUsage() {
        return getKeyUsage();
    }

    @Override
    public int getBasicConstraints() {
        return x509Certificate.getBasicConstraints();
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return x509Certificate.getEncoded();
    }

    @Override
    public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        x509Certificate.verify(key);
    }

    @Override
    public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        x509Certificate.verify(key, sigProvider);
    }

    @Override
    public String toString() {
        return x509Certificate.toString();
    }

    @Override
    public PublicKey getPublicKey() {
        return x509Certificate.getPublicKey();
    }
}
