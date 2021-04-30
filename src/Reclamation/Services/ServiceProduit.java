/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import Interfaces.IServiceProduit;
import Reclamation.Entities.produit;
import Reclamation.Entities.user;
import Reclamation.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author elyes
 */
public class ServiceProduit implements IServiceProduit{
     Connection cnx;

    public ServiceProduit() {
                 cnx = ConnexionSingleton.getInstance().getConnection();

    }

    @Override
    public ObservableList<String> getValuesProduit() {
        
              ObservableList<String> inter = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT id FROM `produit`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                inter.add(rs.getString("id"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return inter;
    }

    @Override
    public produit load_data(String id) {
        Statement stm = null;
        produit p = new produit();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `produit` WHERE id='" + id + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                p.setId_produit(rs.getInt(1));
     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return p;
    }

    @Override
    public String load_data2(int id) {
         Statement stm = null;
        produit u = new produit();
        String email="";
        try {
            stm = cnx.createStatement();
            String query = "SELECT id FROM `produit` WHERE id='" + id +  "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
           email=rs.getString("id");     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return email;
    }
    public produit load_data_modify(String id) { // charger données Client pour la modification

        Statement stm = null;
        produit c = new produit();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `produit`  WHERE id='" + id +  "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId_produit(rst.getInt("id"));
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }
    
     public int nb_produit(){
     
    int i=0;
        Statement stm = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `produit` ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
               i++;              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return i;
     }
}

