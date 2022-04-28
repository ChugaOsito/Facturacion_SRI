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
package io.rubrica.certificate.ec.bce;

import static io.rubrica.certificate.ec.bce.CertificadoBancoCentral.OID_SELLADO_TIEMPO;
import static io.rubrica.certificate.ec.bce.CertificadoBancoCentral.OID_CERTIFICADO_FUNCIONARIO_PUBLICO;
import static io.rubrica.certificate.ec.bce.CertificadoBancoCentral.OID_CERTIFICADO_PERSONA_JURIDICA;
import static io.rubrica.certificate.ec.bce.CertificadoBancoCentral.OID_CERTIFICADO_PERSONA_NATURAL;
import static io.rubrica.utils.BouncyCastleUtils.certificateHasPolicy;

import java.security.cert.X509Certificate;

/**
 * Permite construir certificados tipo CertificadoBancoCentral a partir de
 * certificados X509Certificate.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class CertificadoBancoCentralFactory {

    public static boolean esCertificadoDelBancoCentral(X509Certificate certificado) {
        return (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL)
                || certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)
                || certificateHasPolicy(certificado, OID_CERTIFICADO_FUNCIONARIO_PUBLICO)
                || certificateHasPolicy(certificado, OID_SELLADO_TIEMPO));
    }

    public static CertificadoBancoCentral construir(X509Certificate certificado) {
        if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL)) {
            return new CertificadoPersonaNaturalBancoCentral(certificado);
        } else if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)) {
            return new CertificadoPersonaJuridicaBancoCentral(certificado);
        } else if (certificateHasPolicy(certificado, OID_CERTIFICADO_FUNCIONARIO_PUBLICO)) {
            return new CertificadoFuncionarioPublicoBancoCentral(certificado);
        } else if (certificateHasPolicy(certificado, OID_SELLADO_TIEMPO)) {
            return new CertificadoPersonaNaturalBancoCentral(certificado);
        } else {
            throw new RuntimeException("Certificado del Banco Central del Ecuador de tipo desconocido!");
        }
    }
}
