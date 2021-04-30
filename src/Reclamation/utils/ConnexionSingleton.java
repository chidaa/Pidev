/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author SeifBS
 */
public class ConnexionSingleton {
    final static String URL="jdbc:mysql://localhost:3306/pideve";
    final static String LOGIN="root";
    final static String PWD="";
    static ConnexionSingleton instance=null;
    private Connection cnx;
    public ConnexionSingleton(){
    try {
    cnx=DriverManager.getConnection(URL,LOGIN,PWD);
    System.out.println("Ma Connexion est etablie "); 
   }
    catch (SQLException ex){
   
   System.out.println("Pas De Connexion");
   }
    
    
    }
    
public static ConnexionSingleton getInstance(){
if(instance==null){

instance=new ConnexionSingleton();
}
return instance ;
}
public Connection getConnection(){
return cnx;
}

}
    

