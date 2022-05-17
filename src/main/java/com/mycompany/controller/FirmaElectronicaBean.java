/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyStoreException;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author chuga
 */
@ManagedBean
public class FirmaElectronicaBean {
    private static String Certificado;
    private static String Clave;
    private static UploadedFile uploadedFile;
    private static UploadedFile zip;
public  FirmaElectronicaBean(){
}

    public UploadedFile getZip() {
        return zip;
    }

    public void setZip(UploadedFile zip) {
        this.zip = zip;
    }

   

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }


public void Facturacion(){
    
    String Cert;

        System.out.println("Uploaded file name : " + uploadedFile.getFileName());

Cert=saveUploadedFile(System.getProperty("user.dir")+"/Facturas/Certificados",uploadedFile);
setCertificado(Cert);

}
    public String getCertificado() {
        return Certificado;
    }

    public void setCertificado(String Certificado) {
        this.Certificado = Certificado;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }
    
    public static String saveUploadedFile(String path, UploadedFile uploadedFile) {
  try {
    //First, Generate file to make directories
    String savedFileName = path + "/" + uploadedFile.getFileName();
    File fileToSave = new File(savedFileName);
    fileToSave.getParentFile().mkdirs();
    fileToSave.delete();
    //Generate path file to copy file
    Path folder = Paths.get(savedFileName);
    Path fileToSavePath = Files.createFile(folder);
    //Copy file to server
    InputStream input = uploadedFile.getInputStream();
    Files.copy(input, fileToSavePath, StandardCopyOption.REPLACE_EXISTING);
    return savedFileName;
  } catch (Exception e) {
    System.out.println(e);
  } finally {

  }
  return null;
}
   public void subirzip(){
      
       if(zip.getContentType().equals("application/x-zip-compressed")){
      saveUploadedFile(System.getProperty("user.dir")+"/Facturas/Generadas",zip);
       }else{
        return;
       }

} 
}
