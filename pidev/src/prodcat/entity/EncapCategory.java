/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.entity;

/**
 *
 * @author USER
 */
public class EncapCategory {
    private static int id;
    private static String nom;
    
    
    public EncapCategory(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public EncapCategory(int id) {
        this.id = id;
      
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapCategory.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        EncapCategory.nom = nom;
    }
    
    
}
