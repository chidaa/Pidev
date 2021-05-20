/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.DetailsResForm;
import com.mycompany.myapp.gui.chercherRes;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ThreadLocalRandom.current;

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
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        setTitle("List tasks");
 
        
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

//          cnt2.getAllStyles().setAlignment(Component.CENTER);
//
//         cnt2.getStyle().setBgColor(0xffffff);
//            
//            cnt2.getStyle().setBgTransparency(255);
//            cnt2.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
//            cnt2.getStyle().setMargin(Component.BOTTOM, 3);
//            
//            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
//            cnt2.getStyle().setBorder(RoundBorder.create().
//                    rectangle(true).
//                    color(0xffffff).
//                    strokeColor(0).
//                    strokeOpacity(120)
//                  //  stroke(borderStroke)
//                    );
//            
            add(cnt2);
             btnDetails.addActionListener(p -> new DetailsResForm(current, e).show());
//             chercherbutton.addActionListener(p->{
//         Form f= new  chercherRes(theme,chercher.getText());
//         f.show();
//         });

            chercherbutton.addActionListener(p->new chercherRes(current,chercher.getText()).show());



        }


    }

    

}
