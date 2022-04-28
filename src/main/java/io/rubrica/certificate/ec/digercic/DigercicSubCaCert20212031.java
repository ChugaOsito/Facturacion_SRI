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
package io.rubrica.certificate.ec.digercic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/**
 * Certificado intermedio del Banco Central del Ecuador, representado como un
 * objeto <code>X509Certificate</code>.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class DigercicSubCaCert20212031 extends X509Certificate {

    private X509Certificate certificate;

    public DigercicSubCaCert20212031() {
        super();

        StringBuilder cer = new StringBuilder();
        cer.append("-----BEGIN CERTIFICATE-----\n");
        cer.append("MIIIbjCCBlagAwIBAgIUMDAwMDAwMDAwMDAwMDAwMDAwMDQwDQYJKoZIhvcNAQEL\n");
        cer.append("BQAwggFFMQ4wDAYDVQQHEwVRdWl0bzESMBAGA1UECBMJUGljaGluY2hhMQswCQYD\n");
        cer.append("VQQGEwJFQzEuMCwGA1UECRMlQXYuIEFtYXpvbmFzIE4zNy02MSB5IE5hY2lvbmVz\n");
        cer.append("IFVuaWRhczE1MDMGA1UEAwwsQXV0b3JpZGFkIENlcnRpZmljYWRvcmEgUmHDrXog\n");
        cer.append("ZGUgbGEgRElHRVJDSUMxKzApBgNVBAsMIkNvb3JkaW5hY2nDs24gR2VuZXJhbCBk\n");
        cer.append("ZSBTZXJ2aWNpb3MxTDBKBgNVBAoMQ0RpcmVjY2nDs24gR2VuZXJhbCBkZSBSZWdp\n");
        cer.append("c3RybyBDaXZpbCwgSWRlbnRpZmljYWNpw7NuIHkgQ2VkdWxhY2nDs24xMDAuBgkq\n");
        cer.append("hkiG9w0BCQEWIWluZm8tZmlybWFyY0ByZWdpc3Ryb2NpdmlsLmdvYi5lYzAeFw0y\n");
        cer.append("MTAxMjYwMDAwMDBaFw0zMTAxMjYwMDAwMDBaMIIBPzEOMAwGA1UEBxMFUXVpdG8x\n");
        cer.append("EjAQBgNVBAgTCVBpY2hpbmNoYTELMAkGA1UEBhMCRUMxLjAsBgNVBAkTJUF2LiBB\n");
        cer.append("bWF6b25hcyBOMzctNjEgeSBOYWNpb25lcyBVbmlkYXMxLzAtBgNVBAMTJkF1dG9y\n");
        cer.append("aWRhZCBDZXJ0aWZpY2Fkb3JhIGRlIGxhIERJR0VSQ0lDMSswKQYDVQQLDCJDb29y\n");
        cer.append("ZGluYWNpw7NuIEdlbmVyYWwgZGUgU2VydmljaW9zMUwwSgYDVQQKDENEaXJlY2Np\n");
        cer.append("w7NuIEdlbmVyYWwgZGUgUmVnaXN0cm8gQ2l2aWwsIElkZW50aWZpY2FjacOzbiB5\n");
        cer.append("IENlZHVsYWNpw7NuMTAwLgYJKoZIhvcNAQkBFiFpbmZvLWZpcm1hcmNAcmVnaXN0\n");
        cer.append("cm9jaXZpbC5nb2IuZWMwggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQC7\n");
        cer.append("/QT9//BZz4Txlix67LO+n4lNwCQrOvO7NjsNk0N9VU/ANejWISKlqZteTB2Z1qNx\n");
        cer.append("FDPUzj8IhWHEbLTP8znwThPhv7uv0W3tPY0ChdJ5a2JtcESdwUR13uotfzs6w46J\n");
        cer.append("BCWdOr6JbGcrhWMv143/9ff1BgmFJWgNr8E3ia8x8YRaQ8iUzZfAq3M2ycJ7oFwA\n");
        cer.append("xBP8jCDUf4xgh0T3i3g9qM2NCOqTTe3KWjwLcGXz+EOzt36tc5IaYIH0LKiP2ia/\n");
        cer.append("HkauilO0lA6SXeOtMHVMlI8YGYcgr5Mykigahlfd7zTKa2LQkCQcQjViYdrAIaOy\n");
        cer.append("QRT//9kWJCLYFPmAgbqD6CR9G2SWRsVL+58GtYh/8MCo9wysOsyoiSlQvizKppMG\n");
        cer.append("n4/9w1sFHcqXzlLpIoZL8mZReg5BXIlouzkwm1Nkzbo6cL/MaEKJnjvsN3k7Uwk1\n");
        cer.append("5a73D7mHBQvcv+AIo24B2SRJMcTc0Sq6MiMDcM0rgPWYoci/tqgveX0IhEWls1Mc\n");
        cer.append("4QQNWDg77kxokvbonYk4oY4Aonuvg/wioi7Z7aH0N9rGQ6vS7edPTK9Kij2BGnNS\n");
        cer.append("NlMh0Yg7QfwYFE09+W4RiAjVfACJfUduWIxeowBg7grygjOAZQkc8rVfQawkZbIV\n");
        cer.append("CsZbRU79M3eTt2Tp1lW1bK5x5VP2erJ2+vZYoxDINwIDAQABo4IBVjCCAVIwHwYD\n");
        cer.append("VR0jBBgwFoAUt+WkO2Cl6kF92xO7HimLzOaxySAwHQYDVR0OBBYEFNPcV1hiZ/xE\n");
        cer.append("gejohTRU8sxSaCp8MEIGCCsGAQUFBwEBBDYwNDAyBggrBgEFBQcwAYYmaHR0cDov\n");
        cer.append("L29jc3AucmVnaXN0cm9jaXZpbC5nb2IuZWM6ODA4My8wgZcGA1UdIAEB/wSBjDCB\n");
        cer.append("iTCBhgYOKwYBBAGDsV8BAQEFAwIwdDA4BggrBgEFBQcCARYsaHR0cHM6Ly9maXJt\n");
        cer.append("YS5yZWdpc3Ryb2NpdmlsLmdvYi5lYy9wb2xpdGljYXMwOAYIKwYBBQUHAgEWLGh0\n");
        cer.append("dHBzOi8vZmlybWEucmVnaXN0cm9jaXZpbC5nb2IuZWMvcG9saXRpY2FzMA8GA1Ud\n");
        cer.append("EwEB/wQFMAMBAf8wDgYDVR0PAQH/BAQDAgH+MBEGCWCGSAGG+EIBAQQEAwIABzAN\n");
        cer.append("BgkqhkiG9w0BAQsFAAOCAgEAXVPWsmEEOYJcLXxQzos8F6kJ84pZ96ucMTTpAWzO\n");
        cer.append("AhTTQvK0eQwN7hXRH2KIdB1+vqoS630bxiBZcCf4vRyCWR81+hBbFfNsmLJai9uY\n");
        cer.append("IzAMDSDp3BuCklH1D2FmTwXIVYF/byWndmuGhepYl+Sv7uw1jAiWX4q8fgkyZJHX\n");
        cer.append("FQYloT0c6vzxK27PY41rYck9KH65/0A3/oWZ58hF6gCnLGhSlHhpU+tkXrRM6iwq\n");
        cer.append("bE7/mN5i2R+Wy9XAiNtZ/U+YECmHu65k37dv5LTR9R8vGAEsGR71p3EkrAIDupZl\n");
        cer.append("PXs6LGCWMtySfuB5Mj4dUPYF4HzNlXLt1T7RXRpr2L2mMsH5Hx6UBD3LcRPsFdxr\n");
        cer.append("1p6nuW/DSxjQ1j5QDAxIHmkjmA6Og/TRO0AbHfibAeAtYpUgJjrmDqQ0g1cZ3ZjN\n");
        cer.append("K47yGqQnZAm9/ERzRjvVxUR9v71H8pRxcxnXxrmoAPwBpixZfAv5IkBxP10qMmMF\n");
        cer.append("5fxKb55YrX/OWsZTajcU7TepV8G9KxGpui7eVkV3nPgooO2RXwsi+wGq6MvqPocv\n");
        cer.append("6MnMaR8Z1njoe8Tl13TTTHloqEd+1aCbSckpi5Gyg8e/UMrZKmqPxCrGWAAnNyC7\n");
        cer.append("IpnexnuXvwZYJedShGrINbvd8JBcvT38zbuaf90Fyoy54ywUeRJPz+yEdTX6g+86\n");
        cer.append("MRk=\n");
        cer.append("-----END CERTIFICATE-----");

        try {
            InputStream is = new ByteArrayInputStream(cer.toString().getBytes("UTF-8"));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.certificate = (X509Certificate) cf.generateCertificate(is);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        certificate.checkValidity();
    }

    @Override
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        certificate.checkValidity(date);
    }

    @Override
    public int getBasicConstraints() {
        return certificate.getBasicConstraints();
    }

    @Override
    public Principal getIssuerDN() {
        return certificate.getIssuerDN();
    }

    @Override
    public boolean[] getIssuerUniqueID() {
        return certificate.getIssuerUniqueID();
    }

    @Override
    public boolean[] getKeyUsage() {
        return certificate.getKeyUsage();
    }

    @Override
    public Date getNotAfter() {
        return certificate.getNotAfter();
    }

    @Override
    public Date getNotBefore() {
        return certificate.getNotBefore();
    }

    @Override
    public BigInteger getSerialNumber() {
        return certificate.getSerialNumber();
    }

    @Override
    public String getSigAlgName() {
        return certificate.getSigAlgName();
    }

    @Override
    public String getSigAlgOID() {
        return certificate.getSigAlgOID();
    }

    @Override
    public byte[] getSigAlgParams() {
        return certificate.getSigAlgParams();
    }

    @Override
    public byte[] getSignature() {
        return certificate.getSignature();
    }

    @Override
    public Principal getSubjectDN() {
        return certificate.getSubjectDN();
    }

    @Override
    public boolean[] getSubjectUniqueID() {
        return certificate.getSubjectUniqueID();
    }

    @Override
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return certificate.getTBSCertificate();
    }

    @Override
    public int getVersion() {
        return certificate.getVersion();
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return certificate.getEncoded();
    }

    @Override
    public PublicKey getPublicKey() {
        return certificate.getPublicKey();
    }

    @Override
    public String toString() {
        return certificate.toString();
    }

    @Override
    public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException,
            NoSuchProviderException, SignatureException {
        certificate.verify(key);
    }

    @Override
    public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException,
            InvalidKeyException, NoSuchProviderException, SignatureException {
        certificate.verify(key, sigProvider);
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        return certificate.getCriticalExtensionOIDs();
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return certificate.getExtensionValue(oid);
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        return certificate.getNonCriticalExtensionOIDs();
    }

    @Override
    public boolean hasUnsupportedCriticalExtension() {
        return certificate.hasUnsupportedCriticalExtension();
    }
}
