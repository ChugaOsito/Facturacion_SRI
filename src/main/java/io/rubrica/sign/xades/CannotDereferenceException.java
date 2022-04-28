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

/**
 * No se puede dereferenciar la hoja de estilo.
 */
public final class CannotDereferenceException extends StyleException {

    /**
     * Construye una excepci&oacute;n que indica la imposibilidad de
     * dereferenciar una hoja de estilo.
     *
     * @param s Mesaje de excepci&oacute;n
     */
    CannotDereferenceException(final String s) {
        super(s);
    }

    /**
     * Construye una excepci&oacute;n que indica la imposibilidad de
     * dereferenciar una hoja de estilo.
     *
     * @param s Mesaje de excepci&oacute;n
     * @param e Excepci&oacute;n anterior en la cadena
     */
    CannotDereferenceException(final String s, final Exception e) {
        super(s, e);
    }
}
