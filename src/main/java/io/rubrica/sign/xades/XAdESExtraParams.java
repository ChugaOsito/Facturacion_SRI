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

import java.util.HashMap;
import java.util.Map;

/**
 * Clase con los par&aacute;metros extra que pueden configurarse para el formato
 * de firma XAdES.
 */
final class XAdESExtraParams {

    /**
     * Si se indica <code>true</code>, <u>no</u> se firma una referencia al nodo
     * <code>KeyInfo</code>, si no se establece valor o se establece a otro
     * valor distinto de <code>true</code>, se firma el nodo incluyendo una
     * referencia a &eacute;l.
     */
    static final String KEEP_KEYINFO_UNSIGNED = "keepKeyInfoUnsigned"; //$NON-NLS-1$

    /**
     * URL en la que se encuentra el documento a firmar, necesario en el caso
     * del formato <i>XAdES Externally Detached</i> (no aplica a contrafirmas).
     */
    public static final String URI = "uri"; //$NON-NLS-1$

    /**
     * Indica si se debe evitar la inclusi&oacute;n de la transformaci&oacute;n
     * XPATH2 que normalmente se a&ntilde;ade para posibilitar las cofirmas y
     * que elimina todas las firmas del documento para dejar &uacute;nicamente
     * el contenido. Por defecto, se encuentra a <code>false</code>.
     * &Uacute;nicamente aplica a firmas <i>enveloped</i>.
     */
    static final String AVOID_XPATH_EXTRA_TRANSFORMS_ON_ENVELOPED = "avoidXpathExtraTransformsOnEnveloped";//$NON-NLS-1$

    /**
     * Indica, mediante un <code>true</code> o <code>false</code>, que debe
     * incluirse en la firma &uacute;nicamente el certificado utilizado para
     * firmar y no su cadena de certificaci&oacute;n completa. Por defecto, se
     * incluir&aacute; toda la cadena de certificaci&oacute;n. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String INCLUDE_ONLY_SIGNNING_CERTIFICATE = "includeOnlySignningCertificate";//$NON-NLS-1$

    /**
     * Indica, mediante <code>true</code> o <code>false</code> (por defecto), si
     * debe usarse un
     * <a href="http://www.w3.org/TR/xmldsig-core1/#sec-o-Manifest">Manifest</a>
     * de XMLDSig con las referencias de firma en vez de firmar directamente
     * estas referencias.<br>
     * Esto permite que sea opcional la comprobaci&oacute;n del destino y
     * huellas digitales de las referencias.
     */
    public static final String USE_MANIFEST = "useManifest";//$NON-NLS-1$

    /**
     * Indica, mediante una expresi&oacute;n XPath (v1), el nodo bajo el cual
     * debe insertarse el nodo de firma en el caso de una firma
     * <i>Enveloped</i>.<br>
     * Si la expresi&oacute;n devuelve m&aacute;s de un nodo, se usa solo el
     * primero. Si la expresi&oacute;n no devuelve nodos o est&aacute; mal
     * construida se lanzar&aacute; una excepci&oacute;n.<br>
     * Este par&aacute;metro solo tiene efecto en firmas <i>Enveloped</i>.
     */
    static final String INSERT_ENVELOPED_SIGNATURE_ON_NODE_BY_XPATH = "insertEnvelopedSignatureOnNodeByXPath";

    /**
     * Indica si se debe evitar la inclusi&oacute;n de la transformaci&oacute;n
     * XPATH2 que normalmente se a&ntilde;ade para posibilitar las cofirmas y
     * que elimina todas las firmas del documento para dejar &uacute;nicamente
     * el contenido. Por defecto, se encuentra a <code>false</code>. No aplica a
     * contrafirmas.
     */
    static final String NODE_TOSIGN = "nodeToSign";

