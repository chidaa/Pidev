/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.ContainerList;
import java.util.List;
import prodcats.MyApplication;
import static prodcats.MyApplication.theme;
import prodcats.entities.Produits;
import prodcats.services.PanierService;

/**
 *
 * @author USER
 */
public class Panier extends Form {
    
    //private ServiceVoiture sv;
private List<Produits> produits;    
private ContainerList listContainer;
static int i;

      public Panier()
      {
        //sv = new ServiceVoiture();
         PanierService L = new PanierService();
         produits=L.getLivs();
          System.out.println(L.getLivs());
        setTitle("MON PANIER");
        setLayout(BoxLayout.y());

//        List<Voiture> voitures = sv.AfficherListVoitures();
//
//        for (int i = 0; i < voitures.size(); i++) {
//            add(addVoitureItem(voitures.get(i)));
//        }

        refreshLayout();
        
        getToolbar().addCommandToOverflowMenu("Delete All", null, (evt) -> {
            try {
                produits.clear();
                this.refreshLayout();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());;
            }
        });
        
        getToolbar().addCommandToOverflowMenu("Refresh", null, (evt) -> {
            this.refreshLayout();
        });
        
        getToolbar().addCommandToLeftSideMenu("Retour", null, (evt) -> {
           Form f= new  AffProduit(theme);
           f.show();
        });
      }
      
      public void refreshLayout() {
      
        this.removeAll();
       
    
        for (i = 0; i < produits.size(); i++) {
            this.add(addProduitItem(produits.get(i)));
        }
        
   
    this.revalidate();
        }

      public Container addProduitItem(Produits L){
          
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
      
       
      ImageViewer image = new ImageViewer(MyApplication.theme.getImage("windows-icon.png").scaled(150, 150));
       Container c = new Container();
       c.setLayout(BorderLayout.center());
       c.setLayout(BoxLayout.y());
      
       Container c1 = new Container();
       c1.setLayout(BoxLayout.x());
        
       c1.add(image);
        c.add(new SpanLabel("nom : " + L.getNom()));
       c.add(new SpanLabel("quantite : " + L.getQuantite()));
       c.add(new SpanLabel("prix  : " + L.getPrix()));
       c.add(new SpanLabel("categories  : " + L.getCategorie()));
       Label lbtest = new Label("Supprimer");
     
       c1.addAll(c,lbtest);
    lbtest.addPointerReleasedListener(ev -> {
            if (Dialog.show("Confirmation", "Supprimer le produit de mon panier?", "Oui", "Non")) {
                try {
                    produits.remove(L);
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
