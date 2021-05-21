/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class Home extends Form{
     Form current;
public Home(){
    current=this;
    setTitle("home");
    setLayout(BoxLayout.y());
    
    add(new Label("choose an option"));
    
    Button btnListeSoc = new Button("Liste Societe de livraison");
     Button btnListeDispo = new Button("Liste disponibilite");
    
   
    
    btnListeSoc.addActionListener(e-> new ListeSociete(current).show());
     btnListeDispo.addActionListener(e-> new ListeDispo(current).show());
     
     addAll(btnListeSoc,btnListeDispo);
    
    
}    
    
}
