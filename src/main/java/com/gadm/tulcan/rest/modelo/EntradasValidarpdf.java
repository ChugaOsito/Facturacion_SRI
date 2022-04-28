
package com.gadm.tulcan.rest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public  class EntradasValidarpdf {
    private String ubicacion;
    public  EntradasValidarpdf(){
    
    }

    public EntradasValidarpdf(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
