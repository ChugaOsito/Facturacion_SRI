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
package io.rubrica.certificate.ec.digercic;

import java.io.IOException;
import java.security.cert.X509Certificate;

import io.rubrica.certificate.CertUtils;

/**
 * Certificado emitido por el Banco Central del Ecuador.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public abstract class CertificadoDigercic {

    // OIDs de tipo de certificado:
    public static final String OID_CERTIFICADO_PERSONA_NATURAL = "1.3.6.1.4.1.55519.1.1.1.5.3.2";
    public static final String OID_SELLADO_TIEMPO = "1.3.6.1.4.1.55519.1.1.1.5.3.4";

    // OIDs de Campos del Certificado:
    public static final String OID_CEDULA = "1.3.6.1.4.1.55519.1.1.1.5.2.1";
    public static final String OID_PASAPORTE = "1.3.6.1.4.1.55519.1.1.1.5.2.2";
    public static final String OID_APELLIDO_2 = "1.3.6.1.4.1.55519.1.1.1.5.2.3";
    public static final String OID_RUC = "1.3.6.1.4.1.55519.1.1.1.5.2.4";
    
    /**
     * Certificado a analizar
     */
    private final X509Certificate certificado;

    public CertificadoDigercic(X509Certificate certificado) {
        this.certificado = certificado;
    }
    
    public String getCedulaPasaporte() {
        return obtenerExtension(OID_CEDULA);
    }

    public String getSegundoApellido() {
        return obtenerExtension(OID_APELLIDO_2);
    }

    public String getRuc() {
        return obtenerExtension(OID_RUC);
    }

    /**
     * Retorna el valor de la extension, y una cadena vacia si no existe.
     *
     * @param oid
     * @return
     */
    protected String obtenerExtension(String oid) {
        try {
            String valor = CertUtils.getExtensionValue(certificado, oid);
            return (valor != null) ? valor : "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
