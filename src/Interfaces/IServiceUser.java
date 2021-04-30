/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Reclamation.Entities.user;
import javafx.collections.ObservableList;

/**
 *
 * @author elyes
 */
public interface IServiceUser {
    public ObservableList<String> getValuesUser();
     public user load_data(String email);
         public String load_data2(int id);
          public user load_data_modify(String id);
             //public int nb_clients();
}
