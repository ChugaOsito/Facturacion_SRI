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
package io.rubrica.validaciones;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Utilidades para Filtrar extensiones
 *
 * @author mfernandez
 */
public class FiltrarArchivosPdf extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Extensiones.getExtension(f);
        if (extension != null) {
            if (extension.equals(Extensiones.PDF)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    // The description of this filter
    public String getDescription() {
        return "Archivos PDF (*.pdf)";
    }

    @Override
    public String toString() {
        return "." + Extensiones.PDF;
    }
}
