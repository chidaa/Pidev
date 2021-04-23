/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author USER
 */
public class EncapProduits {
    private static int id;
    private static String nom;
    private static int quantite;
    private static int prix;
    private static String categorie;
    private static String image;
    
   public EncapProduits(){
   }

    public EncapProduits(int id, String nom, int quantite, int prix, String categorie,String image) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image ;
    }

    public EncapProduits(String nom, int quantite, int prix, String categorie,String image) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
        this.image = image ;
    }
    
    

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapProduits.id = id;
    }
    
    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        EncapProduits.nom = nom;
    }

    public static int getQuantite() {
        return quantite;
    }

    public static void setQuantite(int quantite) {
        EncapProduits.quantite = quantite;
    }

    public static int getPrix() {
        return prix;
    }

    public static void setPrix(int prix) {
        EncapProduits.prix = prix;
    }

    public static String getCategorie() {
        return categorie;
    }

    public static void setCategorie(String categorie) {
        EncapProduits.categorie = categorie;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        EncapProduits.image = image;
    }
    

    @Override
    public String toString() {
        return "produits{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + ", categorie=" + categorie + '}';
    }
    
    
     
}
