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
package io.rubrica.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Logger;

import io.rubrica.xml.FileUtils;

/**
 * M&eacute;todos de utilidad para la gesti&oacute;n de MimeType y OID
 * identificadores de tipo de contenidos.
 */
public final class MimeHelper {

    private static final Logger logger = Logger.getLogger(MimeHelper.class.getName());

    /**
     * Valor que devuelve JMimeMagic por defecto cuando no sabe identificar la
     * extension o el mimetype de unos datos.
     */
    public static final String UNKNOWN_JMIMEMAGIC_VALUE = "???";

    /**
     * MimeType por defecto.
     */
    public static final String DEFAULT_MIMETYPE = "application/octet-stream";

    /**
     * Descriptor por defecto del tipo de contenido.
     */
    public static final String DEFAULT_CONTENT_DESCRIPTION = "binary";

    /**
     * OID del tipo de datos gen&eacute;rico.
     */
    public static final String DEFAULT_CONTENT_OID_DATA = "1.2.840.113549.1.7.1";

    /**
     * Tabla que asocia Oids y Mimetypes.
     */
    private static Properties oidMimetypeProp = null;

    /**
     * Tabla que asocia Mimetypes y Oids.
     */
    private static Properties mimetypeOidProp = null;

    /**
     * Objeto para el almac&eacute;n de la informaci&oacute;n de los datos.
     */
    private MimeInfo mimeInfo = null;

    /**
     * Datos analizados.
     */
    private final byte[] data;

    /**
     * MimeType de los datos analizados.
     */
    private String mimeType = null;

    /**
     * Realiza el an&aacute;lisis de los datos.
     *
     * @param data Datos que se desean analizar.
     * @throws NullPointerException Cuando se introducen datos nulos.
     */
    public MimeHelper(final byte[] data) {

        if (data == null) {
            throw new IllegalArgumentException("No se han indicado los datos que se desean analizar");
        }

        this.data = data.clone();
        this.mimeInfo = new MimeInfo();

        try {
            final Method getMagicMatchMethod = Class.forName("net.sf.jmimemagic.Magic").getMethod("getMagicMatch",
                    new Class[]{byte[].class});
            final Object magicMatchObject = getMagicMatchMethod.invoke(null, this.data);

            final Class<?> magicMatchClass = Class.forName("net.sf.jmimemagic.MagicMatch");
            String mt = (String) magicMatchClass.getMethod("getMimeType", (Class[]) null).invoke(magicMatchObject,
                    (Object[]) null);
            if (mt != null && UNKNOWN_JMIMEMAGIC_VALUE.equals(mt)) {
                mt = null;
            }
            this.mimeInfo.setMimeType(mt);
            String ext = (String) magicMatchClass.getMethod("getExtension", (Class[]) null).invoke(magicMatchObject,
                    (Object[]) null);
            if (ext != null && UNKNOWN_JMIMEMAGIC_VALUE.equals(ext)) {
                ext = null;
            }
            this.mimeInfo.setExtension(ext);
            String desc = (String) magicMatchClass.getMethod("getDescription", (Class[]) null).invoke(magicMatchObject,
                    (Object[]) null);
            if (desc != null && UNKNOWN_JMIMEMAGIC_VALUE.equals(desc)) {
                desc = null;
            }
            this.mimeInfo.setDescription(desc);
        } catch (final ClassNotFoundException e) {
            logger.warning("No se encontro la biblioteca JMimeMagic para la deteccion del tipo de dato: " + e);
        } catch (final Exception e) {
            try {
                final Class<?> magicMatchNotFoundException = Class
                        .forName("net.sf.jmimemagic.MagicMatchNotFoundException");
                if (e.getCause() != null && magicMatchNotFoundException.isInstance(e.getCause())) {
                    logger.warning("No se pudo detectar el formato de los datos");
                } else {
                    logger.warning("Error durante el analisis de la cabecera de los datos: " + e);
                }
            } catch (final Exception e2) {
                logger.warning("Error al cargar las bibliotecas de deteccion del tipo de dato: " + e2);
            }
        }
    }

    /**
     * Obtiene el Oid correspondiente a un Mime Type concreto. Si no conoce el
     * Oid asociado, devuelve el OID por defecto para datos binarios
     * (DEFAULT_CONTENT_OID_DATA).
     *
     * @param mimetype del que deseamos obtener el Oid.
     * @return OID asociado al Mime Type.
     * @throws IOException Si no se puede cargar la tabla de correspondencias
     * entre MIMEType y OID
     */
    public static String transformMimeTypeToOid(final String mimetype) throws IOException {
        if (mimetypeOidProp == null) {
            loadMimetypeOidProperties();
        }
        return mimetype == null ? DEFAULT_CONTENT_OID_DATA
                : mimetypeOidProp.getProperty(mimetype, DEFAULT_CONTENT_OID_DATA);
    }

