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
 * Excepcion genérica de Rubrica.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class RubricaException extends Exception {

    static final long serialVersionUID = -7855834122538664923L;

    public RubricaException() {
        super();
    }

    public RubricaException(String message) {
        super(message);
    }

    public RubricaException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RubricaException(Throwable cause) {
        super(cause);
    }
}
