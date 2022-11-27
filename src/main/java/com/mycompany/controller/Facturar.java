/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;
import autorizacion.ws.sri.gob.ec.Autorizacion;
import autorizacion.ws.sri.gob.ec.AutorizacionComprobantesOfflineService;
import dyto.util.CorreoElectronico;

import java.text.SimpleDateFormat;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import static java.nio.file.StandardCopyOption.*;
import javax.xml.ws.WebServiceRef;
import recepcion.ws.sri.gob.ec.RecepcionComprobantesOfflineService;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStoreException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sri.XAdESBESSignature;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import static org.bouncycastle.asn1.cms.CMSAttributes.contentType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.shaded.commons.io.FilenameUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author chuga
 */
@Named
@ViewScoped

public class Facturar implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline.wsdl")
    private AutorizacionComprobantesOfflineService service_1;

  
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline.wsdl")
    private RecepcionComprobantesOfflineService service;
     public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  private String pagina_principal= "http://localhost:8080/Facturacion_SRI/";
private String PathFacturas= System.getProperty("user.dir")+"/Facturas/"; 
private StreamedContent ComprobanteADescargar;
//Inicio pruebas envioCorreo
//Las siguientes lineas de codigo solo se usaron para demostracion de metodo envioCorreo BORRAR de ser necesario
private String Path;
private String correoDestinatario;
private Boolean envioCorreoExitoso;

    public Boolean getEnvioCorreoExitoso() {
        return envioCorreoExitoso;
    }

    public void setEnvioCorreoExitoso(Boolean envioCorreoExitoso) {
        this.envioCorreoExitoso = envioCorreoExitoso;
    }


    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }
    public void envioXMLporCorreo(){
         List<String> Paths=new ArrayList<>();
        CorreoElectronico cr=new CorreoElectronico();
        Paths.add(Path);
        envioCorreoExitoso= cr.envioCorreo(correoDestinatario, Paths);    
    }
//Fin pruebas metodo envioCorreo


 
    public StreamedContent getComprobanteADescargar() {
        return ComprobanteADescargar;
    }

    public void setComprobanteADescargar(StreamedContent ComprobanteADescargar) {
        this.ComprobanteADescargar = ComprobanteADescargar;
    }


  
    public synchronized void enviar() throws IOException,KeyStoreException, Exception{
        System.out.println("UBICACION USER DIR==="+System.getProperty("user.dir"));
        FirmaElectronicaBean datos =new FirmaElectronicaBean ();
        String[] comprimidos=listarXMLs("/Facturas/Generadas");
         for (int i=0; i< comprimidos.length; i++) {
         Descomprimir(PathFacturas+"Generadas/"+comprimidos[i],PathFacturas+"Generadas/");
         }
        //Inicio firma electronica/
        String[] facturas=listarXMLs("/Facturas/Generadas");
         datos.Facturacion();
        
        for (int i=0; i< facturas.length; i++) {
           if(FilenameUtils.getExtension(PathFacturas+"Generadas/"+facturas[i]).equals("xml")) {
            //Inicio Fimra XADESBESS
         try{  
        XAdESBESSignature.firmar(PathFacturas+"Generadas/"+facturas[i], datos.getCertificado(), datos.getClave(), PathFacturas+"Generadas/","firmado-"+facturas[i] );
         }
         catch(IOException e){
        System.out.println("Error: " + e);
        StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionContrasenaIncorrecta = sw.toString().substring(0,59);
            String exceptionErrorParsear = sw.toString().substring(0,50);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+exceptionErrorParsear);
        if(exceptionContrasenaIncorrecta.equals("java.io.IOException: Error: keystore password was incorrect")){
            BorrarArchivo(datos.getCertificado());
          FacesContext.getCurrentInstance().getExternalContext().redirect(pagina_principal);
            return;
        }else{
         CopiarArchivo(PathFacturas+"Generadas/"+facturas[i], PathFacturas+"Generadas/SinFirmar-"+facturas[i]);
        }
        
        /*
          if(exceptionErrorParsear.equals("java.io.IOException: Error al parsear el documento")){
             MoverArchivo(PathFacturas+"Generadas/"+facturas[i],PathFacturas+"Rechazadas/");
        }*/
      
    
        }
         //Fin Fimra XADESBESS
           
           }
     
       
        }      
        BorrarArchivo(datos.getCertificado());
        for (int i=0; i< facturas.length; i++) {
         MoverArchivo(PathFacturas+"Generadas/"+facturas[i],PathFacturas+"Sin Firmar/");
        }
        
