/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class connexionBD {
     public String url = "jdbc:mysql://127.0.0.1:3306/pideve";
    public String login="root";
    public String pwd="";
    public Connection cnx;
        static connexionBD instance=null;

    public connexionBD() {
        
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connextion etablie !");
        } catch (SQLException ex) {
            System.out.println("Erreur de connextion");
            System.out.println(ex.getMessage());
        }
    }
    public static connexionBD getInstance(){
if(instance==null){

instance=new connexionBD();
}
return instance ;
}
public Connection getConnection(){
return cnx;
}
    
    
}
