/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.services;

import java.util.ArrayList;
import java.util.List;
import prodcats.entities.Livreur;

/**
 *
 * @author HP ENVY
 */
public class LivreurFavoriService {
    public static List<Livreur> livs= new ArrayList();   

    public LivreurFavoriService() {
    }

    public List<Livreur> getLivs() {
        return livs;
    }
    
      public void add(Livreur L)
      {livs.add(L);}
    
}
