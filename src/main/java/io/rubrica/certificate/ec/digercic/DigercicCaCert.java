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
 * Certificado raiz del Banco Central del Ecuador, representado como un objeto
 * <code>X509Certificate</code>.
 *
 * @author Ricardo Arguello <ricardo.arguello@soportelibre.com>
 */
public class DigercicCaCert extends X509Certificate {

    private X509Certificate certificate;

    public DigercicCaCert() {
        super();

        StringBuilder cer = new StringBuilder();
        cer.append("-----BEGIN CERTIFICATE-----\n");
        cer.append("MIIHYjCCBUqgAwIBAgIUMDAwMDAwMDAwMDAwMDAwMDAwMTgwDQYJKoZIhvcNAQEL\n");
        cer.append("BQAwggE/MQ4wDAYDVQQHEwVRdWl0bzESMBAGA1UECBMJUGljaGluY2hhMQswCQYD\n");
        cer.append("VQQGEwJFQzEuMCwGA1UECRMlQXYuIEFtYXpvbmFzIE4zNy02MSB5IE5hY2lvbmVz\n");
        cer.append("IFVuaWRhczEvMC0GA1UEAxMmQXV0b3JpZGFkIENlcnRpZmljYWRvcmEgZGUgbGEg\n");
        cer.append("RElHRVJDSUMxKzApBgNVBAsMIkNvb3JkaW5hY2nDs24gR2VuZXJhbCBkZSBTZXJ2\n");
        cer.append("aWNpb3MxTDBKBgNVBAoMQ0RpcmVjY2nDs24gR2VuZXJhbCBkZSBSZWdpc3RybyBD\n");
        cer.append("aXZpbCwgSWRlbnRpZmljYWNpw7NuIHkgQ2VkdWxhY2nDs24xMDAuBgkqhkiG9w0B\n");
        cer.append("CQEWIWluZm8tZmlybWFyY0ByZWdpc3Ryb2NpdmlsLmdvYi5lYzAeFw0yMTAyMDky\n");
        cer.append("MzA3MTlaFw0yMTA4MDkyMzA3MTlaMIGaMRcwFQYDVQQqEw5FZGdhciBTYW50aWFn\n");
        cer.append("bzEQMA4GA1UEBBMHQ29sdW1iYTFCMEAGA1UECRM5Q2FsbGUgQmVuamFtaW4gV2Fu\n");
        cer.append("ZGVtYmVyZyBOMTQtMTg5IHkgSm9yZ2UgQ2FycmVyYSBBbmRyYWRlMSkwJwYDVQQD\n");
        cer.append("EyBFZGdhciBTYW50aWFnbyBDb2x1bWJhIENhdHVjdWFnbzCCASIwDQYJKoZIhvcN\n");
        cer.append("AQEBBQADggEPADCCAQoCggEBAJTfXmIogCic3KlP4P6Ja1PUP6HKxRMF/V1N6Mgn\n");
        cer.append("H6onTeQnO/OOJfvmYNlArwPBflGOeuv/0qoVZqjjBV9aVKxB0yW0jTjPIZleVLFG\n");
        cer.append("2jG94MxsWhZuMQu/87eYqCmZMHFqIF+ny6m7hvp3Y2RV5xUOWORNl0pZt4o2ZhWX\n");
        cer.append("btsNb2seSxsgSfhs+lQKvT+ebBeu2WvHEdraj8JMnubG7xUOXJXTQz0vbf0UtkvU\n");
        cer.append("QHHfD9kUe9Q7vKawie+WvgTPmvgTIYWpYhbxSxtIBeYwsVwQ/VjlTwfgTKG1nLGu\n");
        cer.append("+eU3UGcGl6ON9cIwoOMFzWSvi2dvjmac8KUQIYFcgt4dJAECAwEAAaOCAfYwggHy\n");
        cer.append("MB4GDisGAQQBg7FfAQEBBQIBBAwTCjE3MTM0MDQ0NzEwHgYOKwYBBAGDsV8BAQEF\n");
        cer.append("AgIEDBMKMTcxMzQwNDQ3MTAdBg4rBgEEAYOxXwEBAQUCAwQLEwlDYXR1Y3VhZ28w\n");
        cer.append("IQYOKwYBBAGDsV8BAQEFAgQEDxMNMTcxMzQwNDQ3MTAwMTAfBgNVHSMEGDAWgBTT\n");
        cer.append("3FdYYmf8RIHo6IU0VPLMUmgqfDAdBgNVHQ4EFgQUpvvRULJ7OaqZrOszD/OJXHom\n");
        cer.append("2MEwRQYIKwYBBQUHAQEBAf8ENjA0MDIGCCsGAQUFBzABhiZodHRwOi8vb2NzcC5y\n");
        cer.append("ZWdpc3Ryb2NpdmlsLmdvYi5lYzo4MDgyLzA7BgNVHR8ENDAyMDCgLqAshipodHRw\n");
        cer.append("czovL2Zpcm1hLnJlZ2lzdHJvY2l2aWwuZ29iLmVjL2NybC5jcmwwWgYDVR0gAQH/\n");
        cer.append("BFAwTjBMBg4rBgEEAYOxXwEBAQUDAjA6MDgGCCsGAQUFBwIBFixodHRwczovL2Zp\n");
        cer.append("cm1hLnJlZ2lzdHJvY2l2aWwuZ29iLmVjL3BvbGl0aWNhczAdBgNVHSUEFjAUBggr\n");
        cer.append("BgEFBQcDBAYIKwYBBQUHAwIwDAYDVR0TAQH/BAIwADAOBgNVHQ8BAf8EBAMCA/gw\n");
        cer.append("EQYJYIZIAYb4QgEBBAQDAgWgMA0GCSqGSIb3DQEBCwUAA4ICAQAWZ8b8gdQazpCi\n");
        cer.append("SoOA0fvxMlZqHeH+39Jk7f4ytQ4iaoYlkqws5O7sBz06h4VAQugt61KKRM5NX1pi\n");
        cer.append("jFpZrjso+MXOXk/8LxO9l34n004m5c/AWopQN+KLPGF5nnc0bwBYMor2d8YqzWMO\n");
        cer.append("+jckaH1LMGCr0Ne8zPV2uN1ah0lYd5/iM906u4af+zHPfl/BmYM4J8XKEEcLfmx3\n");
        cer.append("tJcVm6EyvsVpsk/hSye8HCyGieAGUoHEhoM1AtbB3f0qUGILOMr4hjWQBuEKbL2U\n");
        cer.append("+s/a10s0O8fciYuXZp87fhspSg7ML1IKAabAm++I3Ls2p1KQweHxhAJ8ja6VbS5o\n");
        cer.append("xFN2ASXqH9Pwip/wKqqx2zPmkzSn99UivmT+79mU2PX4HGxxQj+tOrABEuXA6YfS\n");
        cer.append("i24N5J78ds6TI2i1hJ2c9mQWloVOZwfyQssd+zDjso48IjLWD8KmKk6DgX8aOGp4\n");
        cer.append("4uIcqPleJ6Tt15u2bwQQxAkf1e6rdjuq6eKnpqalC8SLA0ATZe1Dc2O3DUsv+hju\n");
        cer.append("XFAgqUFTJu2qt0Z8adNmlcY3z+60aBq/TyQaPF9//mnQ+uo/RFJI6GorTErH9j2V\n");
        cer.append("MfTdtfzHL+tqZsDMNWXhAadO84I0+A2G7C0K4GwzSJlrgAPdozFIVk7wS6eFy15+\n");
        cer.append("qevWJmd0+4htjWHKBt/VM7H7M29i7w==\n");
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
