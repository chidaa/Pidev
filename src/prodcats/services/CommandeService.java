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
import prodcats.entities.Commande;
import prodcats.entities.Livreur;
import prodcats.utils.Statics;

/**
 *
 * @author HP ENVY
 */
public class CommandeService {
    
    private ArrayList <Commande> commandes ;
    
    public ArrayList<Commande> parseCommandes(String jsonText) {
        try {
            commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Commande mdata = new Commande();
                mdata.setID((int) Float.parseFloat(obj.get("id").toString()));
                 mdata.setPrix_Total((float) Float.parseFloat(obj.get("prixtotal").toString()));
                mdata.setEtat(obj.get("etat").toString());
             
               commandes.add(mdata);
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return commandes;
    }

    public ArrayList<Commande> getCommandes() {

        String url = Statics.BASE_URL + "/Api/Commande";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }

    public ArrayList<Commande> getCommandes(String chercher) {
 String url = Statics.BASE_URL + "/Api/commande/AfficherCom/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }

    public void delete(Commande m) {
        String url=Statics.BASE_URL+"/Api/commande/Supprimer/"+m.getID();
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
}
