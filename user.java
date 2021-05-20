/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author elyes
 */
public class user {
   
        private Integer iduser;
                private String email_user;

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }


    public user() {
    }

    public user(Integer iduser) {
        this.iduser = iduser;
    }
 public user(String iduser) {
        this.email_user = iduser;
    }
    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }
        
@Override
    public String toString() {
        return ""+iduser;
    }
}
