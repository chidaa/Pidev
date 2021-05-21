/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;
import pidevMobile.Entities.disponibilite;
import guizi.articleform;

import pidevMobile.Services.ServiceDisponibilite;


/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ListeDispo extends Form{
     private TextField chercher ;
     private Button chercherbutton;
     Resources theme;
          Resources theme_2;

     Form current;
     public ListeDispo(Form previous) {
 current = this; //Récupération de l'interface(Form) en cours
         theme_2 = UIManager.initFirstTheme("/theme_2");

        theme = UIManager.initFirstTheme("/theme_1");
        
      Toolbar tb =new Toolbar();
        setToolbar(tb);
      //  getTitleArea().setUIID("container");
             setTitle("Home");
             getContentPane().setScrollVisible(false);
             
                     Image img = theme.getImage("logo.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
               tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl)); 
   tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e -> new ShowlistResForm(current).show());
   Form hi = new prodcats.gui.HomeForm();
   tb.addMaterialCommandToSideMenu("Livreurs", FontImage.MATERIAL_UPDATE, e ->  hi.show());
   tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_UPDATE, e ->  new articleform(theme_2).show());
    tb.addMaterialCommandToSideMenu("Disponibilité", FontImage.MATERIAL_UPDATE, e ->  new ListeDispo(current).show());
            
        setTitle("Liste des disponibilites");
        chercher = new TextField("","chercher une disponibilite");
        chercherbutton = new Button("chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);
        
         
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceSocieteLivraison.getInstance().getAllSociete().toString());
//         getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         
         
         List<disponibilite> list = ServiceDisponibilite.getInstance().getAllDispo();
         
       for (disponibilite e : list) {
            System.out.println(e);
           
            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());;
            
             SpanLabel lbid = new SpanLabel("Nom :" + e.getSocietes_id().getNom());
            SpanLabel lbjours = new SpanLabel("Jours :" + e.getJours());
            SpanLabel lbRegion = new SpanLabel("Region :" + e.getRegion());
            

      
             cnt1.add(lbid);
            cnt1.add(lbjours);
            cnt1.add(lbRegion);
            
            Button contact = new Button("contacter");
       cnt1.add(contact);
       contact.addActionListener((evt)->{
           
      Message msg = new Message("Body of message");
      Display.getInstance().sendMessage(new String[]{""}, "Subject of message", msg);
       }); 
            cnt2.add(cnt1);
           cnt2.getStyle().setBgColor(0x99CCCC);
          cnt2.getStyle().setBgTransparency(255);
           Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
           cnt2.getStyle().setBorder(RoundBorder.create().
                   rectangle(true).
                   color(0xffffff).
                   strokeColor(0).
                    strokeOpacity(120).
                   stroke(borderStroke));
              
        
            add(cnt2);

        }
        chercherbutton.addActionListener((evt)->{
        Form l= new RechercheDispo(current,chercher.getText());
        l.show();
    });
    }
   
}
