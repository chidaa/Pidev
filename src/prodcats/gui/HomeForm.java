/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  prodcats.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import static prodcats.MyApplication.theme;
import prodcats.gui.LivreurFormFront;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnLivreur = new Button("Les livreurs");
        Button btnProduit = new Button("Les Produits");
        Button btnCommande = new Button("Mes commandes");
        
        btnProduit.addActionListener((evt)->{
        Form f= new AffProduit(theme);
         f.show();
     });
        
        btnLivreur.addActionListener((evt)->{
         Form f= new LivreurFormFront(theme);
         f.show();
     });
        
         
         btnCommande.addActionListener((evt)->{
         Form f= new CommandeFormFront(theme);
         f.show();
     });
        //btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
        //btnListTasks.addActionListener(e -> new ListTasksForm(current).show());
        addAll(btnLivreur, btnCommande, btnProduit);

    }

   

}
