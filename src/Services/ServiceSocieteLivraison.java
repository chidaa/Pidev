/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevMobile.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
//import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import pidevMobile.Entities.societelivraison;
import pidevMobile.utils.Statics;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ServiceSocieteLivraison {
    
    public ArrayList<societelivraison> societelivraison;
    public static ServiceSocieteLivraison instance;
     public boolean resultOk;
    private  ConnectionRequest req;
    public ServiceSocieteLivraison() {
        
        req= new  ConnectionRequest();
    }
    public static ServiceSocieteLivraison getInstance(){
        if(instance == null)
            instance= new ServiceSocieteLivraison();
        return instance;
    }
    public boolean addSociete(societelivraison s){
        
        //creer l'url:
       //String url = Statics.BASE_URL+"/tasks/"+t.getName()+"/"+t.getStatus();
       String url = Statics.BASE_URL+"/tasks/"+s.getId()+"/"+s.getNom()+"/"+s.getAdresse()+"/"+s.getContact();
        //creer la requete de cnx:
        req.setUrl(url);
        //ajout d'une action a la reception de la reponse:
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt){
                //verif le status de la reponse:
                resultOk= req.getResponseCode()==200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        }
        );
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public ArrayList<societelivraison> parseSocietes(String jsonText){
        
        try {
            societelivraison=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> societeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)societeListJson.get("root");
            for( Map<String,Object> obj : list){
                societelivraison s = new societelivraison();
               float id = Float.parseFloat(obj.get("id").toString());
               s.setId((int)id);
               s.setNom(obj.get("nom").toString());
               s.setAdresse(obj.get("adresse").toString());
               s.setContact((int)Float.parseFloat(obj.get("contact").toString()));
               societelivraison.add(s);
             
            }
            
        } catch (IOException ex) {
           // Logger.getLogger(ServiceSocieteLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return societelivraison;
    }
    
    public ArrayList<societelivraison>  getAllSociete(){
          String url = Statics.BASE_URL+"/allSocJSON";
          req.setUrl(url);
          req.setPost(false);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  societelivraison = parseSocietes(new String(req.getResponseData()));
                  req.removeResponseListener(this);
              }
          });
          NetworkManager.getInstance().addToQueueAndWait(req);
          return societelivraison;
    }
}
