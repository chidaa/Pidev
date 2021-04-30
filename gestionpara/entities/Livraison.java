/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.entities;

import java.util.Date;



/**
 *
 * @author HP ENVY
 */
public class Livraison {
    int ID;
    String Etat;
    Date datelivraison;
    float Prix_total;
       int livreur_id;

    public int getLivreur_id() {
        return livreur_id;
    }

    public void setLivreur_id(int livreur_id) {
        this.livreur_id = livreur_id;
    }

    int Commande_id;

    public Livraison(String Etat, Date datelivraison, float Prix_total, int livreur_id, int Commande_id) {
        this.Etat = Etat;
        this.datelivraison = datelivraison;
        this.Prix_total = Prix_total;
        this.livreur_id = livreur_id;
        this.Commande_id = Commande_id;
    }

    public Livraison(int ID, String Etat, Date datelivraison, float Prix_total, int livreur_id, int Commande_id) {
        this.ID = ID;
        this.Etat = Etat;
        this.datelivraison = datelivraison;
        this.Prix_total = Prix_total;
        this.livreur_id = livreur_id;
        this.Commande_id = Commande_id;
    }

  
    public Livraison() {
    }

   

    public Livraison(int ID, String Etat, Date datelivraison, float Prix_total, int Commande_id ) {
        this.ID = ID;
        this.Etat = Etat;
        this.datelivraison = datelivraison;
        this.Prix_total = Prix_total;
        this.Commande_id = Commande_id;
    }
    
     public Livraison( int ID,String Etat, Date datelivraison, float Prix_total) {
         this.ID=ID;
        this.Etat = Etat;
        this.datelivraison = datelivraison;
        this.Prix_total = Prix_total;
         
    }
    
    public Livraison( String Etat, Date datelivraison, float Prix_total, int Commande_id) {
        this.Etat = Etat;
        this.datelivraison = datelivraison;
        this.Prix_total = Prix_total;
         this.Commande_id = Commande_id;
    }
    
     public Livraison( int ID) {
         this.ID = ID;
      
    }

    public int getCommande_id() {
        return Commande_id;
    }

    public void setCommande_id(int Commande_id) {
        this.Commande_id = Commande_id;
    }
    
    
     public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public Date getDatelivraison() {
        return datelivraison;
    }

    public void setDatelivraison(Date datelivraison) {
        this.datelivraison = datelivraison;
    }

    public float getPrix_total() {
        return Prix_total;
    }

    public void setPrix_total(float Prix_total) {
        this.Prix_total = Prix_total;
    }

    @Override
    public String toString() {
        return "Livraison{" + "ID=" + ID + ", Etat=" + Etat + ", datelivraison=" + datelivraison + ", Prix_total=" + Prix_total + ", livreur_id=" + livreur_id + ", Commande_id=" + Commande_id + '}';
    }
    
}
