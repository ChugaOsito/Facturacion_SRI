
package com.gadm.tulcan.rest.validarpdf;

import com.gadm.tulcan.rest.certificados.certificados;
import com.gadm.tulcan.rest.modelo.EntradasValidarpdf;
import com.gadm.tulcan.rest.modelo.SalidasValidarpdf;

import java.security.KeyStoreException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Validarpdf")
public class validarpdf {
    @GET
    @Produces (MediaType.TEXT_PLAIN)
    public String Home(){
        EntradasValidarpdf modelo= new EntradasValidarpdf();
    return "No se ha ingresado ningun valor"+modelo.getUbicacion();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public  List<SalidasValidarpdf> getDatos(EntradasValidarpdf datos)throws KeyStoreException, Exception{
         System.out.println("Este es el resultado");
        EntradasValidarpdf modelo= new EntradasValidarpdf();
        
        certificados firmas = new certificados();
      
        firmas.Encerar();
      List<SalidasValidarpdf> salida=null;
        modelo=datos;
       
    
       
       
      
      
    return salida;
    }
}
 /*
{
    "ubicacion": "Aqui-va-ubicacion"
}
*/