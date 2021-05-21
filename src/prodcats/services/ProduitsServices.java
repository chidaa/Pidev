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
import prodcats.entities.Produits;
import prodcats.utils.Statics;

/**
 *
 * @author USER
 */
public class ProduitsServices {
    public ArrayList<Produits> produits;
    
    public static ProduitsServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ProduitsServices() {
         req = new ConnectionRequest();
    }

    public static ProduitsServices getInstance() {
        if (instance == null) {
            instance = new ProduitsServices();
        }
        return instance;
    }

    public boolean addProduits(Produits p) {
        String url = Statics.BASE_URL + "/produits/" + p.getNom() + "/" + p.getQuantite() +"/" + p.getPrix() + "/" + p.getCategorie(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


    public ArrayList<Produits> parseproduit(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

          
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                Produits p = new Produits();
                float id = Float.parseFloat(obj.get("id").toString());
                float Quantite = Float.parseFloat(obj.get("quantite").toString());
                float Prix = Float.parseFloat(obj.get("prix").toString());
                p.setId((int)id);
                p.setNom(obj.get("nom").toString());
                p.setQuantite((int)Quantite);
                p.setPrix((int)Prix);
           
                               
                             
                System.out.println("fin praser");

                
                produits.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return produits;
    }
    public ArrayList<Produits> getAllProducts(){
        String url = Statics.BASE_URL+"/produitmob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    public boolean deletedProduit(int id) {
       
        String url = Statics.BASE_URL + "/deleteProduit/"+id+"";  
        req.setUrl(url);
        req.setPost(true);
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
    
     public ArrayList<Produits> getProduits(String chercher) {
 String url = Statics.BASE_URL + "/Api/Produit/AfficherPr/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduit(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;

    }
    
    
}
