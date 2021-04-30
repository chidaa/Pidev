/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javafx.collections.ObservableList;
import pidevj.Entities.disponibilite;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public interface IServicedisponibilite {
     public ObservableList<disponibilite> afficherDisponibiliteList();
               public void addDisponibilite(disponibilite d );
       
     public void modifierDisponibilite(disponibilite d);
      public void supprimerDisponibilite(int id) ;
    public int getIdParId(String id ) ;
}
