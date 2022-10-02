/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dyto.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Fernando Bustos
 */
public class EmailHelper {
    
    public static boolean sendHtmlEmail(String hostName, int port, boolean ssl, String username, String password, String toEmail, String toName, String fromEmail, String fromName, String subject, String html, String text) {
        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setDebug(false);
            email.setHostName(hostName);
            email.setSmtpPort(port);
            email.setSSLOnConnect(ssl);
            
            //email.setStartTLSEnabled(true);
            
            if (!username.isEmpty())
                email.setAuthentication(username, password);
            email.addTo(toEmail, toName);
            email.setFrom(fromEmail, fromName);
            
            email.setCharset("UTF-8");
            email.setSubject(subject);

            // set the html message
            email.setHtmlMsg(html);
            
            

            // set the alternative message
            if (text.isEmpty())
                text = "Su cliente de correo no soporta HTML.";
            email.setTextMsg(text);

            // send the email
            email.send();
        } catch (EmailException ee) {
            ee.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean sendHtmlEmailWithAttachments(String hostName, int port, boolean ssl, String username, String password, String toEmail, String toName, String fromEmail, String fromName, String subject, String html, String text, List<File> adjuntos) {
        try {
                List<EmailAttachment> attachments=new ArrayList<>();
                for(int i=0;i<adjuntos.size();i++){
                // Create the attachment
                    EmailAttachment attachment = new EmailAttachment();
                    attachment.setPath(adjuntos.get(i).getPath());
                    attachment.setDisposition(EmailAttachment.ATTACHMENT);
                    attachment.setDescription("Description");
                    attachment.setName(adjuntos.get(i).getName()); 
                    attachments.add(attachment);
            }
           
            
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setDebug(false);
            email.setHostName(hostName);
            email.setSmtpPort(port);
            email.setSSLOnConnect(ssl);
            
            if (!username.isEmpty())
                email.setAuthentication(username, password);
            email.addTo(toEmail, toName);
            email.setFrom(fromEmail, fromName);
            
            email.setCharset("UTF-8");
            email.setSubject(subject);

            // set the html message
            email.setHtmlMsg(html);
            
            

            // set the alternative message
            if (text.isEmpty())
                text = "Su cliente de correo no soporta HTML.";
            email.setTextMsg(text);
            // add the attachment
            if(attachments.size()>0){
                for(int i=0;i<attachments.size();i++){
                    email.attach(attachments.get(i));
                }
            }
            
            // send the email
            email.send();
        } catch (EmailException ee) {
            ee.printStackTrace();
            return false;
        }
        return true;
    }
    
}
