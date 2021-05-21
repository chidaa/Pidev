/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import prodcats.MyApplication;
import prodcats.entities.Commande;
import prodcats.entities.Livreur;
import prodcats.services.CommandeService;
import prodcats.services.LivreurFavoriService;
import prodcats.services.LivreurService;

/**
 *
 * @author HP ENVY
 */
public class CommandeFormFront extends Form{
    private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Resources theme;
 
    public CommandeFormFront(Resources theme) {
         setTitle("LISTE DES COMMANDES");
        this.theme=theme;
        this.setLayout(BoxLayout.y());
                    
        addGui();
        //addAction();

      
    }

    public CommandeFormFront() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addGui() {
        chercher = new TextField("","chercher un commande");
        chercherbutton = new Button("chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            CommandeService ms = new CommandeService();

     ArrayList<Commande> allcommandes = ms.getCommandes();
        for (Commande m : allcommandes){
       //AFFICHER Image
           /* EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);*/
       //End Afficher Image
       ImageViewer image = new ImageViewer(MyApplication.theme.getImage("cart.png").scaled(150, 150));
       Container c = new Container();
       c.setLayout(BorderLayout.center());
       c.setLayout(BoxLayout.y());
      
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
       c1.add(image);
       c.add(new SpanLabel("Id  : " + m.getID()));
       c.add(new SpanLabel("Prix total : " + m.getPrix_Total()));
       c.add(new SpanLabel("Etat : " + m.getEtat()));
       c1.add(c);
       Button supprimer = new Button("supprimer");
       supprimer.addActionListener((evt)->{
       ms.delete(m);
       new CommandeFormFront(theme).show();
       });
       c1.add(supprimer);
       listContainer.addAll(c1);
       
   }
         getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           Form f= new  HomeForm();
           f.show();
        });
         
          
   this.add(listContainer);
   }

  /* private void addAction() {
     chercherbutton.addActionListener((evt)->{
         Form f= new  chercherLivreur(theme,chercher.getText());
         f.show();
     });
     */
    
    
}
