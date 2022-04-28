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

import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.ietf.jgss.Oid;

import es.uji.crypto.xades.jxades.security.xml.XAdES.SignaturePolicyIdentifier;
import es.uji.crypto.xades.jxades.security.xml.XAdES.SignaturePolicyIdentifierImpl;
import es.uji.crypto.xades.jxades.security.xml.XAdES.SignatureProductionPlace;
import es.uji.crypto.xades.jxades.security.xml.XAdES.SignatureProductionPlaceImpl;
import es.uji.crypto.xades.jxades.security.xml.XAdES.SignerRole;
import es.uji.crypto.xades.jxades.security.xml.XAdES.SignerRoleImpl;
import es.uji.crypto.xades.jxades.security.xml.XAdES.XAdES_EPES;
import io.rubrica.exceptions.RubricaException;

final class XAdESCommonMetadataUtil {

    private static final Logger logger = Logger.getLogger(XAdESCommonMetadataUtil.class.getName());

    private XAdESCommonMetadataUtil() {
        // No instanciable
    }

    static void addCommonMetadata(final XAdES_EPES xades, final Properties extraParams) throws RubricaException {

        // SignaturePolicyIdentifier
        final SignaturePolicyIdentifier spi;
        try {
            spi = getPolicy(extraParams);
        } catch (final NoSuchAlgorithmException e1) {
            throw new RubricaException("El algoritmo indicado para la politica ("
                    + extraParams.getProperty(XAdESExtraParams.POLICY_IDENTIFIER_HASH_ALGORITHM)
                    + ") no esta soportado: " + e1, e1);
        }
        if (spi != null) {
            xades.setSignaturePolicyIdentifier(spi);
        }

        // SignatureProductionPlace
        final SignatureProductionPlace spp = getSignatureProductionPlace(extraParams);
        if (spp != null) {
            xades.setSignatureProductionPlace(spp);
        }

        // SignerRole
        final SignerRole signerRole = parseSignerRole(extraParams);
        if (signerRole != null) {
            xades.setSignerRole(signerRole);
        }

    }

    private static SignerRole parseSignerRole(final Properties extraParams) {
        if (extraParams == null) {
            return null;
        }
        SignerRole signerRole = null;
        try {
            final String claimedRole = extraParams.getProperty(XAdESExtraParams.SIGNER_CLAIMED_ROLES);
            signerRole = new SignerRoleImpl();
            if (claimedRole != null) {
                final String[] roles = claimedRole.split(Pattern.quote("|"));
                for (final String role : roles) {
                    signerRole.addClaimedRole(role);
                }
            }
        } catch (final Exception e) {
            // Se ignoran los errores, el parametro es opcional
        }
        return signerRole;
    }

    private static SignatureProductionPlace getSignatureProductionPlace(final Properties extraParams) {
        if (extraParams == null) {
            return null;
        }
        return getSignatureProductionPlace(extraParams.getProperty(XAdESExtraParams.SIGNATURE_PRODUCTION_CITY),
                extraParams.getProperty(XAdESExtraParams.SIGNATURE_PRODUCTION_PROVINCE),
                extraParams.getProperty(XAdESExtraParams.SIGNATURE_PRODUCTION_POSTAL_CODE),
                extraParams.getProperty(XAdESExtraParams.SIGNATURE_PRODUCTION_COUNTRY));
    }

    private static SignatureProductionPlace getSignatureProductionPlace(final String city, final String province,
            final String postalCode, final String country) {
        if (city == null && province == null && postalCode == null && country == null) {
            return null;
        }
        return new SignatureProductionPlaceImpl(city, province, postalCode, country);
    }

    private static SignaturePolicyIdentifier getPolicy(final Properties extraParams) throws NoSuchAlgorithmException {
        if (extraParams == null) {
            return null;
        }
        return getPolicy(extraParams.getProperty(XAdESExtraParams.POLICY_IDENTIFIER),
                extraParams.getProperty(XAdESExtraParams.POLICY_IDENTIFIER_HASH),
                extraParams.getProperty(XAdESExtraParams.POLICY_IDENTIFIER_HASH_ALGORITHM),
                extraParams.getProperty(XAdESExtraParams.POLICY_DESCRIPTION),
                extraParams.getProperty(XAdESExtraParams.POLICY_QUALIFIER));
    }

    private static SignaturePolicyIdentifier getPolicy(final String id, final String identifierHash,
            final String identifierHashAlgorithm, final String description, final String qualifier)
            throws NoSuchAlgorithmException {
        if (id == null) {
            return null;
        }

        String identifier;
        try {
            logger.warning("Se proporciono directamente un OID como identificador de politica (" + new Oid(id)
                    + "), se tranformara en URN con el prefijo 'urn:oid:'");
            identifier = "urn:oid:" + id;
        } catch (final Exception e1) {
            identifier = id;
        }

        final String hashAlgo = identifierHashAlgorithm != null
                ? XAdESUtil.getDigestMethodByCommonName(identifierHashAlgorithm)
                : null;

        final SignaturePolicyIdentifier spi = new SignaturePolicyIdentifierImpl(false);
        try {
            spi.setIdentifier(identifier, hashAlgo != null ? identifierHash : null, hashAlgo);
        } catch (final Exception e) {
            logger.warning("No se ha podido acceder al identificador ('" + identifier
                    + "') de la politica de firma, no se anadira este campo: " + e);
            return null;
        }
        // Error en JXAdES. Si la descripcion es nula toda la firma falla.
        final String desc = description != null ? description : "";
        spi.setDescription(desc);

        if (qualifier != null) {
            spi.setQualifier(qualifier);
        }
        return spi;
    }

}
