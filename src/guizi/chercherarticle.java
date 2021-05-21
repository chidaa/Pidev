/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guizi;

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
import mobilezi.mainzi;
import entitieszi.article;
import mobilezi.mainzi;
import serviceszi.serviceart;

/**
 *
 * @author HP ENVY
 */
public class chercherarticle extends Form {
     Resources theme;
   String chercher;
    private ContainerList listContainer;
    

    public chercherarticle(Resources theme,String chercher) {
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
            serviceart ms = new serviceart();

     ArrayList<article> allmedecins = ms.getarte(chercher);
        for (article m : allmedecins){
       //AFFICHER Image
           /* EncodedImage placeHolder = EncodedImage.createFromImage(MyApplication.theme.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getPic();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);*/
       //End Afficher Image
     //AFFICHER Image
            EncodedImage placeHolder = EncodedImage.createFromImage(prodcats.MyApplication.theme2.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getImage();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);
       //End Afficher Image
       Container c = new Container();
       
       c.setLayout(BoxLayout.y());
       c.add(image);
       c.add(new SpanLabel("Sujet : " + m.getSujet()));
       c.add(new SpanLabel("Description : " + m.getDescription()));
       
     
      
       
       listContainer.add(c);
       
   
   }
   this.add(listContainer);
   getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           articleform f= new  articleform(theme);
           f.show();
        });
   }

    private void addAction() {
    
    }
    
    
}