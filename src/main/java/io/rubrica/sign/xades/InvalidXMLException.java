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
package io.rubrica.sign.xades;

import io.rubrica.exceptions.FormatFileException;

/**
 * Excepci&oacute;n para notificar que se ha encontrado un objeto que no es un
 * XML v&aacute;lido.
 *
 * @author Tom&aacute;s Garc&iacute;a-Mer&aacute;s
 */
public final class InvalidXMLException extends FormatFileException {

    /**
     * Construye una excepci&oacute;n para notificar que se ha encontrado un
     * objeto que no es un XML apto para ser firmado con los par&aacute;metros
     * indicados.
     *
     * @param e Causa de la excepci&oacute;n
     */
    public InvalidXMLException(final Throwable e) {
        super("Los datos proporcionados no son un XML apto para su firma con los parametros indicados: " + e, e); //$NON-NLS-1$
    }

    /**
     * Construye una excepci&oacute;n para notificar que se ha encontrado un
     * objeto que no es un XML apto para ser firmado con los par&aacute;metros
     * indicados.
     *
     * @param msg Mensaje de la excepci&oacute;n
     */
    public InvalidXMLException(final String msg) {
        super(msg);
    }

    /**
     * Construye una excepci&oacute;n para notificar que se ha encontrado un
     * objeto que no es un XML apto para ser firmado con los par&aacute;metros
     * indicados.
     *
     * @param msg Mensaje de la excepci&oacute;n.
     * @param e Causa de la excepci&oacute;n.
     */
    public InvalidXMLException(final String msg, final Throwable e) {
        super(msg, e);
    }

}
