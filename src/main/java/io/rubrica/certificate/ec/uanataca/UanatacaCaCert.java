/*
 * Copyright (C) 2021 
 * Authors: Edison Lomas Almeida
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
package io.rubrica.certificate.ec.uanataca;

import io.rubrica.certificate.base.RubricaCertificate;

/**
 * Certificado raiz del Uanataca Ecuador, representado como un objeto
 * <code>X509Certificate</code>.
 *
 * @author Edison Lomas Almeida <elomas@appshandler.com>
 */
public class UanatacaCaCert extends RubricaCertificate {

    private static final StringBuilder stringBuilder;

    static {
        stringBuilder = new StringBuilder();
        stringBuilder.append("-----BEGIN CERTIFICATE-----\n");
        stringBuilder.append("MIIHAzCCBOugAwIBAgIITWOS6Y7X5ZQwDQYJKoZIhvcNAQELBQAwgbkxCzAJBgNV\n");
        stringBuilder.append("BAYTAkVTMUQwQgYDVQQHDDtCYXJjZWxvbmEgKHNlZSBjdXJyZW50IGFkZHJlc3Mg\n");
        stringBuilder.append("YXQgd3d3LnVhbmF0YWNhLmNvbS9hZGRyZXNzKTEWMBQGA1UECgwNVUFOQVRBQ0Eg\n");
        stringBuilder.append("Uy5BLjEVMBMGA1UECwwMVFNQLVVBTkFUQUNBMRswGQYDVQQDDBJVQU5BVEFDQSBS\n");
        stringBuilder.append("T09UIDIwMTYxGDAWBgNVBGEMD1ZBVEVTLUE2NjcyMTQ5OTAeFw0xNjAzMTEwOTEz\n");
        stringBuilder.append("NTNaFw00MTAzMTEwOTEzNTNaMIG5MQswCQYDVQQGEwJFUzFEMEIGA1UEBww7QmFy\n");
        stringBuilder.append("Y2Vsb25hIChzZWUgY3VycmVudCBhZGRyZXNzIGF0IHd3dy51YW5hdGFjYS5jb20v\n");
        stringBuilder.append("YWRkcmVzcykxFjAUBgNVBAoMDVVBTkFUQUNBIFMuQS4xFTATBgNVBAsMDFRTUC1V\n");
        stringBuilder.append("QU5BVEFDQTEbMBkGA1UEAwwSVUFOQVRBQ0EgUk9PVCAyMDE2MRgwFgYDVQRhDA9W\n");
        stringBuilder.append("QVRFUy1BNjY3MjE0OTkwggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQCa\n");
        stringBuilder.append("d5rtQey704cMzz7A1vuB4HLs0Y8YlH7BXKFAuxwzst1GOl7TzZzOeDEjGZMSjI00\n");
        stringBuilder.append("JRUdmZ/lmG927tES5dDlrfDrNvKu3mof9j6Wjch4HmNqT6I30TXnhBNbtKEYHWxC\n");
        stringBuilder.append("cIvQ0OKaFUUBEt+NzS6smyDAzbwyFUPSPid8JoGaGUMy7hhah38cLN408ffigCFT\n");
        stringBuilder.append("ehZIsRvDnsU1WU34vcAYLLmgjsvBNmq2V+Ts8+vtrLcRbpQ8usMbwJSO1aoi71Lu\n");
        stringBuilder.append("ISeBGJjqaszMPOty923PGHemImxHl5mHT13k5ha98EK4ZXMFfjxSVrypvpHgjThU\n");
        stringBuilder.append("V3s4ZeaSpSbWkFxl6Tl++OTciMLOp66jwZV3I4DqeRmNXJkiRebs5u8bDDZxxeSP\n");
        stringBuilder.append("RusoFI1cLm9cqCNy51hd2LNv8QECUNQ/RPon0sh+BSoSedppYXq6TFqpabE/FTnt\n");
        stringBuilder.append("JBU7CMJV3EFJ/jSvXf6qj7JjInUQXajSxDdt0WrmDW8aQCRKCZ0Ml/Iwb8yk83/y\n");
        stringBuilder.append("ZDt6E+Ez63V/x7sA2ZygG61zf4wOT95FNA4Z1atfOEcp/2uc5HXKrUTXTMDJJZfD\n");
        stringBuilder.append("WMo30Ae1Rei94TRd/9XRqPdEk0B/VL5/991SlEX6Ol0NwKRPm6HNNZoWbdnmLEc+\n");
        stringBuilder.append("CGnX1yj01R51Y4UTOalJ/W7oiNxmpZQAdAc9NN/gkwIDAQABo4IBCzCCAQcwHQYD\n");
        stringBuilder.append("VR0OBBYEFFUs8byhXrnuoC+IVxBb/Jb3kZosMA8GA1UdEwEB/wQFMAMBAf8wgaYG\n");
        stringBuilder.append("A1UdIASBnjCBmzCBmAYEVR0gADCBjzAzBggrBgEFBQcCARYnaHR0cDovL3d3dy51\n");
        stringBuilder.append("YW5hdGFjYS5jb20vcHVibGljL3BraS9kcGMvMFgGCCsGAQUFBwICMEwMSkNlcnRp\n");
        stringBuilder.append("ZmljYWRvIHJhw616IGRlIFVBTkFUQUNBLiBWZXIgaHR0cDovL3d3dy51YW5hdGFj\n");
        stringBuilder.append("YS5jb20vcHVibGljL3BraS9kcGMvMA4GA1UdDwEB/wQEAwIBBjAcBgNVHREEFTAT\n");
        stringBuilder.append("gRFpbmZvQHVhbmF0YWNhLmNvbTANBgkqhkiG9w0BAQsFAAOCAgEATAyOSKmK/yj6\n");
        stringBuilder.append("JFb/RaHMmor8knkwQWVi3lFASKyfLQc6FfHoVjEgihu6HekIlMS7WBzetQVomaTR\n");
        stringBuilder.append("TDu6eJeyo/+7CB+VGGHOYYjSdc8F8WI1HFN3f6ztKuM6zlVz3Xyj9BHhg1H4gqNL\n");
        stringBuilder.append("Yxe99kq14xQEOR/fm0p7rVgVeeHhG8m1S5UGyyJ1ukeiB0d0PqwVWlG1np+i/nhf\n");
        stringBuilder.append("nrxGSTnbRJyHzx6tuaLuQyHQU+Dg0TS8k65a8URioVkQ0CWb7yIyJ5bEBmPR2yqX\n");
        stringBuilder.append("Owt6nYR8/3blrU99+wp67pmQttSggX3sB2a9WfY94Y5uIPB7JisOUBmqH23RjakE\n");
        stringBuilder.append("c+UMLMjnvJQ82+1M7oGebnaVd1RVK+okemQ5zx57BzkzSl/i4G+Zxya8oQb2cIqF\n");
        stringBuilder.append("HnvyCVXDOd4/CWNBLZQCTyGRUKOocvu1kKXgmVY6hTQGhM8Tr5yg/XT21gaAv3/7\n");
        stringBuilder.append("th5ib2iGgq8Q8E3AW3ND+8N/qMjZ2aIkBKQYUFmLWiZt6n6ni73E2LQQEs+0uh9+\n");
        stringBuilder.append("1xTPcI7AfDv+p0m6HDP0pq0t7BX0DQbh5QwPpiHBkB8atzE5gmQxnkt4/g0S2av5\n");
        stringBuilder.append("Lc+U7ufZ5/ao7tLL1qkTX2r87jN7T8+lZOSHBbQan2QosyBfZWXgxaFYTspoy5tP\n");
        stringBuilder.append("n4RMcCgXqHSYlArUKaQ8OWmT42AKLdY=\n");
        stringBuilder.append("-----END CERTIFICATE-----\n");
    }

    public UanatacaCaCert() {
        super(stringBuilder);
    }

}
