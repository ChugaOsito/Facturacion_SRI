/* 
 * AppsHandler © 2021
 * development@appshandler.com
 * http://www.appshandler.com
 */
package io.rubrica.certificate.ec.eclipsoft;

import static io.rubrica.certificate.ec.eclipsoft.CertificadoEclipsoft.OID_CERTIFICADO_MIEMBRO_EMPRESA;
import static io.rubrica.certificate.ec.eclipsoft.CertificadoEclipsoft.OID_CERTIFICADO_PERSONA_JURIDICA;
import static io.rubrica.certificate.ec.eclipsoft.CertificadoEclipsoft.OID_CERTIFICADO_PERSONA_NATURAL;
import static io.rubrica.certificate.ec.eclipsoft.CertificadoEclipsoft.OID_CERTIFICADO_REPRESENTANTE_EMPRESA;
import static io.rubrica.utils.BouncyCastleUtils.certificateHasPolicy;

import java.security.cert.X509Certificate;

/**
 * Permite construir certificados tipo CertificadoEclipsoft a partir de certificados X509Certificate.
 *
 * @author Edison Lomas Almeida
 */
public class CertificadoEclipsoftDataFactory {

	public static boolean esCertificadoEclipsoft(X509Certificate certificado) {
		return (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL) || certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)
				|| certificateHasPolicy(certificado, OID_CERTIFICADO_MIEMBRO_EMPRESA) || certificateHasPolicy(certificado, OID_CERTIFICADO_REPRESENTANTE_EMPRESA));
	}

	public static CertificadoEclipsoft construir(X509Certificate certificado) {
		if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_NATURAL)) {
			return new CertificadoPersonalNaturalEclipsoft(certificado);
		} else if (certificateHasPolicy(certificado, OID_CERTIFICADO_MIEMBRO_EMPRESA)) {
			return new CertificadoMiembroEmpresaEclipsoft(certificado);
		} else if (certificateHasPolicy(certificado, OID_CERTIFICADO_REPRESENTANTE_EMPRESA)) {
			return new CertificadoRepresentanteLegalEclipsoft(certificado);
		} else if (certificateHasPolicy(certificado, OID_CERTIFICADO_PERSONA_JURIDICA)) {
			return new CertificadoPersonaJuridicaPrivadaEclipsoft(certificado);
		} else {
			throw new RuntimeException("Certificado del EclipSoft de tipo desconocido!");
		}
	}

}