    /**
     * Formato de firma. Se aceptan los siguientes valores:<br>
     * <ul>
     * <li><i>XAdES Detached</i>
     * (<code>AOSignConstants.SIGN_FORMAT_XADES_DETACHED</code>)</li>
     * <li><i>XAdES Externally Detached</i>
     * (<code>AOSignConstants.SIGN_FORMAT_XADES_EXTERNALLY_DETACHED</code>)
     * <p>
     * Para el uso del formato <i>XAdES Externally Detached</i> es necesario
     * establecer tambi&eacute;n el par&aacute;metro <code>uri</code> con una
     * direcci&oacute;n accesible universalmente.
     * </p>
     * </li>
     * <li><i>XAdES Enveloped</i>
     * (<code>AOSignConstants.SIGN_FORMAT_XADES_ENVELOPED</code>)</li>
     * <li><i>XAdES Enveloping</i>
     * (<code>AOSignConstants.SIGN_FORMAT_XADES_ENVELOPING</code>)</li>
     * </ul>
     */
    public static final String FORMAT = "format";//$NON-NLS-1$

    /**
     * Algoritmo de huella digital a usar en las referencias XML
     * (referencesDigestMethod). Debe indicarse como una URL, acept&aacute;ndose
     * los siguientes valores:
     * <ul>
     * <li><i>http://www.w3.org/2000/09/xmldsig#sha1</i> (SHA-1)</li>
     * <li><i>http://www.w3.org/2001/04/xmlenc#sha256</i> (SHA-256, valor
     * recomendado)</li>
     * <li><i>http://www.w3.org/2001/04/xmlenc#sha512</i> (SHA-512)</li>
     * </ul>
     */
    static final String REFERENCES_DIGEST_METHOD = "referencesDigestMethod";//$NON-NLS-1$

    /**
     * Algoritmo de canonicalizaci&oacute;n.
     */
    static final String CANONICALIZATION_ALGORITHM = "canonicalizationAlgorithm";//$NON-NLS-1$

    /**
     * URL de definici&oacute;n del espacio de nombres de XAdES (y por
     * extensi&oacute;n, versi&oacute;n de XAdES). Si se establece este
     * par&aacute;metro es posible que se necesite establecer tambi&eacute;n el
     * par&aacute;metro <code>signedPropertiesTypeUrl</code> para evitar
     * incoherencias en la versi&oacute;n de XAdES.
     */
    static final String XADES_NAMESPACE = "xadesNamespace";//$NON-NLS-1$

    /**
     * URL de definici&oacute;n del tipo de las propiedades firmadas (<i>Signed
     * Properties</i>) de XAdES. Si se establece este par&aacute;metro es
     * posible que se necesite establecer tambi&eacute;n el par&aacute;metro
     * <code>xadesNamespace</code> para evitar incoherencias en la
     * versi&oacute;n de XAdES.<br>
     * Si no se establece se usa el valor por defecto:
     * <a href="http://uri.etsi.org/01903#SignedProperties"
     * >http://uri.etsi.org/01903#SignedProperties</a>.
     */
    static final String SIGNED_PROPERTIES_TYPE_URL = "signedPropertiesTypeUrl";//$NON-NLS-1$

    /**
     * Ignora las hojas de estilo externas de los XML (no las firma) si se
     * establece a <code>true</code>, si se establece a <code>false</code>
     * act&uacute;a normalmente (s&iacute; las firma). (s&iacute; las firma).
     * <br>
     * No aplica a contrafirmas.
     */
    static final String IGNORE_STYLE_SHEETS = "ignoreStyleSheets";//$NON-NLS-1$

    /**
     * No declara transformaciones Base64 incluso si son necesarias si se
     * establece a <code>true</code>, si se establece a <code>false</code>
     * act&uacute;a normalmente (s&iacute; las declara). (s&iacute; las
     * declara). <br>
     * No aplica a contrafirmas)
     */
    static final String AVOID_BASE64_TRANSFORMS = "avoidBase64Transforms";//$NON-NLS-1$

    /**
     * Evita cualquier interacci&oacute;n con el usuario si se establece a
     * <code>true</code>, si se establece a <code>false</code> act&uacute;a
     * normalmente (puede mostrar di&aacute;logos, por ejemplo, para la
     * dereferenciaci&oacute;n de hojas de estilo enlazadas con rutas
     * relativas). &Uacute;til para los procesos desatendidos y por lotes.
     */
    static final String HEADLESS = "headless";//$NON-NLS-1$

    /**
     * Indica, mediante true (por defecto) o false, si debe incluirse el nodo
     * KeyValue dentro de KeyInfo de XAdES.
     */
    static final String ADD_KEY_INFO_KEY_VALUE = "addKeyInfoKeyValue";//$NON-NLS-1$

