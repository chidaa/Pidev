/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.services;

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
import prodcats.entities.Livreur;
import prodcats.utils.Statics;

/**
 *
 * @author HP ENVY
 */
public class LivreurService {
    private ArrayList <Livreur> livreurs ;
    private  double[] statvalues ;
    private String[] statTitles;
    
    public ArrayList<Livreur> parseLivreurs(String jsonText) {
        try {
            livreurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Livreur mdata = new Livreur();
                mdata.setID((int) Float.parseFloat(obj.get("id").toString()));
                 mdata.setCIN((int) Float.parseFloat(obj.get("cin").toString()));
                mdata.setNom(obj.get("nom").toString());
                mdata.setPrenom(obj.get("prenom").toString());
                mdata.setDisponibilite(obj.get("disponibilite").toString());
             
               livreurs.add(mdata);
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return livreurs;
    }

    public ArrayList<Livreur> getLivreurs() {

        String url = Statics.BASE_URL + "/Api/livreur";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livreurs = parseLivreurs(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreurs;
    }

    public ArrayList<Livreur> getLivreurs(String chercher) {
 String url = Statics.BASE_URL + "/Api/livreur/AfficherLiv/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livreurs = parseLivreurs(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreurs;

    }
    
     public double[] parseStatValues(String jsonText) {
        try {
            statvalues = new double[20];
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            int i=0;
            for (Map<String, Object> obj : list) {
              statvalues[i]=(double) Double.parseDouble(obj.get("nbl").toString());
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return statvalues;
    }

    public double[] getStatValues() {

        String url = "http://127.0.0.1:8000/API/StatMobile";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statvalues = parseStatValues(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
                 System.out.print("values = = " + statvalues.toString());
return statvalues;

    }

    public String[] parseStatTitles(String jsonText) {
        try {
            statTitles = new String[20];
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            int i=0;
            for (Map<String, Object> obj : list) {
              statTitles[i]=obj.get("disponibilite").toString();
            i++;
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        System.out.print("Titles = = " + statTitles.toString());
        return statTitles;
    }

    public String[] getStatTitles() {

        String url = "http://127.0.0.1:8000/API/StatMobile";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                statTitles = parseStatTitles(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return statTitles;
    }
    
}
