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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import prodcats.MyApplication;
import prodcats.entities.Livreur;
import prodcats.services.LivreurFavoriService;
import prodcats.services.LivreurService;

/**
 *
 * @author HP ENVY
 */
public class LivreurFormFront extends Form {
 private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Resources theme;
 
    public LivreurFormFront(Resources theme) {
         setTitle("LISTE DES LIVREURS");
        this.theme=theme;
        this.setLayout(BoxLayout.y());
                    
        addGui();
        addAction();

      
    }

    public LivreurFormFront() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addGui() {
        chercher = new TextField("","chercher un livreur");
        chercherbutton = new Button("chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            LivreurService ms = new LivreurService();

     ArrayList<Livreur> alllivreurs = ms.getLivreurs();
        for (Livreur m : alllivreurs){
       //AFFICHER Image
           /* EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);*/
       //End Afficher Image
       ImageViewer image = new ImageViewer(MyApplication.theme.getImage("livreur.png").scaled(150, 150));
       Container c = new Container();
       c.setLayout(BorderLayout.center());
       c.setLayout(BoxLayout.y());
      
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
       c1.add(image);
       c.add(new SpanLabel("Cin  : " + m.getCIN()));
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("prenom : " + m.getPrenom()));
       c.add(new SpanLabel("Disponibilite  : " + m.getDisponibilite()));
      
       Button contact = new Button("contacter");
       c.add(contact);
       contact.addActionListener((evt)->{
           
      Message msg = new Message("Body of message");
      Display.getInstance().sendMessage(new String[]{""}, "Subject of message", msg);
       }); 
      
       Button btnFavoris= new Button("Favoris");
       btnFavoris.addActionListener((evt)->{
          LivreurFavoriService L = new LivreurFavoriService();
          L.add(m);
          System.out.println(L.getLivs());
     });
        c1.add(c);
  
       listContainer.addAll(c1,btnFavoris);
       
   
   }
         getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           Form f= new  HomeForm();
           f.show();
        });
         
           getToolbar().addCommandToLeftSideMenu("Favoris", null, (evt) -> {
           Form f= new  LivreurFavoris();
           f.show();
        });
            getToolbar().addCommandToLeftSideMenu("Statistique", null, (evt) -> {
            StatForm st= new StatForm();
           st.createPieChartForm().show();
        });
   this.add(listContainer);
   }

   private void addAction() {
     chercherbutton.addActionListener((evt)->{
         Form f= new  chercherLivreur(theme,chercher.getText());
         f.show();
     });
     
    
    }
    
    
}