    /**
     * Indica, mediante true o false (por defecto), si debe incluirse el nodo
     * KeyName dentro de KeyInfo de XAdES.
     */
    public static final String ADD_KEY_INFO_KEY_NAME = "addKeyInfoKeyName";//$NON-NLS-1$

    /**
     * Indica, mediante true o false (por defecto), si debe incluirse el nodo
     * X509IssuerSerial dentro de KeyInfo de XAdES.
     */
    public static final String ADD_KEY_INFO_X509_ISSUER_SERIAL = "addKeyInfoX509IssuerSerial"; //$NON-NLS-1$

    /**
     * Algoritmo utilizado para el c&aacute;lculo de la huella digital cuando se
     * proporciona esta en vez de los datos a firmar. Cuando se proporcione una
     * huella en vez de datos deben tenerse en cuenta los siguientes aspectos:
     * <ul>
     * <li>La huella digital debe indicarse en lugar de los datos, en el mismo
     * par&aacute;metro del m&eacute;todo de firma.</li>
     * <li>Solo puede indicarse una huella cuando no se incluyan los datos
     * dentro de la propia firma, es decir, en firmas <i>externally
     * detached</i>, siendo conveniente adem&aacute;s hacer uso de un
     * <i>Manifest</i>.</li>
     * </ul>
     */
    static final String PRECALCULATED_HASH_ALGORITHM = "precalculatedHashAlgorithm";//$NON-NLS-1$

    /**
     * Indica, mediante un <code>true</code> o <code>false</code>, si se deben
     * realizar las necesarias restricciones de comportamiento para la firma de
     * facturas electr&oacute;nicas (FACTURAe). Estas restricciones son, no
     * introducir la transformaci&oacute;n de canonicalizaci&oacute;n de la
     * firma, ni la transformaci&oacute;n XPATH en las firmas <i>Enveloped</i>.
     */
    static final String FACTURAE_SIGN = "facturaeSign";//$NON-NLS-1$

    /**
     * MIME-Type de los datos a firmar. Si no se indica se realiza una
     * auto-detecci&oacute;n cuyo resultado puede ser inexacto.
     */
    static final String XMLDSIG_OBJECT_MIME_TYPE = "mimeType";//$NON-NLS-1$

    /**
     * Codificaci&oacute;n de los datos a firmar.<br>
     * No aplica a contrafirmas. Por restricc&oacute;n de esquema de XMLDsig
     * debe ser una URI:
     *
     * <pre>
     *    &lt;element name="Object" type="ds:ObjectType"/&gt;
     *    &lt;complexType name="ObjectType" mixed="true"&gt;
     *      &lt;sequence minOccurs="0" maxOccurs="unbounded"&gt;
     *        &lt;any namespace="##any" processContents="lax"/&gt;
     *      &lt;/sequence&gt;
     *      &lt;attribute name="Id" type="ID" use="optional"/&gt;
     *      &lt;attribute name="MimeType" type="string" use="optional"/&gt;
     *      &lt;attribute name="Encoding" type="anyURI" use="optional"/&gt;
     *    &lt;/complexType&gt;
     * </pre>
     */
    static final String XMLDSIG_OBJECT_ENCODING = "encoding";//$NON-NLS-1$

    /**
     * Codificaci&oacute;n del XML de salida. Si no se indica este valor se
     * intenta auto-detectar a partir del XML de entrada (si los datos a firmar
     * son un XML).
     */
    static final String OUTPUT_XML_ENCODING = "outputXmlEncoding"; //$NON-NLS-1$

    /**
     * OID que identifica el tipo de datos a firmar. <br>
     * No aplica a contrafirmas.
     */
    static final String CONTENT_TYPE_OID = "contentTypeOid";//$NON-NLS-1$

    /**
     * Identificador de la pol&iacute;tica de firma (normalmente una URL hacia
     * la pol&iacute;tica en formato XML procesable o una URN de tipo OID). <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String POLICY_IDENTIFIER = "policyIdentifier";//$NON-NLS-1$

    /**
     * Huella digital del documento de pol&iacute;tica de firma (normalmente del
     * mismo fichero en formato XML procesable). Si no se indica, es obligatorio
     * que el par&aacute;metro <code>policyIdentifier</code> sea una URL
     * accesible universalmente. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String POLICY_IDENTIFIER_HASH = "policyIdentifierHash";//$NON-NLS-1$

    /**
     * Algoritmo usado para el c&uacute;lculo de la huella digital indicada en
     * el par&uacute;metro policyIdentifierHash. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String POLICY_IDENTIFIER_HASH_ALGORITHM = "policyIdentifierHashAlgorithm";//$NON-NLS-1$

    /**
     * Descripci&oacute;n textual de la pol&iacute;tica
     */
    static final String POLICY_DESCRIPTION = "policyDescription";//$NON-NLS-1$

