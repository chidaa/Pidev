/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.entities;

/**
 *
 * @author USER
 */
public class Produits {
     private int id;
    private String nom;
    private int quantite;
    private int prix;
    private String categorie;
    private String image;
 

 
   public Produits(){
   }

    public Produits(int id, String nom, int quantite, int prix, String categorie) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
        
    }

    public Produits(String nom, int quantite, int prix, String categorie) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
       
    }
  

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    @Override
    public String toString() {
        return "produits{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + ", categorie=" + categorie + '}';
    }
    
    
    
}
