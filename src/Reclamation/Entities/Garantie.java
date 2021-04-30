/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Entities;

import java.util.Objects;

/**
 *
 * @author elyes
 */
public class Garantie {
    private Integer id;
    private produit id_produit;
    private user user_id;
    private String description;

    public Garantie() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public produit getId_produit() {
        return id_produit;
    }

    public void setId_produit(produit id_produit) {
        this.id_produit = id_produit;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Garantie(produit id_produit, user user_id, String description) {
        this.id_produit = id_produit;
        this.user_id = user_id;
        this.description = description;
    }



    @Override
    public String toString() {
        return "Garantie{" + "id=" + id + ", id_produit=" + id_produit + ", user_id=" + user_id + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Garantie other = (Garantie) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
