/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevMobile.Entities;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class societelivraison {
       private int id;
    private String nom;
    private String adresse;
    private int contact;
    
     public societelivraison(){
}
     
     public societelivraison(int id ,String nom, String adresse, int contact  ) {
        this.id=id;
        this.nom=nom;
        this.adresse=adresse;
        this.contact=contact;
        
    }
      public societelivraison(int id ) {
        this.id=id;
       
    }
       public societelivraison(String id ) {
        this.nom=id;
       
    }
      public societelivraison(String nom, String adresse, int contact  ) {
        this.nom=nom;
        this.adresse=adresse;
        this.contact=contact;
        
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "societelivraison{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", contact=" + contact + '}';
    }



    
    
}
