/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Reclamation.Entities.Garantie;
import Reclamation.Entities.Reclamation;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author elyes
 */
public interface IServiceGarantie {
      public ObservableList<Garantie> afficherGarantieList();
               public void addGarantie(Garantie r );
       
     public void modifierGarantie(Garantie r);
      public void supprimerGarantie(int id) ;
    public int getIdParDesc(String id ) ;
     public ObservableList<Garantie> rechercherGarantie(String s) ;
public ObservableList<Garantie> trierGarantieproduit();
        public ObservableList<Garantie> trierGarantieclient();
        public ObservableList<Garantie> trierGarantieid();
        public List<Garantie> afficherGarList();
      public int nb_Garantie();
}
