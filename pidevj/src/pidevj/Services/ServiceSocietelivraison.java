/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidevj.Entities.societelivraison;
import pidevj.utils.connexionBD;
import Interfaces.IServicesociete;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ServiceSocietelivraison  implements IServicesociete {
    
    Connection cnx;

    public ServiceSocietelivraison() {
        
                cnx = connexionBD.getInstance().getConnection();
    }
    
    public ObservableList<societelivraison> afficherSocieteList() {
ObservableList<societelivraison> societe = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `societelivraison`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                societelivraison soc = new societelivraison();
              soc.setId(rs.getInt(1));

                soc.setNom(rs.getString(2));
                soc.setAdresse(rs.getString(3));
                 soc.setContact(rs.getInt(4));

            
                societe.add(soc);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs predefinis");
            System.out.println(ex);
        }
        return societe;
    }
    
    public void addSociete(societelivraison s) {
 try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `societelivraison`( `nom`, `adresse`, `contact`) "
                    + "VALUES ('" 
                    + s.getNom()+ "','"
                    + s.getAdresse() + "','"
                    + s.getContact() +"')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } 
      catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        }   
        }

     public void modifierSociete(societelivraison s) {
try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  societelivraison SET nom  = '" + s.getNom()+ "', adresse = '" + s.getAdresse() + "', contact = '" + s.getContact() + "' WHERE id = '" + s.getId() + "'";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }    }
    
         public void supprimerSociete(int id) {
try {                 

            Statement st = cnx.createStatement();
            System.out.println("bbbbb1111bbbbbbbb");
            String query = "DELETE FROM `societelivraison` WHERE id='" + id + "'";
            st.executeUpdate(query);
            System.out.println("supprimer objectif pred succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }    }
     
      public int getIdParId(String id) {
try {
            Statement st = cnx.createStatement();
            
            String query = "select id from `societelivraison` WHERE  id LIKE '" + id + "'";
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
      
         public int getcontactParId(String id) {
try {
            Statement st = cnx.createStatement();
            
            String query = "select contact from `societelivraison` WHERE  id LIKE '" + id + "'";
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
         
         public ObservableList<String> getValuesSociete() {
       ObservableList<String> inter = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT nom FROM `societelivraison`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                inter.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return inter;    }
         
    @Override
          public societelivraison load_data(String nom) {
       Statement stm = null;
        societelivraison soci = new societelivraison();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `societelivraison` WHERE nom='" + nom + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                soci.setId(rs.getInt(1));
     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return soci;
    }

@Override
          public societelivraison load_data8(String id) {
       Statement stm = null;
        societelivraison soci = new societelivraison();
        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `societelivraison` WHERE id='" + id + "' OR nom='" + id +  "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                soci.setId(rs.getInt(1));

                 soci.setId(rs.getInt("id"));
                soci.setNom(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return soci;
    }


          
           public String load_data2(int id) {
         Statement stm = null;
        societelivraison s = new societelivraison();
        String nom_soc="";
        try {
            stm = cnx.createStatement();
            String query = "SELECT nom FROM `societelivraison` WHERE id='" + id +  "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
           nom_soc=rs.getString("nom");     
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return nom_soc;
    }

           
           public societelivraison load_data_modify3(int id) { // charger données Client pour la modification

        Statement stm = null;
        societelivraison c = new societelivraison();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `societelivraison`  WHERE id='" + id + "'";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setId(rst.getInt("id"));
                c.setNom(rst.getString("nom"));
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }
}
