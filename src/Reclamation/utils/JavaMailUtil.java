/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;


/**
 *
 * @author elyes
 */
public class JavaMailUtil {
    public static void sendMail(String recepient)  throws Exception{
    Properties properties = new Properties();
        System.out.print("55555555555555555");

    properties.put("mail.smtp.auth","true");
    properties.put("mail.smtp.starttls.enable","true");
   properties.put("mail.smtp.host","smtp.gmail.com");
    properties.put("mail.smtp.port","587");
    
    String myAccountEmail ="elyes.ghrairi@esprit.tn";
String password = "181JMT1844";

Session session;
Authenticator authenticator = new Authenticator() {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail,password); //To change body of generated methods, choose Tools | Templates.
        }
    

};
       session = Session.getInstance(properties, authenticator);
               
    Message message = prepareMessage(session,myAccountEmail,recepient);
    Transport.send(message);
    System.out.print("Message envoyé");
    }
    private static Message prepareMessage(Session session,String myAccountEmail, String recepient) {
    try{Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(myAccountEmail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(myAccountEmail));
    message.setSubject("MY FIRST EMAIL");
    message.setText("HEY THERE \n my emailll");
    return message;
        
    }
    catch(Exception ex){
    Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,ex);
    }
     return null;
    }
    
    
   
    
    
    
    
}