    /**
     * URL hacia el documento (legible por personas, normalmente en formato PDF)
     * descriptivo de la pol&iacute;tica de firma. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String POLICY_QUALIFIER = "policyQualifier";//$NON-NLS-1$

    /**
     * Lista de cargos atribuidos al firmante separados por el car&uacute;cter
     * '|'. Los cargos de la lista no pueden contener el car&uacute;cter '|' (ya
     * que este se usa como separador).
     */
    static final String SIGNER_CLAIMED_ROLES = "signerClaimedRoles";//$NON-NLS-1$

    /**
     * Ciudad en la que se realiza la firma. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String SIGNATURE_PRODUCTION_CITY = "signatureProductionCity";//$NON-NLS-1$

    /**
     * Provincia en la que se realiza la firma.
     */
    static final String SIGNATURE_PRODUCTION_PROVINCE = "signatureProductionProvince";//$NON-NLS-1$

    /**
     * C&oacute;digo postal en el que se realiza la firma. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String SIGNATURE_PRODUCTION_POSTAL_CODE = "signatureProductionPostalCode";//$NON-NLS-1$

    /**
     * Pa&iacute;s en el que se realiza la firma. <br>
     * Propiedad compartida con CAdES y PAdES.
     */
    static final String SIGNATURE_PRODUCTION_COUNTRY = "signatureProductionCountry";//$NON-NLS-1$

    /**
     * N&uacute;mero de CommitmentTypeIndications a a&ntilde;adir a la firma
     * XAdES. En los par&uacute;metros siguientes, los CommitmentTypeIndications
     * se numeran a partir de 0 (cero).
     */
    static final String COMMITMENT_TYPE_INDICATIONS = "commitmentTypeIndications";//$NON-NLS-1$

    /**
     * Prefijo de las claves con las que se indican las propiedades de los
     * <i>Commitment Type Indications</i>. Se utilizar&aacute; este prefijo,
     * seguido el n&uacute;mero del commitmentTypeIndication al que queramos
     * referirnos y la clave de la propiedad en cuesti&oacute;n. As&iacute;
     * pues, los par&aacute;metros son:
     * <ul>
     * <li>commitmentTypeIndication<i>n</i>Identifier</li>
     * <li>commitmentTypeIndication<i>n</i>Description</li>
     * <li>commitmentTypeIndication<i>n</i>CommitmentTypeQualifiers</li>
     * <li>commitmentTypeIndication<i>n</i>DocumentationReferences</li>
     * </ul>
     */
    static final String COMMITMENT_TYPE_INDICATION_PREFIX = "commitmentTypeIndication"; //$NON-NLS-1$

    /**
     * Tipo de <i>CommitmentTypeIndication</i> (atributo obligatorio, se debe
     * usar el ordinal, nunca el OID directamente):
     * <ul>
     * <li><i>1</i> = Prueba de origen</li>
     * <li><i>2</i> = Prueba de recepci&oacute;n</li>
     * <li><i>3</i> = Prueba de entrega</li>
     * <li><i>4</i> = Prueba de env&iacute;o</li>
     * <li><i>5</i> = Prueba de aprobaci&oacute;n</li>
     * <li><i>6</i> = Prueba de creaci&oacute;n</li>
     * </ul>
     * <br>
     * Propiedad compartida con CAdES.
     */
    static final String COMMITMENT_TYPE_INDICATION_IDENTIFIER = "Identifier";//$NON-NLS-1$

    /**
     * Descripci&oacute;n textual del CommitmentTypeIndication n&uacute;mero n
     * (atributo opcional).
     */
    static final String COMMITMENT_TYPE_INDICATION_DESCRIPTION = "Description";//$NON-NLS-1$

