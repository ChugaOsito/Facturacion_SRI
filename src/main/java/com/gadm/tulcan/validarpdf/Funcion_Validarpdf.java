/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadm.tulcan.validarpdf;

import com.gadm.tulcan.rest.certificados.certificados;
import com.gadm.tulcan.rest.modelo.EntradasFirmarpdf;
import com.gadm.tulcan.rest.modelo.SalidasFirmarpdf;
import io.rubrica.certificate.CertEcUtils;
import static io.rubrica.certificate.CertUtils.seleccionarAlias;
import io.rubrica.certificate.to.Certificado;
import io.rubrica.certificate.to.Documento;
import io.rubrica.exceptions.InvalidFormatException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import io.rubrica.exceptions.SignatureVerificationException;
import io.rubrica.keystore.FileKeyStoreProvider;
import io.rubrica.keystore.KeyStoreProvider;
import io.rubrica.keystore.KeyStoreProviderFactory;
import io.rubrica.sign.SignConstants;
import io.rubrica.sign.Signer;
import io.rubrica.sign.pdf.PDFSigner;
import io.rubrica.sign.pdf.PdfUtil;
import io.rubrica.utils.FileUtils;
import io.rubrica.utils.Json;
import io.rubrica.utils.TiempoUtils;
import io.rubrica.utils.Utils;
import io.rubrica.utils.UtilsCrlOcsp;
import io.rubrica.utils.X509CertificateUtils;
import io.rubrica.validaciones.DocumentoUtils;
import java.io.File;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
public class Funcion_Validarpdf {
       // ARCHIVO
//    private static final String ARCHIVO = "/home/mfernandez/prueba.p12";
//    private static final String PASSWORD = "11111111";
   // private static  String ARCHIVO;
//    private static final String ARCHIVO = "C:\\Users\\desarrollo\\Downloads\\prueba.p12";
    //private static  String PASSWORD;
//    private static final String ARCHIVO = "C:\\Users\\desarrollo\\Documents\\Digercic\\Edgar_Columba.pfx";
//    private static final String PASSWORD = "T35t_3cu4d0r.2021";
//    private static final String FILE = "C:\\Users\\desarrollo\\Downloads\\documento_blanco-signed.pdf";
//    private static final String FILE = "/home/mfernandez/documento_blanco-signed.pdf";
    private static  String FILE ;
//    private static final String FILE = "/home/mfernandez/Descargas/test/mozilla.pdf.p7m";
      public Boolean Invocador(String Documento)throws KeyStoreException, Exception {
        
          
FILE = Documento; 


          verificarDocumento(FILE);
      return true;
      }
    

    


    private static void verificarDocumento(String file) throws IOException, SignatureVerificationException, Exception {
        certificados firma = new certificados();
        File document = new File(file);
        Documento documento = Utils.verificarDocumento(document);
        System.out.println("JSON:");
        System.out.println(Json.GenerarJsonDocumento(documento));
        System.out.println("Documento: " + documento);
        if (documento.getCertificados() != null) {
            documento.getCertificados().forEach((certificado) -> {
                         SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
                         
                firma.addSalida(certificado.getDatosUsuario().getCedula(), certificado.getIssuedTo(), certificado.getDatosUsuario().getInstitucion()
                        , certificado.getDatosUsuario().getCargo(), 
                       isoDate.format(certificado.getValidFrom().getTime()), isoDate.format(certificado.getValidTo().getTime()), isoDate.format(certificado.getGenerated().getTime()),certificado.getDatosUsuario().isCertificadoDigitalValido());
                System.out.println(certificado.toString());
            });
        } else {
            throw new InvalidFormatException("Documento no soportado");
        }
    }

    //pruebas de fecha-hora
    private static void fechaHora(int segundos) throws KeyStoreException, Exception {
        tiempo(segundos);//espera en segundos
        do {
            try {
                System.out.println("getFechaHora() " + TiempoUtils.getFechaHora());
                System.out.println("getFechaHoraServidor() " + TiempoUtils.getFechaHoraServidor());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } while (tiempo);
        System.exit(0);
    }

    private static boolean tiempo = true;

    private static void tiempo(int segundos) {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                tiempo = false;
            }
        }, segundos * 1000); //espera 3 segundos
    }
    //pruebas de fecha-hora

}