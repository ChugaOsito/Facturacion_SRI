
package com.gadm.tulcan.rest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public  class SalidasFirmarpdf {
    private  static String DocFirmado;
    private  static String DocOriginal;
    
    public  SalidasFirmarpdf(){
    
    }

    public String getDocFirmado() {
        return DocFirmado;
    }

    public void setDocFirmado(String DocFirmado) {
        this.DocFirmado = DocFirmado;
    }

    public String getDocOriginal() {
        return DocOriginal;
    }

    public void setDocOriginal(String DocOriginal) {
        this.DocOriginal = DocOriginal;
    }

 
    
    
}
