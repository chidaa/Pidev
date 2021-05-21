/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import prodcats.MyApplication;
import prodcats.entities.Produits;
import prodcats.services.PanierService;
import prodcats.services.ProduitsServices;

/**
 *
 * @author USER
 */
public class AffProduit extends Form{
     private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Resources theme;
 
    public AffProduit(Resources theme) {
         setTitle("LISTE DES PODUITS");
        this.theme=theme;
        this.setLayout(BoxLayout.y());
                    
        addGui();
        addAction();

      
    }

    public AffProduit() {
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
            ProduitsServices ms = new ProduitsServices();

     ArrayList<Produits> allproduits = ms.getAllProducts();
        for (Produits m : allproduits){
       //AFFICHER Image
            EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme2.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/images/" + m.getImage();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);
       //End Afficher Image
       //ImageViewer image = new ImageViewer(MyApplication.theme.getImage("windows-icon.png").scaled(150, 150));
       Container c = new Container();
       c.setLayout(BorderLayout.center());
       c.setLayout(BoxLayout.y());
      
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
       c1.add(image);
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("quantite : " + m.getQuantite()));
       c.add(new SpanLabel("prix  : " + m.getPrix()));
       c.add(new SpanLabel("categories  : " + m.getCategorie()));
      
       
       
       Button btnFavoris= new Button("Panier");
       btnFavoris.addActionListener((evt)->{
          PanierService L = new PanierService();
          L.add(m);
          System.out.println(L.getLivs());
     });
        c1.add(c);
  
       listContainer.addAll(c1,btnFavoris);
       
   
   }
         /*getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           Form f= new  HomeForm();
           f.show();
        });*/
         
           getToolbar().addCommandToLeftSideMenu("Panier", null, (evt) -> {
           Form f= new Panier();
           f.show();
        });
           
   this.add(listContainer);
   }

  private void addAction() {
     chercherbutton.addActionListener((evt)->{
         Form f= new  chercherproduit(theme,chercher.getText());
         f.show();
     });
     
    
    }
    
}
