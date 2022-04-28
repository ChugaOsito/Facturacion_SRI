package com.gadm.tulcan.rest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public  class EntradasFirmarpdf {
    private String documentopdf;
    private String Archivop12;
    private String Contrasena;
      private int pagina;
    private int h;
    private int v;

     public EntradasFirmarpdf(String documentopdf, String Archivop12, String Contraseña, int pagina, int UbicacionHorizontal,int UbicacionVertical) {
        this.documentopdf = documentopdf;
        this.Archivop12 = Archivop12;
        this.Contrasena = Contraseña;
        this.pagina=pagina;
        this.h= UbicacionHorizontal;
        this.v=UbicacionVertical;
    }
    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
  
    public  EntradasFirmarpdf(){
    
    }

   

    public String getDocumentopdf() {
        return documentopdf;
    }

    public void setDocumentopdf(String documentopdf) {
        this.documentopdf = documentopdf;
    }

    public String getArchivop12() {
        return Archivop12;
    }

    public void setArchivop12(String Archivop12) {
        this.Archivop12 = Archivop12;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

   
    
}
