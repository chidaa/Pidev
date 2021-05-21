/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import pidevMobile.Entities.societelivraison;
import pidevMobile.Services.ServiceSocieteLivraison;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class AddSocForm extends Form{
     public AddSocForm(Form previous){
        setTitle("Add a new societe");
        //creer l'interface d'ajout:
       setLayout(BoxLayout.y());
       
        TextField tfNom = new TextField("","Nom ");
        TextField tfAdresse= new TextField("","Adresse  ");
        TextField tfContact= new TextField("","Contact ");
        
        //creer l'interface ajout:
        Button btnValider = new Button("Add societe");
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                //verif les donnees saisies par l'uti:
                if((tfNom.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfContact.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       //  Task t = new Task(Integer.parseInt(tfStatus.getText()),tfName.getText());
                       societelivraison s = new societelivraison(tfNom.getText(), tfAdresse.getText(),Integer.parseInt(tfContact.getText()));
                         //invoquer la meth d'ajout:
                         if (new ServiceSocieteLivraison().addSociete(s))
                             Dialog.show("Succes","Connection accepted",new Command("OK"));
                         else
                             Dialog.show("ERROR","Server ERROR", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR","Status must be a number", new Command("OK"));
                    }
                    
                }
               
            }
            
            
        });
        addAll(tfNom,tfAdresse,tfContact,btnValider);
        
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
