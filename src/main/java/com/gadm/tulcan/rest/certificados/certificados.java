/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadm.tulcan.rest.certificados;

import com.gadm.tulcan.rest.modelo.SalidasValidarpdf;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chuga
 */
public class certificados {
    private final static certificados Certificados = new certificados();
    private final static List<SalidasValidarpdf> listado=new ArrayList<>();
    
    public static certificados getInstancia(){
    return Certificados;
    }
    public static List<SalidasValidarpdf> getListado(){
    return listado;
    }
     public void Encerar(){
                
    listado.clear();
    }
    public static SalidasValidarpdf addSalida(         
    String Cedula,
    String Nombre,
    String Institucion,
   String Cargo,
    String Valido_Desde,
    String Valido_Hasta,
   String Fecha_Firmado,
    boolean Validez){
        SalidasValidarpdf salidasvalidarpdf = new SalidasValidarpdf();
        salidasvalidarpdf.setCedula(Cedula);
        salidasvalidarpdf.setNombre(Nombre);
        salidasvalidarpdf.setInstitucion(Institucion);
        salidasvalidarpdf.setCargo(Cargo);
        salidasvalidarpdf.setValido_Desde(Valido_Desde);
        salidasvalidarpdf.setValido_Hasta(Valido_Hasta);
        salidasvalidarpdf.setFecha_Firmado(Fecha_Firmado);
         salidasvalidarpdf.setValidez(Validez);
    listado.add(salidasvalidarpdf);
    return salidasvalidarpdf;
    }
    
    /*
    public void ListaCertificados(
     String Cedula,
    String Nombre,
    String Institucion,
   String Cargo,
    String Valido_Desde,
    String Valido_Hasta,
   String Fecha_Firmado){
    SalidasValidarpdf salida = new SalidasValidarpdf(
    Cedula,
    Nombre,
    Institucion,
   Cargo,
    Valido_Desde,
    Valido_Hasta,
   Fecha_Firmado);
    
    listado.add(salida);
    }*/
}
