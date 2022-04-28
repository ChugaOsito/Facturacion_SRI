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
package io.rubrica.sign.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parser de ficheros XML Relationships. Este tipo de fichero se encuentra
 * comunmente dentro de los ficheros OOXML de Microsoft Office con el nombre
 * ".rels".
 */
final class RelationshipsParser {

    /**
     * Esquema del XML Relationships.
     */
    private static final String RELATIONSHIPS_SCHEMA = "http://schemas.openxmlformats.org/package/2006/relationships";

    /**
     * Listado de relaciones obtenido.
     */
    private Relationship[] relations = null;

    /**
     * Construye un parser de objetos XML RelationsShips.
     *
     * @param xmlRelationships XML con el objeto RelationShips.
     * @throws ParserConfigurationException Cuando hay problemas con el
     * analizador SAX.
     * @throws IOException Cuando hay incosistencias de formato OOXML en el XML
     * de entrada.
     * @throws SAXException Cuando el XML de entrada no est&aacute; bien
     * formado.
     */
    RelationshipsParser(final InputStream xmlRelationships)
            throws IOException, SAXException, ParserConfigurationException {
        this.relations = getRelationships(xmlRelationships);
    }

    /**
     * Recupera el listado de relaciones extraido del XML.
     *
     * @return Listado de relaciones.
     */
    Relationship[] getRelationships() {
        return this.relations;
    }

    /**
     * Recupera las relaciones definidas en el XML.
     *
     * @param xmlRelationships Entrada del XML con las relaciones.
     * @return Listado de relaciones.
     * @throws ParserConfigurationException Cuando hay problemas con el
     * analizador SAX.
     * @throws IOException Cuando hay incosistencias de formato OOXML en el XML
     * de entrada.
     * @throws SAXException Cuando el XML de entrada no est&aacute; bien
     * formado.
     */
    private static Relationship[] getRelationships(InputStream xmlRelationships)
            throws SAXException, IOException, ParserConfigurationException {

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlRelationships);

        // Obtenemos la raiz
        Element root = doc.getDocumentElement();

        // Si no se ajusta a la estructura de las Relationships, devolvemos null
        if (!root.getNodeName().equals("Relationships") || root.getAttributeNode("xmlns") == null
                || !root.getAttribute("xmlns").equals(RELATIONSHIPS_SCHEMA)) {
            throw new IOException("El nodo principal no es una etiqueta Relationships");
        }

        NodeList relationsList = root.getChildNodes();
        List<Relationship> relationsVector = new ArrayList<>();

        for (int i = 0; i < relationsList.getLength(); i++) {
            relationsVector.add(RelationshipsParser.getRelationship(relationsList.item(i)));
        }

        return relationsVector.toArray(new Relationship[0]);
    }

    /**
     * Recupera una relaci&oacute;n individual de un nodo Relationship. Un nodo
     * <code>Relationship</code> tiene estas propiedades:
     * <ul>
     * <li><b>Nombre:</b> Relationship</li>
     * <li><b>Atributos:</b> id, type y target</li>
     * </ul>
     *
     * @param node Nodo XML <code>Relationship</code>.
     * @return Objeto de relaci&oacute;n.
     * @throws IOException Cuando el nodo no encaja con el patr&oacute;n
     * Relationship.
     */
    private static Relationship getRelationship(Node node) throws IOException {
        // Comprobamos que sea un nodo de relacion
        if (!node.getNodeName().equals("Relationship")) {
            throw new IOException("Se ha encontrado un nodo que es de relacion: " + node.getNodeName());
        }

        // Comprobamos que tenga todos sus atributos
        NamedNodeMap attributes = node.getAttributes();

        if (attributes.getNamedItem("Id") == null || attributes.getNamedItem("Type") == null
                || attributes.getNamedItem("Target") == null) {
            throw new IOException("Se ha encontrado un nodo de relacion que no disponia de todos sus atributos");
        }

        // Creamos la relacion
        return new Relationship(attributes.getNamedItem("Id").getNodeValue(),
                attributes.getNamedItem("Type").getNodeValue(), attributes.getNamedItem("Target").getNodeValue());
    }
}
