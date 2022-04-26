/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;
import autorizacion.ws.sri.gob.ec.Autorizacion;
import autorizacion.ws.sri.gob.ec.AutorizacionComprobantesOfflineService;
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
import javax.xml.ws.WebServiceRef;
import recepcion.ws.sri.gob.ec.RecepcionComprobantesOfflineService;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
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
private String PathFacturas= "C:\\Users\\chuga\\OneDrive\\Documentos\\Facturas\\"; 
  
    public void enviar() throws IOException{
        
        String[] facturas=listarXMLs("\\OneDrive\\Documentos\\Facturas\\Generadas");
        System.out.println("Archivos a enviar");
        
         for (int i=0; i< facturas.length; i++) {
        System.out.println(facturas[i]);
      }
         
     
         for (int i=0; i< facturas.length; i++) {
        File path = new File(PathFacturas+"Generadas\\"+facturas[i]);

        try { // Call Web Service Operation
            recepcion.ws.sri.gob.ec.RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
            // TODO initialize WS operation arguments here
            
            byte[] xml = new byte[9999];
           xml= XMLaByte(path);
           System.out.println(xml);
            // TODO process result here
            recepcion.ws.sri.gob.ec.RespuestaSolicitud result = port.validarComprobante(xml);
            
            
            
            System.out.println(facturas[i]+" = "+result.getEstado());
            if(result.getEstado().equals("RECIBIDA")){
                File file = new File(PathFacturas+"Generadas\\"+facturas[i]);
		String targetDirectory = PathFacturas+"Aceptadas\\";
 
		if (file.renameTo(new File(targetDirectory+ file.getName()))) {
			System.out.println("El archivo se a movido a: " + targetDirectory);
		} else {
			System.out.println("Fallo al mover el archivo");
		}
            
            }else
            {
                
                    File file = new File(PathFacturas+"Generadas\\"+facturas[i]);
		String targetDirectory = PathFacturas+"Rechazadas\\";
 
		if (file.renameTo(new File(targetDirectory+ file.getName()))) {
			System.out.println("El Archivo se ha movido a: " + targetDirectory);
		} else {
			System.out.println("Fallo al mover el archivo");
		}
            
                
            }
            
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
             System.out.println(ex);
        }

         }
  
FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/Facturacion/");
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
    public  String[] listarXMLs(String Ubicacion){
     
        
    //Carpeta del usuario "\\OneDrive\\Documentos\\NetBeansProjects\\Facturacion\\Facturacion\\src\\main\\resources\\Facturas\\Generadas"
    String sCarpAct = System.getProperty("user.home")+ Ubicacion;
    //System.out.println("Carpeta del usuario = " + sCarpAct);

    //Listemos todas las carpetas y archivos de la carpeta actual
    System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

    File carpeta = new File(sCarpAct);
    String[] listado = carpeta.list();
    if (listado == null || listado.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return null;
    }
    else {
      for (int i=0; i< listado.length; i++) {
        System.out.println(listado[i]);
      }
    }
return listado;/*
    //Lo mismo que lo anterior pero con objetos File para poder ver sus propiedades
    System.out.println(ANSI_RED + "//// LISTADO CON OBJETOS File" + ANSI_RESET);

    File[] archivos = carpeta.listFiles();
    if (archivos == null || archivos.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return;
    }
    else {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      for (int i=0; i< archivos.length; i++) {
        File archivo = archivos[i];
        System.out.println(String.format("%s (%s) - %d - %s", 
                                          archivo.getName(), 
                                          archivo.isDirectory() ? "Carpeta" : "Archivo",
                                          archivo.length(),
                                          sdf.format(archivo.lastModified())
                                          ));
      }
    }

    //Se pueden filtrar los resultados tanto usando list() como usando listFiles()
    //Por ejemplo, en este segundo caso para mostrar solo archivos y no carpetas
    System.out.println(ANSI_RED + "//// LISTADO CON FILTRO APLICADO - SOLO ARCHIVOS" + ANSI_RESET);

    FileFilter filtro = new FileFilter() {
      @Override
      public boolean accept(File arch) {
        return arch.isFile();
      }
    };

    archivos = carpeta.listFiles(filtro);

    if (archivos == null || archivos.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return;
    }
    else {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      for (int i=0; i< archivos.length; i++) {
        File archivo = archivos[i];
        System.out.println(String.format("%s (%s) - %d - %s", 
                                          archivo.getName(), 
                                          archivo.isDirectory() ? "Carpeta" : "Archivo",
                                          archivo.length(),
                                          sdf.format(archivo.lastModified())
                                          ));
      }
    }

  
  */
    }
    
      public  Map listarXMLsyEstado(String Ubicacion){
     Map FacturayEstado = new HashMap();
     String Estado;
        
    //Carpeta del usuario "\\OneDrive\\Documentos\\NetBeansProjects\\Facturacion\\Facturacion\\src\\main\\resources\\Facturas\\Generadas"
    String sCarpAct = System.getProperty("user.home")+ Ubicacion;
    //System.out.println("Carpeta del usuario = " + sCarpAct);

    //Listemos todas las carpetas y archivos de la carpeta actual
    System.out.println(ANSI_RED + "//// LISTADO SIMPLE" + ANSI_RESET);

    File carpeta = new File(sCarpAct);
    String[] listado = carpeta.list();
    if (listado == null || listado.length == 0) {
      System.out.println("No hay elementos dentro de la carpeta actual");
      return null;
    }
    else {
      for (int i=0; i< listado.length; i++) {
        System.out.println(listado[i]);
      }
    }
    //Obtener ESTADO 
     for (int i=0; i< listado.length; i++) {
     try {
            File archivo = new File(PathFacturas+"Aceptadas\\"+listado[i]);
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
                    System.out.println("Clave de Acceso: " + element.getElementsByTagName("claveAcceso").item(0).getTextContent());
                    
                    
                    try { // Call Web Service Operation
                        autorizacion.ws.sri.gob.ec.AutorizacionComprobantesOffline port = service_1.getAutorizacionComprobantesOfflinePort();
                        // TODO initialize WS operation arguments here
                        java.lang.String claveAccesoComprobante = element.getElementsByTagName("claveAcceso").item(0).getTextContent();
                        // TODO process result here
                        autorizacion.ws.sri.gob.ec.RespuestaComprobante result = port.autorizacionComprobante(claveAccesoComprobante);
                        System.out.println("Result = "+port.autorizacionComprobante(claveAccesoComprobante).getAutorizaciones().getAutorizacion().isEmpty());
                        
                           for (Autorizacion item : result.getAutorizaciones().getAutorizacion()) {
          System.out.println("Este es el ultimo "+item.getEstado());
        
          } 
                       
                  Autorizacion aut =new Autorizacion();
                   System.out.println("Result  Estado= "+aut.getEstado());
                        FacturayEstado.put(listado[i],result);
                        
                    } catch (Exception ex) {
                        System.out.println("ERROR >>>>"+ ex);
                        FacturayEstado.put(listado[i],"Entro a excepcion");
                    }
                    
                    
                    
                   

            

                    
                    
                   


                    
                  
                    
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    //fIN oBTENER ESTADO
    System.out.println("INFORMACION PARA LA VISTA "+ FacturayEstado);
return FacturayEstado;
    }
    
    
    
}
