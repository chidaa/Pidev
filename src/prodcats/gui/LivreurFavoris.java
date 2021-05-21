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
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import java.util.List;
import prodcats.MyApplication;
import prodcats.entities.Livreur;
import prodcats.services.LivreurFavoriService;

/**
 *
 * @author HP ENVY
 */
public class LivreurFavoris  extends Form{
//private ServiceVoiture sv;
private List<Livreur> livreurs;    
private ContainerList listContainer;
static int i;

      public LivreurFavoris()
      {
        //sv = new ServiceVoiture();
         LivreurFavoriService L = new LivreurFavoriService();
         livreurs=L.getLivs();
          System.out.println(L.getLivs());
        setTitle("Mes Favoris");
        setLayout(BoxLayout.y());

//        List<Voiture> voitures = sv.AfficherListVoitures();
//
//        for (int i = 0; i < voitures.size(); i++) {
//            add(addVoitureItem(voitures.get(i)));
//        }

        refreshLayout();
        
        getToolbar().addCommandToOverflowMenu("Delete All", null, (evt) -> {
            try {
                livreurs.clear();
                this.refreshLayout();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());;
            }
        });
        
        getToolbar().addCommandToOverflowMenu("Refresh", null, (evt) -> {
            this.refreshLayout();
        });
        
        getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           Form f= new  HomeForm();
           f.show();
        });
      }
      
      public void refreshLayout() {
      
        this.removeAll();
       
    
        for (i = 0; i < livreurs.size(); i++) {
            this.add(addLivreurItem(livreurs.get(i)));
        }
        
   
    this.revalidate();
        }

      public Container addLivreurItem(Livreur L){
          
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
      
       
      ImageViewer image = new ImageViewer(MyApplication.theme.getImage("livreur.png").scaled(150, 150));
       Container c = new Container();
       c.setLayout(BorderLayout.center());
       c.setLayout(BoxLayout.y());
      
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
       c1.add(image);
       c.add(new SpanLabel("Cin  : " + L.getCIN()));
       c.add(new SpanLabel("nom : " + L.getNom()));
       c.add(new SpanLabel("prenom : " + L.getPrenom()));
       c.add(new SpanLabel("Disponibilite  : " + L.getDisponibilite()));
       Label lbtest = new Label("Supprimer");
     
       c1.addAll(c,lbtest);
    lbtest.addPointerReleasedListener(ev -> {
            if (Dialog.show("Confirmation", "Supprimer le livreur de la liste de favoris?", "Oui", "Non")) {
                try {
                    livreurs.remove(L);
                    this.refreshLayout();
                    System.out.println("Suppression OK !");
                } catch (Exception ex) {
                    Dialog.show("Error", "Erreur d'insertion!", "OK", null);
                }
            }
        });

      //  holder.setLeadComponent(lbMarque);
       
      /* Button BtnSupprimer = new Button("Supprimer");
       BtnSupprimer.addActionListener((evt)->{
         livreurs.remove(livreurs.get(i));
     });*/
       listContainer.addAll(c1);
       return listContainer;
   }
      
      
    }

    

