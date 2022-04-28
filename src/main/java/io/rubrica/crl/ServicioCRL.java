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
package io.rubrica.crl;

import io.rubrica.utils.HttpClient;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;

/**
 * Se establecen url de consulta
 * 
 * @author mfernandez
 */
public class ServicioCRL {

    public static final String BCE_CRL = "http://www.eci.bce.ec/CRL/eci_bce_ec_crlfilecomb.crl";
    public static final String SD_CRL1 = "https://direct.securitydata.net.ec/~crl/autoridad_de_certificacion_sub_security_data_entidad_de_certificacion_de_informacion_curity_data_s.a._c_ec_crlfile.crl";
    public static final String SD_CRL2 = "https://portal-operador.securitydata.net.ec/ejbca/publicweb/webdist/certdist?cmd=crl&issuer=CN%3DAUTORIDAD+DE+CERTIFICACION+SUBCA-1+SECURITY+DATA%2COU%3DENTIDAD+DE+CERTIFICACION+DE+INFORMACION%2CO%3DSECURITY+DATA+S.A.+1%2CC%3DEC";
    public static final String SD_CRL3 = "https://portal-operador.securitydata.net.ec/ejbca/publicweb/webdist/certdist?cmd=deltacrl&issuer=CN%3DAUTORIDAD+DE+CERTIFICACION+SUBCA-1+SECURITY+DATA%2COU%3DENTIDAD+DE+CERTIFICACION+DE+INFORMACION%2CO%3DSECURITY+DATA+S.A.+1%2CC%3DEC";
    public static final String SD_CRL4 = "https://portal-operador2.securitydata.net.ec/ejbca/publicweb/webdist/certdist?cmd=crl&issuer=CN%3DAUTORIDAD+DE+CERTIFICACION+SUBCA-2+SECURITY+DATA%2COU%3DENTIDAD+DE+CERTIFICACION+DE+INFORMACION%2CO%3DSECURITY+DATA+S.A.+2%2CC%3DEC";
    public static final String SD_CRL5 = "https://portal-operador2.securitydata.net.ec/ejbca/publicweb/webdist/certdist?cmd=deltacrl&issuer=CN%3DAUTORIDAD+DE+CERTIFICACION+SUBCA-2+SECURITY+DATA%2COU%3DENTIDAD+DE+CERTIFICACION+DE+INFORMACION%2CO%3DSECURITY+DATA+S.A.+2%2CC%3DEC";
    public static final String CJ_CRL = "https://www.icert.fje.gob.ec/crl/icert.crl";
    public static final String ANFAC_CRL = "http://www.anf.es/crl/ANF_Ecuador_CA1_SHA256.crl";
    public static final String DIGERCIC_CRL = "https://firma.registrocivil.gob.ec/crl.crl";
    public static final String UANATACA_CRL1 = "http://crl1.uanataca.com/public/pki/crl/CA2subordinada.crl";
    public static final String UANATACA_CRL2 = "http://crl2.uanataca.com/public/pki/crl/CA2subordinada.crl";

    public static X509CRL downloadCrl(String url) throws Exception {
        byte[] content;

        HttpClient http = new HttpClient();
        content = http.download(url);

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509CRL) cf.generateCRL(new ByteArrayInputStream(content));

    }

}