    /**
     * Lista de URL separadas por el car&aacute;cter '<i>|</i>' que se aportan
     * como referencias documentales del <i>CommitmentTypeIndication</i>
     * n&uacute;mero
     * <i>n</i> (atributo opcional).<br>
     * Las URL de la lista no pueden contener el car&aacute;cter '<i>|</i>' (ya
     * que este se usa como separador)
     */
    static final String COMMITMENT_TYPE_INDICATION_DOCUMENTATION_REFERENCE = "DocumentationReferences"; //$NON-NLS-1$

    /**
     * Lista de indicadores textuales separados por el car&aacute;cter '|' que
     * se aportan como calificadores adicionales del CommitmentTypeIndication
     * n&uacute;mero n (atributo opcional). Normalmente son OID. Los elementos
     * de la lista no pueden contener el car&aacute;cter '|' (ya que este se usa
     * como separador). <br>
     * Propiedad compartida con CAdES.
     */
    static final String COMMITMENT_TYPE_INDICATION_QUALIFIERS = "CommitmentTypeQualifiers"; //$NON-NLS-1$

    // Definicion de las URI para los Commitment Type Identifiers:
    // http://uri.etsi.org/01903/v1.2.2/ts_101903v010202p.pdf
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_ORIGIN = "http://uri.etsi.org/01903/v1.2.2#ProofOfOrigin"; //$NON-NLS-1$
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_RECEIPT = "http://uri.etsi.org/01903/v1.2.2#ProofOfReceipt"; //$NON-NLS-1$
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_DELIVERY = "http://uri.etsi.org/01903/v1.2.2#ProofOfDelivery"; //$NON-NLS-1$
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_SENDER = "http://uri.etsi.org/01903/v1.2.2#ProofOfSender"; //$NON-NLS-1$
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_APPROVAL = "http://uri.etsi.org/01903/v1.2.2#ProofOfApproval"; //$NON-NLS-1$
    private static final String COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_CREATION = "http://uri.etsi.org/01903/v1.2.2#ProofOfCreation"; //$NON-NLS-1$
    static final Map<String, String> COMMITMENT_TYPE_IDENTIFIERS = new HashMap<>(6);

    static {
        COMMITMENT_TYPE_IDENTIFIERS.put("1", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_ORIGIN); //$NON-NLS-1$
        COMMITMENT_TYPE_IDENTIFIERS.put("2", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_RECEIPT); //$NON-NLS-1$
        COMMITMENT_TYPE_IDENTIFIERS.put("3", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_DELIVERY); //$NON-NLS-1$
        COMMITMENT_TYPE_IDENTIFIERS.put("4", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_SENDER); //$NON-NLS-1$
        COMMITMENT_TYPE_IDENTIFIERS.put("5", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_APPROVAL); //$NON-NLS-1$
        COMMITMENT_TYPE_IDENTIFIERS.put("6", COMMITMENT_TYPE_IDENTIFIER_PROOF_OF_CREATION); //$NON-NLS-1$
    }

    /**
     * Modo de firma a usar. El valor explicit indica que no se incluyen los
     * datos firmados, sino una referencia a estos, mientras que el valor
     * implicit indica que s&iacute; se incluir&aacute;n dentro de la propia
     * firma los datos firmados.
     */
    static final String MODE = "mode";//$NON-NLS-1$

    /**
     * Nombre del par&aacute;metro que almacena el identificador de la firma
     * durante un proceso de firma de lote.
     */
    static final String BATCH_SIGNATURE_ID = "SignatureId"; //$NON-NLS-1$

    /**
     * Nombre nodo ra&iacute;z de la firma.
     */
    public static final String ROOT_XML_NODE_NAME = "RootXmlNodeName"; //$NON-NLS-1$

    /**
     * Nombre del namespace en el nodo ra&iacute;z de la firma.
     */
    public static final String ROOT_XML_NODE_NAMESPACE = "RootXmlNodeNamespace"; //$NON-NLS-1$

    /**
     * Nombre del prefijo en el namespace en el nodo ra&iacute;z de la firma.
     */
    public static final String ROOT_XML_NODE_NAMESPACE_PREFIX = "RootXmlNodeNamespacePrefix"; //$NON-NLS-1$

    /**
     * Constructor vac&iacute;o privado para que no se pueda instanciar la clase
     * ya que es est&aacute;tico.
     */
    private XAdESExtraParams() {
        // No instanciable
    }
}
