/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcats.entities;

/**
 *
 * @author HP ENVY
 */
public class Livreur {
    private Integer ID;
    private Integer CIN;
    private String Nom;
    private String Prenom;
    private String Disponibilite;
    
    public Livreur(int ID) {
        this.ID = ID;
    }
    
    public Livreur(Integer ID, Integer CIN, String Nom, String Prenom, String Disponibilite) {
        this.ID = ID;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Disponibilite = Disponibilite;
    }
    
    public Livreur( Integer CIN, String Nom, String Prenom, String Disponibilite) {
       this.ID = ID;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Disponibilite = Disponibilite;
    }

    public Livreur() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCIN() {
        return CIN;
    }

    public void setCIN(Integer CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    @Override
    public String toString() {
        return "Livreur{" + "ID=" + ID + ", CIN=" + CIN + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Disponibilite=" + Disponibilite + '}';
    }

    
}
