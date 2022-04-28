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
package io.rubrica.sign.pdf;

import com.lowagie.text.Rectangle;

public class RectanguloParaFirmar {

    public static final int ANCHO_RECTANGULO_FIRMA = 108;
    public static final int ALTO_RECTANGULO_FIRMA = 32;

    public static Rectangle obtenerRectangulo(Rectangle dimensionHoja, float posicionUnitariaX,
            float posicionUnitariaY) {
        float lowerLeftX = dimensionHoja.getWidth() * posicionUnitariaX - ANCHO_RECTANGULO_FIRMA / 2;
        float lowerLeftY = dimensionHoja.getHeight() * posicionUnitariaY - ALTO_RECTANGULO_FIRMA / 2;
        float upperLeftX = dimensionHoja.getWidth() * posicionUnitariaX + ANCHO_RECTANGULO_FIRMA / 2;
        float upperLeftY = dimensionHoja.getHeight() * posicionUnitariaY + ALTO_RECTANGULO_FIRMA / 2;
        return new Rectangle(lowerLeftX, lowerLeftY, upperLeftX, upperLeftY);
    }
}
