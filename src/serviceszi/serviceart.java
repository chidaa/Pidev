/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceszi;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.Resources;
import entitieszi.article;
import java.io.IOException;
import serviceszi.serviceart;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilszi.connect;

/**
 *
 * @author User
 */
public class serviceart {
    private ConnectionRequest req;
       private ArrayList <article> articles; 
     public ArrayList<article> parseart(String jsonText) {
        try {
            articles = new ArrayList<>();
           JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map <String, Object> obj : list) {
                article mdata = new article();
               mdata.setId((int) Float.parseFloat(obj.get("id").toString()));
               
                                mdata.setSujet(obj.get("Sujet").toString());
                mdata.setDescription(obj.get("Description").toString());
              mdata.setImage(obj.get("image").toString());
    
                
               articles.add(mdata);
                           System.out.println("yess");
            }

        } catch (IOException ex) {
            System.out.println("prob");
        }
        return articles;
    }

    
     public ArrayList<article> get(String e) {

        String url = connect.BASE_URL + "/articlemob1"+e;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseart(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
    public ArrayList<article> getarte(String chercher) {
 String url = connect.BASE_URL + "/articlemob/"+chercher;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseart(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    public ArrayList<article> getart() {
 String url = connect.BASE_URL + "/affichermobilea";
     
      ConnectionRequest req2 = new ConnectionRequest();
        req2.setUrl(url);
        req2.setPost(false);
        req2.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseart(new String(req2.getResponseData()));
                req2.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req2);
        return articles;

    }
}
