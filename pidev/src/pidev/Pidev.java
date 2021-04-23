/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import prodcat.entity.Produits;
import prodcat.services.ProduitsServices;
import prodcat.utils.DataSource;

/**
 *
 * @author USER
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Singleton
        //DataSource ds1=DataSource.getInstance();
        //
        //Produits p1 =new Produits("creme", 2 , 88 , "svr");
        //Produits p1=new Produits(1,"zzzzz",5000, 888, "java");
        ProduitsServices ps=new ProduitsServices();
        //ps.ajouterproduit(p1);
        //ps.readALL().forEach(e->System.out.println(e));
        //ps.supprimerPorduits("wassim");
       // ps.modifierProduit(p1);
    }
    
    
    
}
 