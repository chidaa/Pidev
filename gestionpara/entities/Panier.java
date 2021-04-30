/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.entities;

/**
 *
 * @author HP ENVY
 */
public class Panier {
    private int id;
    private int quantite;
    private int produit_id;

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", quantite=" + quantite + ", produit_id=" + produit_id + '}';
    }

    
    public Panier() {
    }

    public Panier(int quantite, int produit_id) {
        this.quantite = quantite;
        this.produit_id = produit_id;
    }

    public Panier(int id, int quantite, int produit_id) {
        this.id = id;
        this.quantite = quantite;
        this.produit_id = produit_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }
    
    
}
