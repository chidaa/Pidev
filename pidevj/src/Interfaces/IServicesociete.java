/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javafx.collections.ObservableList;
import pidevj.Entities.societelivraison;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public interface IServicesociete {
    
    public ObservableList<societelivraison> afficherSocieteList();
               public void addSociete(societelivraison s );
       
     public void modifierSociete(societelivraison s);
      public void supprimerSociete(int id) ;
    public int getIdParId(String id ) ;
        public int getcontactParId(String id) ;
        
        public ObservableList<String> getValuesSociete();
     public societelivraison load_data(String email);
         public String load_data2(int id);
              public societelivraison load_data8(String id);
}
