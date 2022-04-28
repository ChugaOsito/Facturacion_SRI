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
package io.rubrica.certificate.ec.uanataca;

import static io.rubrica.certificate.ec.uanataca.CertificadoUanataca.OID_CERTIFICADO_MIEMBRO_EMPRESA;
import static io.rubrica.certificate.ec.uanataca.CertificadoUanataca.OID_CERTIFICADO_PERSONA_JURIDICA;
import static io.rubrica.certificate.ec.uanataca.CertificadoUanataca.OID_CERTIFICADO_PERSONA_NATURAL;
import static io.rubrica.certificate.ec.uanataca.CertificadoUanataca.OID_CERTIFICADO_REPRESENTANTE_EMPRESA;
import static io.rubrica.utils.BouncyCastleUtils.certificateHasPolicy;

import java.security.cert.X509Certificate;

/**
 * Permite construir certificados tipo CertificadoUanataca a partir de
 * certificados X509Certificate.
 *
 * @author Edison Lomas Almeida
 */
public class CertificadoUanatacaDataFactory {

    public static boolean esCertificadoUanataca(X509Certificate certificado) {
        return (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL)
                || certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)
                || certificateHasPolicy(certificado, OID_CERTIFICADO_MIEMBRO_EMPRESA)
                || certificateHasPolicy(certificado, OID_CERTIFICADO_REPRESENTANTE_EMPRESA));
    }

    public static CertificadoUanataca construir(X509Certificate certificado) {
        if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL)) {
            return new CertificadoPersonaNaturalUanataca(certificado);
        } else if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)) {
            return new CertificadoPersonaJuridicaPrivadaUanataca(certificado);
        } else if (certificateHasPolicy(certificado, OID_CERTIFICADO_MIEMBRO_EMPRESA)) {
            return new CertificadoMiembroEmpresaUanataca(certificado);
        } else if (certificateHasPolicy(certificado, OID_CERTIFICADO_REPRESENTANTE_EMPRESA)) {
            return new CertificadoRepresentanteLegalUanataca(certificado);
        } else {
            throw new RuntimeException("Certificado del Unataca S.A. de tipo desconocido!");
        }
    }

}
