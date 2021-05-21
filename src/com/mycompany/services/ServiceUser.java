/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
 
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
 
import com.codename1.io.NetworkManager;
 
import com.codename1.ui.Dialog;
 
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.myapp.gui.elyesform;


import com.mycompany.utils.Statics;
import java.util.Map;


 
 
 
/**
 *
 * @author asus
 */
public class ServiceUser {
   
    public static ServiceUser instance = null;
   
    public static boolean resultOk = true;
   
    private ConnectionRequest req;
   
   
    public static ServiceUser getInstance() {
        if(instance == null)
            instance = new ServiceUser();
        return instance ;
       
    }
   
    public ServiceUser() {
        req = new ConnectionRequest();
    }
      //Signup
    public void signup(TextField nom,TextField prenom,TextField password,TextField email,TextField confirmPassword, Resources res){
        String url = Statics.BASE_URL+"user/signup?username="+nom.getText()+"&nom="+nom.getText()+"&prenom="+prenom.getText()+"&email="+email.getText()+"&roles=ROLE_CLIENT"+"&password="+password.getText();  
   
    req.setUrl(url);
    //controle de saisie
    if(nom.getText().equals(" ") && prenom.getText().equals(" ") && email.getText().equals(" ")  && password.getText().equals(" ") ){
        Dialog.show("Erreur"," veuillez remplir tout les champs !","OK",null);
    }
    //execution de url
    req.addResponseListener((e)-> {
   
           
                //get data
                byte[]data = (byte[]) e.getMetaData();
                String responseData = new String(data);
                System.out.println("data====>"+responseData);
            String adrs = "bedis.melaouah@esprit.tn";
               
               
             
       
               
           
           
       
       
    }
           
        );
  
NetworkManager.getInstance().addToQueueAndWait(req);
 
 
 
}  
    public void signin(TextField username,TextField password,Resources res){
        String url = Statics.BASE_URL+"signin?username="+username.getText()+"&password="+password.getText();
        req.setUrl(url);
       
        req.addResponseListener((e)->{
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
             try{ 
                if (json.equals("failed")){
                    Dialog.show("Echer dauthentification","Identifiants erronés","OK",null);
                }
                else {
                    System.out.println("data ====>"+json);
                    Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    Dialog.show("success", "success", "OK",null);
                     //Session  Service
                float id = Float.parseFloat(user.get("id").toString());
                    System.out.println("id*********"+id);
            
               new elyesform().show();
                }
             }catch(Exception ex) {
                    ex.printStackTrace();
                }});
                    
                        
       
       
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    /* public static void sendMail(String recepient) throws Exception{
        //Config
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
       
       
        String myAccountEmail = "testapimail77@gmail.com";
        String password  ="abcazerty1";
       
       /* Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
           
       
       
     Message message = prepareMessage(session,myAccountEmail,recepient);
       
        Transport.send(message);
        System.out.println("Message sent successfully");
           
        Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(myAccountEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            msg.setSubject("Utilisateur ajouté");
           
            SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
            st.connect("smtp.gmail",587,myAccountEmail,password);
            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response"+st.getLastServerResponse());*/
       
           
        

     
}