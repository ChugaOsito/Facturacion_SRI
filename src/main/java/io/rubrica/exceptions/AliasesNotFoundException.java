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
 * Excecion lanzada al no encontrar un alias en un KeyStore.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 * @deprecated
 */
public class AliasesNotFoundException extends Exception {

    public AliasesNotFoundException() {
    }

    public AliasesNotFoundException(String message) {
        super(message);
    }

    public AliasesNotFoundException(Throwable cause) {
        super(cause);
    }

    public AliasesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
