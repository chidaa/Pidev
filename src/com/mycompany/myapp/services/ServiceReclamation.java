/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import static com.codename1.push.PushContent.setTitle;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elyes
 */
public class ServiceReclamation {
 public ArrayList<Reclamation> restab;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {

        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ArrayList<Reclamation> parseObjectifs(String jsonText) {
        try {
            restab = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ResListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ResListJson.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation res = new Reclamation();

                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("voici l'id" + id);
                res.setId((int) id);
                res.setDescription(obj.get("description").toString());
                res.setTheme(obj.get("theme").toString());

                Map<String, Object> med = (Map<String, Object>) obj.get("email");
                user mede = new user(med.get("email").toString());
                res.setUser_id(mede);
                
             

                restab.add(res);
            }
        } catch (IOException ex) {

        }
        return restab;
    }

    public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "/allrecJSON";

        System.out.println("eeeee" + url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                restab = parseObjectifs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return restab;
    }

    ArrayList<user> listmed = new ArrayList<>();

    public ArrayList<user> getListmed2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/medliste");
        con.addResponseListener((NetworkEvent evt) -> {
            ServiceUser ser = new ServiceUser();
            listmed = ser.getListMed(new String(con.getResponseData()));
            System.out.println("************************");
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listmed;
    }



    public boolean addReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "/ajouter_reclamation?description=" + t.getDescription()+ "&theme=" + t.getTheme()+ "&email_id=" + t.getUser_id().getIduser();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

 
      ArrayList<Reclamation> resss = new ArrayList<>();;

    public ArrayList<Reclamation> getRes(String chercher) {
        String url = Statics.BASE_URL + "/recherche/" + chercher;
        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resss = parseObjectifs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resss;

    }
    
    public void delete(Reclamation m) {
        String url=Statics.BASE_URL+"/Supprimer/"+m.getId();
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
public void send_sms(String content){
    
                 String accountSID = "ACc2a4d0ddf8f51c9f15eee57f920d3a0f";
String authToken = "8b230162ac037d0d77b64c8f40df3ca5";



String fromPhone = "+12057079946";
String destinationPhone="+21654428182";
      Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", destinationPhone).
        queryParam("From", fromPhone).
        queryParam("Body", content).
        basicAuth(accountSID, authToken).getAsJsonMap();
      if(result.getResponseData() != null) {
    String error = (String)result.getResponseData().get("error_message");
    if(error != null) 
        ToastBar.showErrorMessage(error);
    }
    
    
    }

}
