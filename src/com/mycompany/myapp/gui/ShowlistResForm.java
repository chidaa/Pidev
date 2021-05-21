/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.DetailsResForm;
import com.mycompany.myapp.gui.chercherRes;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ThreadLocalRandom.current;
import com.mycompany.myapp.gui.elyesform;

/**
 *
 * @author admin
 */
public class ShowlistResForm extends Form {

    Form current;
    private TextField chercher;
        
 private Button chercherbutton;
  Resources theme;
  
 

 
    public ShowlistResForm(Form previous) {
      
 Toolbar tb =new Toolbar();
        setToolbar(tb);
      //  getTitleArea().setUIID("container");
             setTitle("Home");
             getContentPane().setScrollVisible(false);
             
                            theme = UIManager.initFirstTheme("/theme_1");
 Image img = theme.getImage("logo.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
               tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl)); 
               
   tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e -> new com.mycompany.myapp.gui.elyesform().show());
   Form hi = new prodcats.gui.HomeForm();
   tb.addMaterialCommandToSideMenu("Livreurs", FontImage.MATERIAL_UPDATE, e ->  hi.show());

        setTitle("Liste Reclamations");
        
        ServiceReclamation rec = new ServiceReclamation();
        
        String msg="Vous venez de consulter vos reclamations";
      //  rec.send_sms(msg);
 
        
//                TextField recherche = new TextField();
//        add(recherche);
//        
//        
         

       // Button stat = new Button("statistique ");


        add(new Label("Voici votre liste des reclamation:"));
        
         TextField chercher = new TextField("","chercher reclamation");
        Button chercherbutton = new Button("chercher");
        
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     


        //Affichge de la liste des objectifs
        List<Reclamation> list = ServiceReclamation.getInstance().getAllReclamation();
        for (Reclamation e : list) {
                    current = this; //Récupération de l'interface(Form) en cours

            System.out.println(e);
            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());;

            SpanLabel lbnom = new SpanLabel("id :" + e.getId());
            SpanLabel lbemail = new SpanLabel("description :" + e.getDescription());
            SpanLabel lbpays = new SpanLabel("theme :" + e.getTheme());
            SpanLabel lbmed = new SpanLabel("Client :" + e.getUser_id().getEmail_user());
            
                                Button btnDetails = new Button("Détails");



            cnt1.add(lbnom);
            cnt1.add(lbemail);
            cnt1.add(lbpays);
            cnt1.add(lbmed);
            cnt1.add(btnDetails);
            cnt2.add(cnt1);

         
            add(cnt2);
             btnDetails.addActionListener(p -> new DetailsResForm(current, e).show());
//             chercherbutton.addActionListener(p->{
//         Form f= new  chercherRes(theme,chercher.getText());
//         f.show();
//         });




        }
            chercherbutton.addActionListener(p->new chercherRes(current,chercher.getText()).show());


    }

    

}
