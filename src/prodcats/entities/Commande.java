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
public class Commande {
    int ID;
    float Prix_Total;
    String Etat;

    public Commande(int ID) {
        this.ID = ID;
        
    }
    
    public Commande(int ID, float Prix_Total, String Etat) {
        this.ID = ID;
        this.Prix_Total = Prix_Total;
        this.Etat = Etat;
    }

    public Commande(int ID, String Etat) {
        this.ID = ID;
        this.Etat = Etat;
    }
    
    
    
    public Commande( float Prix_Total, String Etat) {
        
        this.Prix_Total = Prix_Total;
        this.Etat = Etat;
    }

    public Commande() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrix_Total() {
        return Prix_Total;
    }

    public void setPrix_Total(float Prix_Total) {
        this.Prix_Total = Prix_Total;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }
    
}
