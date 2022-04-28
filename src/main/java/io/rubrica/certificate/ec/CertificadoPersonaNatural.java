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
package io.rubrica.certificate.ec;

/**
 * Certificado que identifica al suscriptos como una persona natural o fisica, y
 * sera responsable a titulo personal de todo lo que firme electronicamente,
 * dentro del ambito de su actividad y limites de uso que correspondan.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public interface CertificadoPersonaNatural {

    /**
     * @return Cedula o Pasaporte
     */
    String getCedulaPasaporte();

    /**
     * @return Nombre(s)
     */
    String getNombres();

    /**
     * @return Primer apellido
     */
    String getPrimerApellido();

    /**
     * @return Segundo apellido (si no tiene queda en blanco)
     */
    String getSegundoApellido();

    /**
     * @return Direccion
     */
    String getDireccion();

    /**
     * @return Telefono
     */
    String getTelefono();

    /**
     * @return Ciudad
     */
    String getCiudad();

    /**
     * @return Pais
     */
    String getPais();

    /**
     * @return RUC (si no tiene queda en blanco)
     */
    String getRuc();
}
