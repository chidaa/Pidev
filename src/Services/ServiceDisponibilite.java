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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pidevMobile.Entities.disponibilite;
import pidevMobile.Entities.societelivraison;
import pidevMobile.utils.Statics;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ServiceDisponibilite {
    
      
    public ArrayList<disponibilite> disponibilite;
    public static ServiceDisponibilite instance;
     public boolean resultOk;
    private  ConnectionRequest req;
    public ServiceDisponibilite() {
        
        req= new  ConnectionRequest();
    }
    public static ServiceDisponibilite getInstance(){
        if(instance == null)
            instance= new ServiceDisponibilite();
         return instance;
       
    }
    public boolean addDispo(disponibilite d){
        
        //creer l'url:
       //String url = Statics.BASE_URL+"/tasks/"+t.getName()+"/"+t.getStatus();
       String url = Statics.BASE_URL+"/tasks/"+d.getId()+"/"+d.getJours()+"/"+d.getRegion();
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

    public ArrayList<disponibilite> parseDispo(String jsonText){
        
        try {
            disponibilite=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> DispoListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)DispoListJson.get("root");
            for( Map<String,Object> obj : list){
                disponibilite d = new disponibilite();
                
             //  float id = Float.parseFloat(obj.get("id").toString());
               
               Map<String, Object> med = (Map<String, Object>) obj.get("societes");
           societelivraison mede = new societelivraison(med.get("nom").toString());
                d.setSocietes_id(mede);
                
               d.setJours(obj.get("jours").toString());
               d.setRegion(obj.get("region").toString());
               
              disponibilite.add(d);
             
            }
            
        } catch (IOException ex) {
           // Logger.getLogger(ServiceSocieteLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return disponibilite;
    }
    public ArrayList<disponibilite> parseDispo2(String jsonText){
        
        try {
            disponibilite=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> DispoListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)DispoListJson.get("root");
            for( Map<String,Object> obj : list){
                disponibilite d = new disponibilite();
                
             //  float id = Float.parseFloat(obj.get("id").toString());
               
               Map<String, Object> med = (Map<String, Object>) obj.get("societes");
          // societelivraison mede = new societelivraison(med.get("nom").toString());
              //  d.setSocietes_id(mede);
                
               d.setJours(obj.get("jours").toString());
               d.setRegion(obj.get("region").toString());
               
              disponibilite.add(d);
             
            }
            
        } catch (IOException ex) {
           // Logger.getLogger(ServiceSocieteLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return disponibilite;
    }
    
    public ArrayList<disponibilite>  getAllDispo(){
          String url = Statics.BASE_URL+"/allDispoJSON";
          req.setUrl(url);
          req.setPost(false);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  disponibilite = parseDispo(new String(req.getResponseData()));
                  req.removeResponseListener(this);
              }
          });
          NetworkManager.getInstance().addToQueueAndWait(req);
          return disponibilite;
    }
    
    
    public ArrayList<disponibilite> getarte(String chercher) {
        String url = Statics.BASE_URL+"/RechDispoJSON/"+chercher;
 //String url = connect.BASE_URL + "/articlemob/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                disponibilite = parseDispo2(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return disponibilite;
    }

}
