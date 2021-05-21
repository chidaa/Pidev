/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.services;

import java.util.ArrayList;
import java.util.List;
import prodcats.entities.Produits;

/**
 *
 * @author USER
 */
public class PanierService {
     public static List<Produits> livs= new ArrayList();   

    public PanierService() {
    }

    public List<Produits> getLivs() {
        return livs;
    }
    
      public void add(Produits L)
      {livs.add(L);}
    
}
