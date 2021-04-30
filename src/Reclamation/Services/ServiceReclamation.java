/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import Reclamation.Entities.Reclamation;
import Reclamation.Entities.user;
import Interfaces.IServiceReclamation;
import Reclamation.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author admin
 */
public class ServiceReclamation implements IServiceReclamation {
        Connection cnx;

    public ServiceReclamation() {
        
                cnx = ConnexionSingleton.getInstance().getConnection();
    }
    
     @Override
    public void addReclamation(Reclamation r) {
      try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `reclamation`( `description`, `theme`, `email_id`) "
                    + "VALUES ('" 
                    + r.getDescription()+ "','"
                    + r.getTheme() + "','"
                    + r.getUser_id() +"')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } 
      catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }   
    
    }
    
    
        @Override
           public ObservableList<Reclamation> afficherReclamationList()
 {
       ObservableList<Reclamation> reclamation = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `reclamation`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                user user = new user();
              

                rec.setId(rs.getInt(1));
                rec.setDescription(rs.getString(2));
                 rec.setTheme(rs.getString(3));
                     user.setIduser(rs.getInt(4));
                rec.setUser_id(user);
                reclamation.add(rec);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs predefinis");
            System.out.println(ex);
        }
        return reclamation;
    }

    @Override
    public void modifierReclamation(Reclamation r) {
try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  reclamation SET description  = '" + r.getDescription() + "', theme = '" + r.getTheme() + "', email_id = '" + r.getUser_id() + "' WHERE id = '" + r.getId() + "'";;
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerReclamation(int id) {
try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `reclamation` WHERE id='" + id + "'";
            st.executeUpdate(query);
            System.out.println("supprimer objectif pred succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }
    }

    @Override
    public int getIdParDesc(String id) {
try {
            Statement st = cnx.createStatement();
            
            String query = "select id from `reclamation` WHERE  id LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return 0;
    }
    
    @Override
    public int nombrerec() {
int i=0;
        try {
            Statement st = cnx.createStatement();
            
            String query = "select * from `reclamation` ";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               i=i+1;
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return i ;
    }
    
    
     @Override
    public ObservableList<Reclamation> rechercherReclamation(String s) {
        ObservableList<Reclamation> reclamation =  FXCollections.observableArrayList();
       user u = new user();
       ServiceUser usr = new ServiceUser();



        try {
            Statement st = cnx.createStatement();
            String query = "select * from reclamation r  join user u ON u.id =r.email_id  WHERE r.id LIKE '%" + s + "%' OR r.description LIKE '%" + s + "%' OR r.theme LIKE '%" + s + "%' OR r.email_id LIKE '%" + s + "%'";
            ResultSet rs = st.executeQuery(query);  

            while (rs.next()) {
                  Reclamation r = new Reclamation();
            
                r.setId(rs.getInt(1));
                r.setDescription(rs.getString(2));
                 r.setTheme(rs.getString(3));
                r.setUser_id(usr.load_data_modify(rs.getString(4)));

                 
                reclamation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher reservation");
            System.out.println(ex);
        }
        return reclamation;
    }
      @Override
    public ObservableList<Reclamation> trierReservationid() {
        ObservableList<Reclamation> reclamation =  FXCollections.observableArrayList();
       user u = new user();
       ServiceUser usr = new ServiceUser();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `reclamation` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Reclamation r = new Reclamation();
            
                r.setId(rs.getInt(1));
                r.setDescription(rs.getString(2));
                 r.setTheme(rs.getString(3));
                r.setUser_id(usr.load_data_modify(rs.getString(4)));

                 
                reclamation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par id");
            System.out.println(ex);
        }
        return reclamation;
    }

    @Override
    public ObservableList<Reclamation> trierReservationtheme() {
  ObservableList<Reclamation> reclamation =  FXCollections.observableArrayList();
       user u = new user();
       ServiceUser usr = new ServiceUser();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `reclamation` ORDER BY theme";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Reclamation r = new Reclamation();
            
                r.setId(rs.getInt(1));
                r.setDescription(rs.getString(2));
                 r.setTheme(rs.getString(3));
                r.setUser_id(usr.load_data_modify(rs.getString(4)));

                 
                reclamation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par theme");
            System.out.println(ex);
        }
        return reclamation;    }

    @Override
    public ObservableList<Reclamation> trierReservationclient() {
  ObservableList<Reclamation> reclamation =  FXCollections.observableArrayList();
       user u = new user();
       ServiceUser usr = new ServiceUser();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `reclamation` ORDER BY email_id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Reclamation r = new Reclamation();
            
                r.setId(rs.getInt(1));
                r.setDescription(rs.getString(2));
                 r.setTheme(rs.getString(3));
                r.setUser_id(usr.load_data_modify(rs.getString(4)));

                 
                reclamation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par client");
            System.out.println(ex);
        }
        return reclamation;    
    
    }
    
    
       @Override
    public List<Reclamation> afficherRecList() {
        List<Reclamation> reclamation = new ArrayList<>();
           ServiceUser user = new ServiceUser();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `reclamation`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            
                 Reclamation r = new Reclamation();
            
                r.setId(rs.getInt(1));
                r.setDescription(rs.getString(2));
                 r.setTheme(rs.getString(3));
                r.setUser_id(user.load_data_modify(rs.getString(4)));

                 
                reclamation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher reservation");
            System.out.println(ex);
        }
        return reclamation;
    }
    
    public ObservableList<Integer> getnb_reclamation_Client(){
 
  ObservableList<Integer> reserv = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT  COUNT(*) as i FROM reclamation GROUP BY email_id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                reserv.add(rs.getInt("i"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return reserv;
 
    
    }
    
    
    
    public int nb_clients()
    {
   
        
      int i=0;
        Statement stm = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM reclamation GROUP BY email_id";
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
