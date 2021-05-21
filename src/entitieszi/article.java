/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitieszi;

/**
 *
 * @author User
 */
public class article {
    private int id;
    private int categorie_id;
private String sujet;
private String image;

    private String description;
    public article(int id, int categorie_id, String sujet, String description) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.sujet = sujet;
        this.description = description;
    }

    public article(int id, int categorie_id, String sujet, String description, String image) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.sujet = sujet;
        this.image = image;
        this.description = description;
    }

    public article(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public article() {
    }

    public article(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}

