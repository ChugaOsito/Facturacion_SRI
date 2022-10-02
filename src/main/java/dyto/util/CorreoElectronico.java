/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dyto.util;


import static dyto.util.Constantes.CORREO_ENVIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;





/**
 *
 * @author Fernando Bustos
 */
public class CorreoElectronico {

   public boolean enviarCorreoConAnexos(String correoDestinatario, List <String> pathsArchivos) {
       boolean operacionExitosa=false;
        List<File> adjuntos=new ArrayList<>();
       String subject="Envio de comprobantes SRI";
       String emailBody="Le enviamos los siguientes comprobantes generados por la aplicacion: ";
        for(int i=0;i<pathsArchivos.size();i++){
            adjuntos.add(new File(pathsArchivos.get(i)));
        }
        try {
            operacionExitosa= EmailHelper.sendHtmlEmailWithAttachments("smtp.gmail.com",465, true, CORREO_ENVIO, "qfwghnhlqmrxrdij", correoDestinatario, correoDestinatario, CORREO_ENVIO, "SRI COMPROBANTES", subject, generarMensaje(emailBody) , "Su navegador no admite HTML",adjuntos);  
        } catch (SecurityException | IllegalStateException e1) {
            operacionExitosa=false;
            System.err.println(e1);
        }
        return operacionExitosa;
    }
   
    public String generarMensaje(String emailBody) {
        String[] mes = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "setiembre", "octubre", "noviembre", "diciembre"};
        Calendar c= Calendar.getInstance();    
        String fechaActual;
        fechaActual= Integer.toString(c.get(Calendar.DATE))+" de "+mes[c.get(Calendar.MONTH)]+" del "+Integer.toString(c.get(Calendar.YEAR));
        
        String contenido = "<body style=\"margin:0;padding:0;\">\n"
                + "	<table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;\">\n"
                + "		<tr>\n"
                + "			<td align=\"center\" style=\"padding:0;\">\n"
                + "				<table role=\"presentation\" style=\"width:21cm;border-collapse:collapse;border:1px solid #cccccc;border-spacing:0;text-align:left;\">\n"
                + "					<tr>\n"
                + "						<td align=\"center\" style=\"padding:40px 0 30px 0;background:#FFFFF;\">\n"
               // + "							<img src=\"https://logitransacros.com/logistica/javax.faces.resource/images/logo.png.logitrans?ln=omega-layout\" alt=\"\" width=\"300\" style=\"height:auto;display:block;\" />\n"
                + "						</td>\n"
                + "					</tr>\n"
                + "					<tr>\n"
                + "						<td style=\"padding:36px 30px 42px 30px;\">\n"
                + "							<table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;\">\n"
                + "								<tr>\n"
                + "									<td style=\"padding:0 0 36px 0;color:#153643;\">\n"
                + "										<p align=\"right\" style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n"
                +"                                                                                  COMPROBANTES SRI APP.<br />Tulcán, "+fechaActual+"</p>"
                + "										<p style=\"margin:0 0 12px 0;font-size:16px;line-height:24px;font-family:Arial,sans-serif;\">\n"
                +"Estimado cliente: <br /><br />"
                +emailBody+" <br /><br />"
                +"    Atentamente,<br /><br /> \n" 
                +"    COMPROBANTES SRI APP.</p>"
                + "									</td>\n"
                + "								</tr>\n"
                + "								<tr>\n"
                + "									<td style=\"padding:0;\">\n"
                + "										\n"
                + "									</td>\n"
                + "								</tr>\n"
                + "							</table>\n"
                + "						</td>\n"
                + "					</tr>\n"
                + "					<tr>\n"
                + "						<td style=\"padding:30px;background:#000000;\">\n"
                + "							<table role=\"presentation\" style=\"width:100%;border-collapse:collapse;border:0;border-spacing:0;font-size:9px;font-family:Arial,sans-serif;\">\n"
                + "								<tr>\n"
                + "									<td style=\"padding:0;width:50%;\" align=\"left\">\n"
                + "										<p style=\"margin:0;font-size:14px;line-height:16px;font-family:Arial,sans-serif;color:#ffffff;\">\n"
                + "											&reg;COMPROBANTES SRI APP.<br/>\n"
                + "										</p>\n"
                + "									</td>\n"
                + "									<td style=\"padding:0;width:50%;\" align=\"right\">\n"
                + "										\n"
                + "									</td>\n"
                + "								</tr>\n"
                + "							</table>\n"
                + "						</td>\n"
                + "					</tr>\n"
                + "				</table>\n"
                + "			</td>\n"
                + "		</tr>\n"
                + "	</table>\n"
                + "</body>";
                

        return contenido;
    }

}
