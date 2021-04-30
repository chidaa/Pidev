/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import java.util.Properties;

import Interfaces.IServiceMail;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author admin
 */
public class ServiceMail{
    
    public  void envoyerMail(String email,String Subject,String Object) {

        final String username = "aicha.khlif@esprit.tn";
        final String password = "Aichaaicha0511...";
        String fromEmail = "aicha.khlif@esprit.tn";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
           @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        
        System.out.println(email);
        System.out.println(Object);
        System.out.println(Subject);
       
       try { 
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(Subject);

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(Object);

            emailContent.addBodyPart(textBodyPart);
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
        }
       catch (MessagingException e) {
                        System.out.println("MSG ENVOYE");

        }

    }


   
    
}
