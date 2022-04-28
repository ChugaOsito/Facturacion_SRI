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
package io.rubrica.sign.ooxml.relprovider;

import java.util.LinkedList;
import java.util.List;

import javax.xml.crypto.dsig.spec.TransformParameterSpec;

/**
 * Relationship Transform parameter specification class.
 *
 * @author fcorneli
 */
public final class RelationshipTransformParameterSpec implements TransformParameterSpec {

    private final List<String> sourceIds;

    /**
     * Constructor.
     */
    public RelationshipTransformParameterSpec() {
        this.sourceIds = new LinkedList<>();
    }

    /**
     * A&ntilde;ade una referencia de relaci&oacute;n para el identificados
     * proporcionado.
     *
     * @param sourceId Identificador de origen de la relaci&oacute;n
     */
    public void addRelationshipReference(final String sourceId) {
        this.sourceIds.add(sourceId);
    }

    List<String> getSourceIds() {
        return this.sourceIds;
    }
}
