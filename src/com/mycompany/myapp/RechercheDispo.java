/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import pidevMobile.Entities.disponibilite;
import pidevMobile.Services.ServiceDisponibilite;
import pidevMobile.utils.Statics;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class RechercheDispo extends Form {
  
     Resources theme;
     String chercher;
    private ContainerList listContainer;
    

    public RechercheDispo(Form previous,String chercher) {
        this.theme=theme;
        this.setLayout(BoxLayout.y());
        this.chercher=chercher; 


        
        //addAction();
    
      
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            ServiceDisponibilite ms = new ServiceDisponibilite();

     ArrayList<disponibilite> alldispo = ms.getarte(chercher);
        for (disponibilite m : alldispo){
       //AFFICHER Image
           /* EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);*/
       //End Afficher Image
     //AFFICHER Image
           
       Container c = new Container();
       
       c.setLayout(BoxLayout.y());
     //  c.add(new SpanLabel("id : " + m.getId()));
       c.add(new SpanLabel("jours : " + m.getJours()));
       c.add(new SpanLabel("region : " + m.getRegion()));
       m.setSocietes_id(m.getSocietes_id());
            //  c.add(new SpanLabel("societe : " + m.getSocietes_id().getNom()));

       
     
      
       
       listContainer.add(c);
       
   
   }
   this.add(listContainer);
   getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           ListeDispo f= new  ListeDispo(previous);
           f.show();
        });
   
          
          }

    
    
}
    

