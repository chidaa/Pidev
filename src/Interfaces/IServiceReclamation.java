/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Reclamation.Entities.Reclamation;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public interface IServiceReclamation {
               public ObservableList<Reclamation> afficherReclamationList();
               public void addReclamation(Reclamation r );
       
     public void modifierReclamation(Reclamation r);
      public void supprimerReclamation(int id) ;
    public int getIdParDesc(String id ) ;
    public ObservableList<Reclamation> rechercherReclamation(String s) ;
     public int nombrerec();
      public ObservableList<Reclamation> trierReservationid() ;
               public ObservableList<Reclamation> trierReservationtheme() ;
                        public ObservableList<Reclamation> trierReservationclient() ;
                        public List<Reclamation> afficherRecList();
                        public ObservableList<Integer> getnb_reclamation_Client();
                         public int nb_clients();

}
