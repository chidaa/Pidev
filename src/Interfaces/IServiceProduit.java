/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Reclamation.Entities.produit;
import javafx.collections.ObservableList;

/**
 *
 * @author elyes
 */
public interface IServiceProduit {
    public ObservableList<String> getValuesProduit();
     public produit load_data(String id);
         public String load_data2(int id);
          public produit load_data_modify(String id);
          public int nb_produit();
}
