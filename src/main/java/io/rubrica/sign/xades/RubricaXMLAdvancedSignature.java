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

import java.security.GeneralSecurityException;
import java.security.KeyException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import org.w3c.dom.Element;

import es.uji.crypto.xades.jxades.security.xml.WrappedKeyStorePlace;
import es.uji.crypto.xades.jxades.security.xml.XmlWrappedKeyInfo;
import es.uji.crypto.xades.jxades.security.xml.XAdES.XAdES_BES;
import es.uji.crypto.xades.jxades.security.xml.XAdES.XMLAdvancedSignature;

/**
 * Derivado de
 * <code>net.java.xades.security.xml.XAdES.XMLAdvancedSignature</code> con los
 * siguientes cambios:
 * <ul>
 * <li>En el <i>KeyInfo</i> no se a&ntilde;aden los elementos
 * <i>SubjectX500Principal</i> y <i>X509IssuerSerial</i>.</li>
 * <li>Se puede establecer el algoritmo de firma.</li>
 * <li>Se puede establecer el algoritmo de canonicalizaci&oacute;n para la
 * firma.</li>
 * <li>Se puede establecer la URL del espacio de nombres de XAdES.</li>
 * <li>Se puede a&ntilde;adir una hoja de estilo en modo <i>enveloping</i>
 * dentro de la firma.</li>
 * </ul>
 */
public final class RubricaXMLAdvancedSignature extends XMLAdvancedSignature {

    static final Logger logger = Logger.getLogger(RubricaXMLAdvancedSignature.class.getName());

    private RubricaXMLAdvancedSignature(final XAdES_BES xades) {
        super(xades);
    }

    private String canonicalizationMethod = CanonicalizationMethod.INCLUSIVE;
    private Element styleElement = null;
    private String styleType = "text/xsl";
    private String styleEncoding = null;
    private String styleId = null;

    /**
     * A&ntilde;ade una hoja de estilo en modo <i>enveloping</i> dentro de la
     * firma. La referencia para firmarla debe construirse de forma externa,
     * esta clase no la construye ni a&ntilde;ade
     *
     * @param xmlStyle Elemento de estilo XML
     * @param sId Identificador de la hoja de estilo (si se proporciona un nulo
     * no se a&ntilde;ade la hoja de estilo)
     */
    void addStyleSheetEnvelopingOntoSignature(XmlStyle xmlStyle, final String sId) {
        this.styleElement = xmlStyle.getStyleElement();
        if (xmlStyle.getStyleType() != null) {
            this.styleType = xmlStyle.getStyleType();
        }
        this.styleId = sId;
        this.styleEncoding = xmlStyle.getStyleEncoding();
    }

    /**
     * Establece el algoritmo de canonicalizaci&oacute;n.
     *
     * @param canMethod URL del algoritmo de canonicalizaci&oacute;n. Debe estar
     * soportado en XMLDSig 1.0 o 1.1
     */
    void setCanonicalizationMethod(final String canMethod) {
        if (canMethod != null) {
            this.canonicalizationMethod = canMethod;
        }
    }

    private KeyInfo newKeyInfo(final List<Certificate> certs, final String keyInfoId, final boolean addKeyValue,
            final boolean addKeyName, final boolean addIssuerSerial) throws KeyException {

        final List<Certificate> certificates = EscapeHelper.getEscapedCertificates(certs);
        final KeyInfoFactory keyInfoFactory = getXMLSignatureFactory().getKeyInfoFactory();
        final List<Certificate> x509DataList = new ArrayList<>();
        if (!XmlWrappedKeyInfo.PUBLIC_KEY.equals(getXmlWrappedKeyInfo())) {
            for (final Certificate cert : certificates) {
                x509DataList.add(cert);
            }
        }
        final List<XMLStructure> newList = new ArrayList<>();
        newList.add(keyInfoFactory.newX509Data(x509DataList));
        if (addKeyValue) {
            newList.add(keyInfoFactory.newKeyValue(certificates.get(0).getPublicKey()));
        }
        if (addKeyName) {
            newList.add(keyInfoFactory.newKeyName(EscapeHelper
                    .escapeLdapName(((X509Certificate) certificates.get(0)).getSubjectX500Principal().toString())));
        }
        if (addIssuerSerial) {
            newList.add(keyInfoFactory.newX509IssuerSerial(
                    EscapeHelper.escapeLdapName(
                            ((X509Certificate) certificates.get(0)).getIssuerX500Principal().toString()),
                    ((X509Certificate) certificates.get(0)).getSerialNumber()));
        }
        return keyInfoFactory.newKeyInfo(newList, keyInfoId);
    }

