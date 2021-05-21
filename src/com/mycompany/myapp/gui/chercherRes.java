/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;

/**
 *
 * @author BENJOU
 */
public class chercherRes extends Form {
    Form current;
    Resources theme;
    String chercher;
    private ContainerList listContainer;


//    public chercherRes(Resources theme, String chercher) {
//       
//       // addGui();
//    
//
//    }

    
    public chercherRes(Form previous,String chercher) {
         this.theme = theme;
        this.setLayout(BoxLayout.y());
        this.chercher = chercher;
        setTitle("Resultat de la recherche : ");

        
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

          listContainer = new ContainerList();
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
        ServiceReclamation ms = new ServiceReclamation();

        ArrayList<Reclamation> allres = ms.getRes(chercher);
        for (Reclamation m : allres) {

            Container c = new Container();
            c.setLayout(BoxLayout.y());

            c.add(new SpanLabel("id : " + m.getId()));
            c.add(new SpanLabel("description : " + m.getDescription()));
            c.add(new SpanLabel("theme  : " + m.getTheme()));
            m.setUser_id(m.getUser_id());
            c.add(new SpanLabel("user  : " + m.getUser_id().getEmail_user()));
          
            

            listContainer.add(c);

        }
        this.add(listContainer);
    }

        
    
    }