//Fin firma electronica 
       
        System.out.println("Archivos a enviar");
        facturas=listarXMLs("/Facturas/Generadas");
         for (int i=0; i< facturas.length; i++) {
        System.out.println(facturas[i]);
      }
         
     
         for (int i=0; i< facturas.length; i++) {
        File path = new File(PathFacturas+"Generadas/"+facturas[i]);

        try { // Call Web Service Operation
            recepcion.ws.sri.gob.ec.RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
           
            
            byte[] xml = new byte[9999];
           xml= XMLaByte(path);
           System.out.println(xml);
            // TODO process result here
            recepcion.ws.sri.gob.ec.RespuestaSolicitud result = port.validarComprobante(xml);
            
            
            
            System.out.println(facturas[i]+" = "+result.getEstado() );
           if(result.getEstado() != null) {
            if(result.getEstado().equals("RECIBIDA")){
                MoverArchivo(PathFacturas+"Generadas/"+facturas[i],PathFacturas+"Aceptadas/");            
            }else
            {
                  MoverArchivo(PathFacturas+"Generadas/"+facturas[i],PathFacturas+"Rechazadas/");             
            }
           }
else
{
    MoverArchivo(PathFacturas+"Generadas/"+facturas[i],PathFacturas+"Rechazadas/");  
}
           
            BorrarArchivo(datos.getCertificado());
            
            
        } catch (Exception ex) {
             System.out.println(ex);
             BorrarArchivo(datos.getCertificado());
        }

         }
  
