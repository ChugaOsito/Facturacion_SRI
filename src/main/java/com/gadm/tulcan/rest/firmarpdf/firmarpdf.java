
package com.gadm.tulcan.rest.firmarpdf;
//Eliminar estos imports
import com.gadm.tulcan.rest.modelo.EntradasFirmarpdf;
import com.gadm.tulcan.rest.modelo.SalidasFirmarpdf;
import com.gadm.tulcan.firmarpdf.Funcion_Firmarpdf;
import java.security.KeyStoreException;
//eliminar estos imports
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Firmarpdf")
public class firmarpdf {
    @GET
    @Produces (MediaType.TEXT_PLAIN)
    public String Home(){
        
    return "Modulo de firmado de PDF";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SalidasFirmarpdf getDatos(EntradasFirmarpdf datos) throws KeyStoreException, Exception{
        
        EntradasFirmarpdf entradas= new EntradasFirmarpdf();
        entradas=datos;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
         System.out.println(datos.getArchivop12());
        System.out.println(datos.getDocumentopdf());
       
        System.out.println(datos.getPagina());
        System.out.println(datos.getH());
        System.out.println(datos.getV());
        SalidasFirmarpdf firmar=new SalidasFirmarpdf();
        Funcion_Firmarpdf comprobar=new Funcion_Firmarpdf();
        SalidasFirmarpdf salida=null;
       
       
       if(comprobar.Invocador(entradas.getDocumentopdf(), entradas.getArchivop12(), entradas.getContrasena(), entradas.getPagina(), entradas.getH(), entradas.getV() )==false){
       
       salida=null;
       
       }else{
       salida=firmar;
       
       }
       
       
      
      
    return salida;
    }
}
 /*
{
    "ubicacion": "Aqui-va-ubicacion"
}
*/