    void sign(final List<Certificate> certificates, final PrivateKey privateKey, final String signatureMethod,
            final List<?> refsIdList, final String signatureIdPrefix, final boolean addKeyInfoKeyValue,
            final boolean addKeyInfoKeyName, final boolean addKeyInfoX509IssuerSerial,
            final boolean keepKeyInfoUnsigned)
            throws MarshalException, GeneralSecurityException, XMLSignatureException {

        final List<?> referencesIdList = new ArrayList<>(refsIdList);

        if (WrappedKeyStorePlace.SIGNING_CERTIFICATE_PROPERTY.equals(getWrappedKeyStorePlace()) && certificates != null
                && certificates.size() > 0) {
            this.xades.setSigningCertificate((X509Certificate) certificates.get(0));
        }

        addXMLObject(marshalXMLSignature(this.xadesNamespace, this.signedPropertiesTypeUrl, signatureIdPrefix,
                referencesIdList));

        final XMLSignatureFactory fac = getXMLSignatureFactory();

        if (this.styleElement != null && this.styleId != null) {
            addXMLObject(fac.newXMLObject(Collections.singletonList(new DOMStructure(this.styleElement)), this.styleId,
                    this.styleType, this.styleEncoding));
        }

        final List<Reference> documentReferences = getReferences(referencesIdList);

        final String keyInfoId = getKeyInfoId(signatureIdPrefix);
        if (!keepKeyInfoUnsigned) {
            documentReferences.add(fac.newReference("#" + keyInfoId, getDigestMethod()));
        }

        this.signature = fac.newXMLSignature(
                fac.newSignedInfo(
                        fac.newCanonicalizationMethod(this.canonicalizationMethod, (C14NMethodParameterSpec) null),
                        fac.newSignatureMethod(signatureMethod, null), documentReferences),
                newKeyInfo(certificates, keyInfoId, addKeyInfoKeyValue, addKeyInfoKeyName, addKeyInfoX509IssuerSerial),
                getXMLObjects(), getSignatureId(signatureIdPrefix), getSignatureValueId(signatureIdPrefix));

        this.signContext = new DOMSignContext(privateKey, this.baseElement);
        this.signContext.putNamespacePrefix(XMLSignature.XMLNS, this.xades.getXmlSignaturePrefix());
        this.signContext.putNamespacePrefix(this.xadesNamespace, this.xades.getXadesPrefix());

        try {
            // Obtenemos el dereferenciador por defecto por reflexion
            // e instalamos uno nuevo que solo actua cuando falla el por defecto
            this.signContext
                    .setURIDereferencer(new CustomUriDereferencer(CustomUriDereferencer.getDefaultDereferencer()));
        } catch (final Exception e) {
            logger.log(Level.WARNING,
                    "No se ha podido instalar un dereferenciador a medida, es posible que fallen las firmas de nodos concretos: "
                    + e,
                    e);
        }

        this.signature.sign(this.signContext);
    }

    /**
     * Obtiene una instancia de la clase.
     *
     * @param xades Datos de la firma XAdES-BES
     * @return Instancia de la clase
     * @throws GeneralSecurityException Cuando se especifica una XAdES con un
     * algoritmo de huella digital no soportado.
     */
    public static RubricaXMLAdvancedSignature newInstance(final XAdES_BES xades) throws GeneralSecurityException {
        final RubricaXMLAdvancedSignature result = new RubricaXMLAdvancedSignature(xades);
        result.setDigestMethod(xades.getDigestMethod());
        result.setXadesNamespace(xades.getXadesNamespace());
        return result;
    }
}
