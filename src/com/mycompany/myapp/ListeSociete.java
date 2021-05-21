/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Stroke;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import java.util.List;
import pidevMobile.Entities.societelivraison;
import pidevMobile.Services.ServiceSocieteLivraison;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ListeSociete extends Form{
     public ListeSociete(Form previous){
        setTitle("Liste Societe de livraison");
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceSocieteLivraison.getInstance().getAllSociete().toString());
//         getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         
         
         List<societelivraison> list = ServiceSocieteLivraison.getInstance().getAllSociete();
       for (societelivraison e : list) {
            System.out.println(e);
            Container cnt1 = new Container(BoxLayout.y());
            Container cnt2 = new Container(BoxLayout.x());;

            SpanLabel lbnom = new SpanLabel("nom :" + e.getNom());
            SpanLabel lbAdresse = new SpanLabel("Adresse :" + e.getAdresse());
            SpanLabel lbContact = new SpanLabel("Contact :" + e.getContact());

      
            
            cnt1.add(lbnom);
            cnt1.add(lbAdresse);
           cnt1.add(lbContact);
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
    }
    
}