FacesContext.getCurrentInstance().getExternalContext().redirect(pagina_principal);
    }
    
    public static byte[] XMLaByte(File file)
        throws IOException
    {
  
        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(file);
  
        // Now creating byte array of same length as file
        byte[] arr = new byte[(int)file.length()];
  
        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);
  
        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();
  
        // Returning above byte array
        return arr;
    }
    
    public  Map listarXMLsRechazados(String Ubicacion) throws IOException{
        Map FacturayEstado = new HashMap();
        String[] facturas=listarXMLs(Ubicacion);
                
     
         for (int i=0; i< facturas.length; i++) {
        File path = new File(PathFacturas+"Rechazadas/"+facturas[i]);

     
            
            byte[] comprobante = new byte[9999];
           comprobante= XMLaByte(path);
        
         String xml=" <soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ec=\"http://ec.gob.sri.ws.recepcion\">\n" +
"        <soapenv:Header/>\n" +
"        <soapenv:Body>\n" +
"        <ec:validarComprobante>\n" +
"      <xml>\n" +
Base64.getEncoder().encodeToString(comprobante)+
"    </xml>\n" +
"        </ec:validarComprobante>\n" +
"        </soapenv:Body>\n" +
"        </soapenv:Envelope>";
         System.out.println(xml);
            String responseF=SoapRequest(xml, "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl");
            System.out.println(responseF);
              responseF= responseF.substring(responseF.indexOf("</identificador><mensaje>")+25,responseF.indexOf("</mensaje>")); 
            FacturayEstado.put(facturas[i], responseF);
          
    
     
     }
      return FacturayEstado;
    }
    public  String[] listarXMLs(String Ubicacion){
     
            System.out.println("UBICACION USER DIR==="+System.getProperty("user.dir"));
                System.out.println("UBICACION USER HOME==="+System.getProperty("user.home"));
    //Carpeta del usuario "\\OneDrive\\Documentos\\NetBeansProjects\\Facturacion\\Facturacion\\src\\main\\resources\\Facturas\\Generadas"
    String sCarpAct = System.getProperty("user.dir")+ Ubicacion;
    //System.out.println("Carpeta del usuario = " + sCarpAct);

    //Listemos todas las carpetas y archivos de la carpeta actual
    System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

    File carpeta = new File(sCarpAct);
    String[] listado = carpeta.list();
    if (listado == null || listado.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return listado;
    }
    else {
      for (int i=0; i< listado.length; i++) {
        System.out.println(listado[i]);
      }
    }
return listado;
    }
    
    
    public  Map listarXMLsyEstado(String Ubicacion){
        Map FacturayEstado = new HashMap();
        String Estado;
        String ClaveAcceso="";
        //Carpeta del usuario "\\OneDrive\\Documentos\\NetBeansProjects\\Facturacion\\Facturacion\\src\\main\\resources\\Facturas\\Generadas"
        String sCarpAct = System.getProperty("user.dir")+ Ubicacion;
        //System.out.println("Carpeta del usuario = " + sCarpAct);

        //Listemos todas las carpetas y archivos de la carpeta actual
        System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

        File carpeta = new File(sCarpAct);
        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            return FacturayEstado;
        }
        else {
            for (int i=0; i< listado.length; i++) {
                System.out.println(listado[i]);
            }
        }
        //Obtener ESTADO 
        for (int i=0; i< listado.length; i++) {
            try {
                File archivoXML = new File(PathFacturas+"Aceptadas/"+listado[i]);
                ClaveAcceso=obtenerClaveAcceso(archivoXML);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FacturayEstado.put(listado[i],obtenerEstado(ClaveAcceso));   
        }
        //fIN oBTENER ESTADO
        System.out.println("INFORMACION PARA LA VISTA "+ FacturayEstado);
        return FacturayEstado;
    }
      
    public synchronized void MoverArchivo(String Archivo, String RutaDestino){
     File file = new File(Archivo);
		String targetDirectory = RutaDestino;
 
		if (file.renameTo(new File(targetDirectory+ file.getName()))) {
			System.out.println("El archivo se a movido a: " + targetDirectory);
		} else {
			System.out.println("Fallo al mover el archivo");
		}
    } 
    public synchronized void CopiarArchivo(String Archivo, String DestinoyNombre){
    String  sourcePath = Archivo;   // source file path
        String destinationPath=DestinoyNombre;  // destination file path
        File sourceFile = new File(sourcePath);        // Creating A Source File
        File destinationFile = new File(destinationPath);   //Creating A Destination File. Name stays the same this way, referring to getName()
        try 
        {
        Files.copy(sourceFile.toPath(), destinationFile.toPath(),REPLACE_EXISTING);  
          // Static Methods To Copy Copy source path to destination path
        } catch(Exception e)
        {
             System.out.println(e);  // printing in case of error.
        }
    }
    public synchronized void BorrarArchivo(String Archivo){
        try{
            File fichero = new File(Archivo);
            fichero.delete();
   System.out.println("El fichero ha sido borrado satisfactoriamente");
        } catch (Exception e) {
       System.out.println("El fichero no puede ser borrado");
    System.out.println(e);
        }
    
    

    }
    
    
    
    
     public synchronized void Descomprimir(String archivoZip, String rutaSalida) {
        byte[] buffer = new byte[1024];
        try {
            File folder = new File(rutaSalida);
            if (!folder.exists()) {
                folder.mkdir();
            }
            ZipInputStream zis = new ZipInputStream(new FileInputStream(archivoZip));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String nombreArchivo = ze.getName();
                File archivoNuevo = new File(rutaSalida + File.separator + nombreArchivo);
                System.out.println("archivo descomprimido : " + archivoNuevo.getAbsoluteFile());
                new File(archivoNuevo.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(archivoNuevo);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("Listo");
        } catch (IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
            
        }
    }
     
     public synchronized void descargarTodo() throws FileNotFoundException, IOException{
         File f = new File(PathFacturas+"temp/test.zip");
         ZipOutputStream salida = new ZipOutputStream(new FileOutputStream(f));
          String sCarpAct =PathFacturas+"Aceptadas/";
        //System.out.println("Carpeta del usuario = " + sCarpAct);

        //Listemos todas las carpetas y archivos de la carpeta actual
        System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

        File carpeta = new File(sCarpAct);
        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            
        }
        else {
            for (int i=0; i< listado.length; i++) {
                System.out.println(listado[i]);
            }
        }
        //Obtener ESTADO 
        for (int i=0; i< listado.length; i++) {
            
            try {
                
                File archivoXML = new File(PathFacturas+"Aceptadas/"+listado[i]);
                if(obtenerEstado(obtenerClaveAcceso(archivoXML)).equals("AUTORIZADO")){
                 StringBuilder sb = new StringBuilder();
                sb.append(obtenerXMLAutorizado(obtenerClaveAcceso(archivoXML)));
                
                
                ZipEntry e = new ZipEntry(listado[i]);
                salida.putNextEntry(e);
                byte[] data = sb.toString().getBytes();
                salida.write(data, 0, data.length);
                salida.closeEntry();
               
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
              
        }
         salida.close();
        
       
    
        

    
    
    download(f);  
     }
   
     
     public synchronized void descargarUno(String comprobante) throws IOException {
    final File file = new File(PathFacturas+"Aceptadas/"+comprobante);
    
        StringBuilder sb = new StringBuilder();
    sb.append(obtenerXMLAutorizado(obtenerClaveAcceso(file)));

    File f = new File(PathFacturas+"temp/test.zip");
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
    ZipEntry e = new ZipEntry(comprobante);
    out.putNextEntry(e);

    byte[] data = sb.toString().getBytes();
    out.write(data, 0, data.length);
    out.closeEntry();

    out.close();
    
    download(f);    
}
     
      public synchronized void download(File file) throws IOException {
          final FacesContext fc = FacesContext.getCurrentInstance();
    final ExternalContext externalContext = fc.getExternalContext();
           externalContext.responseReset();
    externalContext.setResponseContentType("text/xml");
    //externalContext.setResponseContentLength(Long.valueOf(file.lastModified()).intValue());
    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=" + file.getName());

    final HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

    FileInputStream input = new FileInputStream(file);
    byte[] buffer = new byte[1024];
    final ServletOutputStream out = response.getOutputStream();

    while ((input.read(buffer)) != -1) {
        out.write(buffer);
    }

    out.flush();
    fc.responseComplete();
    }
     
     
        static String SoapRequest(String soapRequest, String url) {
    try {
     //String url = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl"; // replace your URL here
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     
     // change these values as per soapui request on top left of request, click on RAW, you will find all the headers
     con.setRequestMethod("POST");
     con.setRequestProperty("Content-Type","text/xml; charset=utf-8"); 
     con.setDoOutput(true);
     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
     wr.writeBytes(soapRequest);
     wr.flush();
     wr.close();
     String responseStatus = con.getResponseMessage();
     System.out.println(responseStatus);
     BufferedReader in = new BufferedReader(new InputStreamReader(
     con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
     }
     in.close();
     
     // You can play with response which is available as string now:
     String finalvalue= response.toString();
     
     // or you can parse/substring the required tag from response as below based your response code
    
     
     
     return finalvalue;
     } 
    catch (Exception e) {
        return e.getMessage();
    }
    
      }
        
     
  public synchronized static void compress(String dirPath) {
        final Path sourceDir = Paths.get(dirPath);
        String zipFileName = dirPath.concat("Comprobantes.zip");
        try {
            final ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    try {
                        Path targetFile = sourceDir.relativize(file);
                        outputStream.putNextEntry(new ZipEntry(targetFile.toString()));
                        byte[] bytes = Files.readAllBytes(file);
                        outputStream.write(bytes, 0, bytes.length);
                        outputStream.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
  public String obtenerClaveAcceso(File archivo){
    String clv="";
    try {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        document.getDocumentElement().normalize();
        System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
        NodeList listaEmpleados = document.getElementsByTagName("infoTributaria");
        for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
            Node nodo = listaEmpleados.item(temp);
            System.out.println("Elemento:" + nodo.getNodeName());
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                // System.out.println("id: " + element.getAttribute("id"));

                clv=element.getElementsByTagName("claveAcceso").item(0).getTextContent();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    String ClaveAcceso=clv;
    System.out.println("La clave a consultar es: "+clv);
    return clv;
  }
  public String obtenerEstado(String ClaveAcceso){
    String estado="";
    String xml=" <soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ec=\"http://ec.gob.sri.ws.autorizacion\">\n" +
    "        <soapenv:Header/>\n" +
    "        <soapenv:Body>\n" +
    "        <ec:autorizacionComprobante>\n" +
    "      <claveAccesoComprobante>"+ClaveAcceso+"</claveAccesoComprobante>\n" +
    "        </ec:autorizacionComprobante>\n" +
    "        </soapenv:Body>\n" +
    "        </soapenv:Envelope>";
    try{
        String responseF=SoapRequest(xml, "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
        if(responseF.substring(responseF.indexOf("<estado>")+8,responseF.indexOf("</estado>")).equals("AUTORIZADO")){
            responseF= responseF.substring(responseF.indexOf("<estado>")+8,responseF.indexOf("</estado>"));     
        }else{
            responseF= responseF.substring(responseF.indexOf("<estado>")+8,responseF.indexOf("</estado>"))+": "
            +responseF.substring(responseF.indexOf("</identificador><mensaje>")+25,responseF.indexOf("</mensaje>"));     
        }
        estado= responseF;
    }catch (Exception e) {
        estado= "No fue posible conectarse con el servidor del SRI"; 
    }
    return estado;
  }
  public String obtenerXMLAutorizado(String claveAcceso){
  String responseF="";
   String xml=" <soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ec=\"http://ec.gob.sri.ws.autorizacion\">\n" +
    "        <soapenv:Header/>\n" +
    "        <soapenv:Body>\n" +
    "        <ec:autorizacionComprobante>\n" +
    "      <claveAccesoComprobante>"+claveAcceso+"</claveAccesoComprobante>\n" +
    "        </ec:autorizacionComprobante>\n" +
    "        </soapenv:Body>\n" +
    "        </soapenv:Envelope>";
    try{
        responseF=SoapRequest(xml, "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
        responseF=responseF.substring(responseF.indexOf("<RespuestaAutorizacionComprobante>"),responseF.indexOf("</RespuestaAutorizacionComprobante>")+35);
        responseF=StringEscapeUtils.unescapeHtml4(responseF); 
       responseF=responseF.replace("        ", "\n");
        responseF=responseF.replaceFirst(">", ">\n");
        }catch (Exception e) {
        responseF= "No fue posible obtener el XML"; 
    }
    
  return responseF;
  }
  public boolean estadoWebServiceSRIPruebas(){
    boolean estado= false;
    String urlAutorizacion="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl";
    String urlRecepcion="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl";
    String responseAutorizacion="";
    String responseRecepcion="";
    try{
        responseAutorizacion=GETresponse(urlAutorizacion);
        responseRecepcion=GETresponse(urlRecepcion);  
        responseAutorizacion=responseAutorizacion.substring(responseAutorizacion.indexOf("<soap:address location=")+79,responseAutorizacion.indexOf("<soap:address location=")+110);
        responseRecepcion=responseRecepcion.substring(responseRecepcion.indexOf("<soap:address location=")+79,responseRecepcion.indexOf("<soap:address location=")+107);
    }catch(Exception e){
        return estado;
    }

    if((responseAutorizacion.equals("AutorizacionComprobantesOffline")) && (responseRecepcion.equals("RecepcionComprobantesOffline"))){
        estado=true;
    }     
    return estado;
  }
  
    public boolean estadoWebServiceSRIProduccion(){
    boolean estado= false;
    String urlAutorizacion="https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl";
    String urlRecepcion="https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl";
    String responseAutorizacion="";
    String responseRecepcion="";
    try{
        responseAutorizacion=GETresponse(urlAutorizacion);
        responseRecepcion=GETresponse(urlRecepcion);  
        responseAutorizacion=responseAutorizacion.substring(responseAutorizacion.indexOf("<soap:address location=")+76,responseAutorizacion.indexOf("<soap:address location=")+107);
        responseRecepcion=responseRecepcion.substring(responseRecepcion.indexOf("<soap:address location=")+76,responseRecepcion.indexOf("<soap:address location=")+104);
    }catch(Exception e){
        return estado;
    }

    if((responseAutorizacion.equals("AutorizacionComprobantesOffline")) && (responseRecepcion.equals("RecepcionComprobantesOffline"))){
        estado=true;
    }     
    return estado;
  }
  
  public  String GETresponse(String URL){
      String finalvalue="";
  try{
        //String url = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl"; // replace your URL here
     URL obj = new URL(URL);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     
     // change these values as per soapui request on top left of request, click on RAW, you will find all the headers
     con.setRequestMethod("GET");
     con.setRequestProperty("Content-Type","text/xml; charset=utf-8"); 
     con.setDoOutput(true);
     String responseStatus = con.getResponseMessage();
     BufferedReader in = new BufferedReader(new InputStreamReader(
     con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
     }
     in.close();
     finalvalue= response.toString();
        }catch (Exception e) {
            System.out.println(e);
            finalvalue="";
    }
  return finalvalue;
  }
    
}
