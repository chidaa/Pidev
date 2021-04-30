/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Services;

import Interfaces.IServicedisponibilite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidevj.Entities.disponibilite;
import pidevj.Entities.societelivraison;
import pidevj.utils.connexionBD;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ServiceDisponibilite implements IServicedisponibilite{
    Connection cnx;

    public ServiceDisponibilite() {
        
                cnx = connexionBD.getInstance().getConnection();
    }
    
     public void addDisponibilite(disponibilite d) {
      try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `disponibilite`( `societes_id`, `jours`, `region`) "
                    + "VALUES ('" 
                    + d.getSocietes_id().getId()+ "','"
                    + d.getJours() + "','"
                    + d.getRegion() +"')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } 
      catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }   
    
    }
     
            
           public ObservableList<disponibilite> afficherDisponibiliteList()
 {
       ObservableList<disponibilite> disponibilite = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `disponibilite`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                disponibilite rec = new disponibilite();
                ServiceSocietelivraison s = new ServiceSocietelivraison();
              

                rec.setId(rs.getInt(1));
             //   rec.setEmail(rs.getString(2));
//             s.setId(rs.getInt(2));
//                rec.setSocietes_id(s);
               rec.setSocietes_id(s.load_data_modify3(rs.getInt(2)));
                rec.setJours(rs.getString(3));
                 rec.setRegion(rs.getString(4));

            // Integer x=rs.getInt(4);
                     
//                System.out.println(rec.getUser_id());
                disponibilite.add(rec);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs predefinis");
            System.out.println(ex);
        }
        return disponibilite;
    }


    @Override
    public void modifierDisponibilite(disponibilite d) {
try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  disponibilite SET Jours  = '" 
                    + d.getJours() + "', region = '"
                    + d.getRegion() + "', societes_id = '"
                    + d.getSocietes_id().getId()+ "' WHERE id = '" + d.getId() + "'";;
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }
    }

    
    public void supprimerDisponibilite(int id) {
try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `disponibilite` WHERE id='" + id + "'";
            st.executeUpdate(query);
            System.out.println("supprimer objectif pred succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }
    }

    
    public int getIdParId(String id) {
try {
            Statement st = cnx.createStatement();
            
            String query = "select id from `disponibilite` WHERE  id LIKE '" + id + "'";
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
     public ObservableList<Integer> getnb_disponibilite_Region(){
 
  ObservableList<Integer> disponibilite = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT  COUNT(*) as i FROM `disponibilite` GROUP BY region";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                disponibilite.add(rs.getInt("i"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return disponibilite;
 }
    
    
     public int nb_Region(){
     
    int i=0;
        Statement stm = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `disponibilite` group by region ";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
               i++;              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return i;
     }
    
     public ObservableList<String> getValuesSociete() {
       ObservableList<String> inter = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT region FROM `disponibilite` group by region ";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                inter.add(rs.getString("region"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return inter;    }
     
}