    /**
     * Obtiene el Mime correspondiente a un Oid concreto. Si no conoce el Mime
     * Type asociado, devuelve el gen&eacute;rico de datos binarios
     * (DEFAULT_MIMETYPE).
     *
     * @param oid del que deseamos obtener el Mime Type.
     * @return MimeType asociado al OID.
     * @throws IOException Si no se puede cargar la tabla de correspondencias
     * entre MIMEType y OID
     */
    public static String transformOidToMimeType(final String oid) throws IOException {
        if (oidMimetypeProp == null) {
            loadOidMimetypeProperties();
        }
        return oid == null ? DEFAULT_MIMETYPE : oidMimetypeProp.getProperty(oid, DEFAULT_MIMETYPE);
    }

    /**
     * Carga el fichero de propiedades que relaciona OID de formato con su mime
     * type correspondiente.
     *
     * @throws IOException Cuando hay errores en la carga del fichero de
     * propiedades
     */
    private static void loadOidMimetypeProperties() throws IOException {
        oidMimetypeProp = new Properties();
        final InputStream isProp = MimeHelper.class.getClassLoader()
                .getResourceAsStream("mimetypes_oids.properties");
        if (isProp == null) {
            throw new IOException("No se ha encontrado el fichero de recursos para la relacion entre OIDs y MimeTypes"); //$NON-NLS-1$
        }
        oidMimetypeProp.load(isProp);
        isProp.close();
    }

    /**
     * Carga la tabla de relacion de MimeTypes y OID.
     *
     * @throws IOException Cuando hay errores en la carga del fichero de
     * propiedades
     */
    private static void loadMimetypeOidProperties() throws IOException {
        if (oidMimetypeProp == null) {
            loadOidMimetypeProperties();
        }
        mimetypeOidProp = new Properties();
        for (final String key : oidMimetypeProp.keySet().toArray(new String[0])) {
            mimetypeOidProp.put(oidMimetypeProp.get(key), key);
        }
    }

    /**
     * Recupera el MimeType de los datos analizados, <code>null</code> si no se
     * pudo detectar.
     *
     * @return MimeType de los datos.
     * @throws IOException Si no se pueden analizar los datos
     */
    public String getMimeType() throws IOException {

        // Comprobamos si ya se calculo previamente el tipo de datos
        if (this.mimeType == null) {

            if (this.mimeInfo != null) {
                this.mimeType = this.mimeInfo.getMimeType();
            }

            // Si no hubo analisis inicial o este indico que los datos son XML,
            // comprobamos
            // si los datos son XML en realidad
            if (this.mimeInfo == null || "text/xml".equals(this.mimeType)) {
                if (FileUtils.isXML(this.data)) {
                    this.mimeType = "text/xml";
                }
            }

            // Cuando el MimeType sea el de un fichero ZIP o el de Microsoft
            // Word, comprobamos si es en
            // realidad alguno de los ficheros ofimaticos soportados
            if ("application/zip".equals(this.mimeType) || "application/msword".equals(this.mimeType)) {
                this.mimeType = OfficeAnalizer.getMimeType(this.data);
            }

            if (this.mimeType == null) {
                this.mimeType = MimeHelper.DEFAULT_MIMETYPE;
            }
        }

        return this.mimeType;
    }

    /**
     * Recupera la extensi&oacute;n com&uacute;n para un fichero con los datos
     * analizados, {@code null} si no se conoce. La extensi&oacute;n se devuelve
     * sin el punto separador.
     *
     * @return Extensi&oacute;n para un fichero de datos.
     */
    public String getExtension() {

        String extension = null;

        // Comprobamossi los datos son XML, si no, los parseamos
        if (FileUtils.isXML(this.data)) {
            extension = "xml";
        } else if (this.mimeInfo != null) {
            extension = this.mimeInfo.getExtension();
        }

        // Cuando el MimeType sea el de un fichero ZIP, comprobamos si es en
        // realidad alguno de los ficheros ofimaticos soportados (que son ZIP
        // con una
        // estructura concreta)
        if (extension != null && extension.equals("zip")) {
            try {
                extension = OfficeAnalizer.getExtension(this.data);
            } catch (final IOException e) {
                logger.severe(
                        "No se ha podido comprobar si el ZIP corresponde a un ODF o a un OOXML, se tomara como ZIP: " //$NON-NLS-1$
                        + e);
            }
        }

        return extension;
    }

    /**
     * Recupera la descripcion de los datos analizados, <code>null</code> si no
     * se pudo detectar.
     *
     * @return Descripci&oacute;n del tipo de dato.
     */
    public String getDescription() {
        String desc = null;
        if (this.mimeInfo != null) {
            desc = this.mimeInfo.getDescription();
        }
        return desc == null || desc.length() == 0 ? DEFAULT_CONTENT_DESCRIPTION : desc;
    }

    /**
     * Almacena la informaci&oacute;n identificada del tipo de datos.
     */
    static class MimeInfo {

        /**
         * MimeType de los datos.
         */
        private String mType = null;

        void setMimeType(final String mimeType) {
            this.mType = mimeType;
        }

        String getMimeType() {
            return this.mType;
        }

        /**
         * Extensi&oacute;n com&uacute;n para el tipo de fichero.
         */
        private String extension = null;

        String getExtension() {
            return this.extension;
        }

        void setExtension(final String ext) {
            this.extension = ext;
        }

        String getDescription() {
            return this.description;
        }

        void setDescription(final String desc) {
            this.description = desc;
        }

        /**
         * Descripci&oacute;n del tipo de datos.
         */
        private String description = null;
    }
}
