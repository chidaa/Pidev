/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.user;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import javafx.scene.control.ToolBar;

/**
 *
 * @author admin
 */
public class AddResForm extends HomeForm {
    
        private Resources theme;

    
        public AddResForm(Form previous) {
            setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());
  
         Label linfo = new Label("Info personnelle :", "Container");
         linfo.getAllStyles().setAlignment(Component.CENTER);
  
        TextField tfdesc= new TextField("", "Description");
        TextField tftheme= new TextField("", "Theme");
      
                  
          Label lmed = new Label("choisir votre client :", "Container");
                   lmed.getAllStyles().setAlignment(Component.CENTER);
                  ComboBox  cmb = new ComboBox<>();
                  
  
                  
          ArrayList<user> med = new ArrayList<>();
        
        ServiceReclamation ad = new ServiceReclamation();
        
        med.addAll(ad.getListmed2());
          for (user object : med) {
            cmb.addItem(object.getEmail_user());
        }

         
           
                 /**style**/
       Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
      
/**/
   Style tfemailStyle = tfdesc.getAllStyles();
       tfemailStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
tfemailStyle.setBgColor(0xffffff);
tfemailStyle.setBgTransparency(255);
tfemailStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
tfemailStyle.setMargin(Component.BOTTOM, 3);
/**/
   Style tfpaysStyle = tftheme.getAllStyles();
       tfpaysStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
tfpaysStyle.setBgColor(0xffffff);
tfpaysStyle.setBgTransparency(255);
tfpaysStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
tfpaysStyle.setMargin(Component.BOTTOM, 3);
/**/
   Style cmbStyle = cmb.getAllStyles();
       cmbStyle.setBorder(RoundRectBorder.create().
        strokeColor(0).
        strokeOpacity(120).
        stroke(borderStroke));
cmbStyle.setBgColor(0xffffff);
cmbStyle.setBgTransparency(255);
cmbStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
cmbStyle.setMargin(Component.BOTTOM, 3);
/**/
   
/**/
   

        /*********/

        Button btnValider = new Button("Add Reclamation");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdesc.getText().length()==0)||(tftheme.getText().length()==0))
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        Reclamation t = new Reclamation();
                     //   t.setNom(tfnom.getText());
                        t.setDescription(tfdesc.getText());
                        t.setTheme(tftheme.getText());

                        user m = med.get(cmb.getSelectedIndex());
                         System.out.println("aaaaaaaaaaaaaassssss");
                        System.out.println(m);
                        


                        if( ServiceReclamation.getInstance().addReclamation(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
          
                }
                            
            }

                
        });
        /*********************/
        
         addAll(linfo,tfdesc,tftheme,lmed,cmb,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
            
        }

    AddResForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


 
 
}
