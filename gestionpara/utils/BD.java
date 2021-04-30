/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP ENVY
 */
public class BD {
    
    private static BD instance;
    private Connection cnx;
    
    String url = "jdbc:mysql://localhost:3306/pidev";
    
    private BD(){
        System.out.println("Connexion en cours .. ");
        try {
            cnx = DriverManager.getConnection(url, "root", "");
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println("Connexion impossible");
            System.err.println(ex);
        }
    }
    
    public static BD getInstance() {
        if (instance == null) {
            instance = new BD();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}
