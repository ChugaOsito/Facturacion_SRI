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
package io.rubrica.exceptions;

/**
 * Excepci&oacute;n para notificar que se ha encontrado un objeto con un formato
 * inesperado.
 * 
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class InvalidFormatException extends RubricaException {

    private static final long serialVersionUID = 3204897511735178462L;

    /**
     * Crea la excepci&oacute;n con un mensaje determinado.
     *
     * @param msg Mensaje descriptivo de la excepci&oacute;n.
     */
    public InvalidFormatException(String msg) {
        super(msg);
    }

    /**
     * Crea la excepci&oacute;n con un mensaje determinado.
     *
     * @param msg Mensaje descriptivo de la excepci&oacute;n.
     * @param e Excepci&oacute;n que ha causado el lanzamiento de esta.
     */
    public InvalidFormatException(String msg, Exception e) {
        super(msg, e);
    }
}
