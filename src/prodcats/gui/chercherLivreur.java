/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import prodcats.MyApplication;
import prodcats.entities.Livreur;
import prodcats.services.LivreurService;

/**
 *
 * @author HP ENVY
 */
public class chercherLivreur extends Form {
     Resources theme;
   String chercher;
    private ContainerList listContainer;
    

    public chercherLivreur(Resources theme,String chercher) {
        this.theme=theme;
        this.setLayout(BoxLayout.y());
        this.chercher=chercher; 


        addGui();
        addAction();
    }
      private void addGui() {
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
            LivreurService ms = new LivreurService();

     ArrayList<Livreur> allmedecins = ms.getLivreurs(chercher);
        for (Livreur m : allmedecins){
       //AFFICHER Image
           /* EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);*/
       //End Afficher Image
       Container c = new Container();
       ImageViewer image = new ImageViewer(MyApplication.theme.getImage("livreur.png").scaled(150, 150));
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
      
       c.setLayout(BoxLayout.y());
       //c.add(image);
       c1.add(image);
       c.add(new SpanLabel("Cin  : " + m.getCIN()));
       c.add(new SpanLabel("nom : " + m.getNom()));
       c.add(new SpanLabel("prenom : " + m.getPrenom()));
       c.add(new SpanLabel("Disponibilite  : " + m.getDisponibilite()));
       c1.add(c);
       getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           LivreurFormFront f= new  LivreurFormFront(theme);
           f.show();
        });
       
       listContainer.add(c1);
       
   
   }
   this.add(listContainer);
   }

    private void addAction() {
    
    }
    
    
}
