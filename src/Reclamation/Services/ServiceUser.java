/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import Interfaces.IServiceUser;
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
public class ServiceUser  implements IServiceUser{
    Connection cnx;

    public ServiceUser() {
                 cnx = ConnexionSingleton.getInstance().getConnection();

    }

    @Override
    public ObservableList<String> getValuesUser() {
        
              ObservableList<String> inter = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT email FROM `user`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                inter.add(rs.getString("email"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return inter;
    }

    @Override
    public user load_data(String email) {
        Statement stm = null;
        user user = new user();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE email='" + email + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                user.setIduser(rs.getInt(1));
     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return user;
    }

    @Override
    public String load_data2(int id) {
         Statement stm = null;
        user u = new user();
        String email="";
        try {
            stm = cnx.createStatement();
            String query = "SELECT email FROM `user` WHERE id='" + id +  "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
           email=rs.getString("email");     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return email;
    }
    public user load_data_modify(String id) { // charger données Client pour la modification

        Statement stm = null;
        user c = new user();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `user`  WHERE id='" + id +  "' ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setIduser(rst.getInt("id"));
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }
     
    
}
