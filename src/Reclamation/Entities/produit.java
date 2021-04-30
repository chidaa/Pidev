/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Entities;

/**
 *
 * @author elyes
 */
public class produit {
    private Integer id_produit;

    public Integer getId_produit() {
        return id_produit;
    }

    public void setId_produit(Integer id_produit) {
        this.id_produit = id_produit;
    }

    public produit() {
    }

    @Override
    public String toString() {
        return"" + id_produit ;
    }
    
}
