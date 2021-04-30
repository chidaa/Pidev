/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import Interfaces.IServiceGarantie;
import Reclamation.Entities.Garantie;
import Reclamation.Entities.Reclamation;
import Reclamation.Entities.produit;
import Reclamation.Entities.user;
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
 * @author elyes
 */
public class ServiceGarantie implements IServiceGarantie{
    
    Connection cnx;

    public ServiceGarantie() {
        
                cnx = ConnexionSingleton.getInstance().getConnection();
    }

    @Override
    public ObservableList<Garantie> afficherGarantieList() {
 ObservableList<Garantie> garantie = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `garantie`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Garantie rec = new Garantie();
                user user = new user();
                produit produit = new produit();
              

                rec.setId(rs.getInt(1));
             //   rec.setEmail(rs.getString(2));
                             rec.setDescription(rs.getString(2));
                             user.setIduser(rs.getInt(3));

                produit.setId_produit(rs.getInt(4));
                rec.setId_produit(produit);
                
                 

                     
                rec.setUser_id(user);
                
                garantie.add(rec);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher objectifs predefinis");
            System.out.println(ex);
        }
        return garantie;

    }

    @Override
    public void addGarantie(Garantie r) {
 try {
            Statement st = cnx.createStatement();
            String query = "INSERT INTO `garantie`( `description`, `id_client`, `id_produit_id`) "
                    + "VALUES ('" 
                     + r.getDescription()+ "','"
                     + r.getUser_id()+ "','"
                     + r.getId_produit()+"')";
            st.executeUpdate(query);
            System.out.println("ajout avec succes");
        } 
      catch (SQLException ex) {
            System.out.println("erreur ajouter objectifs");
            System.out.println(ex);
        } 
    }

    @Override
    public void modifierGarantie(Garantie r) {
try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String query = "UPDATE  garantie SET description  = '" + r.getDescription() + "', id_client = '" + r.getUser_id() + "', id_produit_id = '" + r.getId_produit()+ "' WHERE id = '" + r.getId()+ "'";;
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur modifier objectif");
            System.out.println(ex);
        }    }

    @Override
    public void supprimerGarantie(int id) {
try {
            Statement st = cnx.createStatement();
            String query = "DELETE FROM `garantie` WHERE id='" + id + "'";
            st.executeUpdate(query);
            System.out.println("supprimer objectif pred succes");
        } catch (SQLException ex) {
            System.out.println("erreur supprimer objectif");
            System.out.println(ex);
        }    }

    @Override
    public int getIdParDesc(String id) {
       try {
            Statement st = cnx.createStatement();
            
            String query = "select id from `garantie` WHERE  id LIKE '" + id + "'";
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
    public ObservableList<Garantie> rechercherGarantie(String s) {
       ObservableList<Garantie> gar =  FXCollections.observableArrayList();
        ServiceUser usr = new ServiceUser();
        ServiceProduit prod = new ServiceProduit();
       


        try {
            Statement st = cnx.createStatement();
            String query = "select * from garantie r  join user u ON u.id =r.id_client  join produit p ON p.id =r.id_produit_id  WHERE r.id LIKE '%" + s + "%' OR r.description LIKE '%" + s + "%' OR r.id_client LIKE '%" + s + "%' OR r.id_produit_id LIKE '%" + s + "%'  ";
            ResultSet rs = st.executeQuery(query);  

            while (rs.next()) {
                  Garantie o = new Garantie();
            
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setUser_id(usr.load_data_modify(rs.getString(3)));
                o.setId_produit(prod.load_data_modify(rs.getString(4)));


                
                gar.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur rechercher reservation");
            System.out.println(ex);
        }
        return gar;
    }
    
     @Override
    public ObservableList<Garantie> trierGarantieid() {
        ObservableList<Garantie> gar =  FXCollections.observableArrayList();
        ServiceUser usr = new ServiceUser();
        ServiceProduit prod = new ServiceProduit();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `garantie` ORDER BY id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Garantie o = new Garantie();
            
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setUser_id(usr.load_data_modify(rs.getString(3)));
                o.setId_produit(prod.load_data_modify(rs.getString(4)));


                
                gar.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par id");
            System.out.println(ex);
        }
        return gar;
    }

    @Override
    public ObservableList<Garantie> trierGarantieproduit() {
         ObservableList<Garantie> gar =  FXCollections.observableArrayList();
        ServiceUser usr = new ServiceUser();
        ServiceProduit prod = new ServiceProduit();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `garantie` ORDER BY id_produit_id";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Garantie o = new Garantie();
            
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setUser_id(usr.load_data_modify(rs.getString(3)));
                o.setId_produit(prod.load_data_modify(rs.getString(4)));


                
                gar.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par produit");
            System.out.println(ex);
        }
        return gar;    }

    @Override
    public ObservableList<Garantie> trierGarantieclient() {
          ObservableList<Garantie> gar =  FXCollections.observableArrayList();
        ServiceUser usr = new ServiceUser();
        ServiceProduit prod = new ServiceProduit();
       
        try {
            Statement st = cnx.createStatement();
            String query = "select * from `garantie` ORDER BY id_client";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 Garantie o = new Garantie();
            
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setUser_id(usr.load_data_modify(rs.getString(3)));
                o.setId_produit(prod.load_data_modify(rs.getString(4)));


                
                gar.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur trier reservation par client");
            System.out.println(ex);
        }
        return gar;    
    
    }
      @Override
    public List<Garantie> afficherGarList() {
        ObservableList<Garantie> gar =  FXCollections.observableArrayList();
        ServiceUser usr = new ServiceUser();
        ServiceProduit prod = new ServiceProduit();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `garantie`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            
                 Garantie o = new Garantie();
            
                o.setId(rs.getInt(1));
                o.setDescription(rs.getString(2));
                o.setUser_id(usr.load_data_modify(rs.getString(3)));
                o.setId_produit(prod.load_data_modify(rs.getString(4)));


                
                gar.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("erreur afficher garantie");
            System.out.println(ex);
        }
        return gar ;
    }
    
    
    public int nb_Garantie()
    {
   
        
      int i=0;
        Statement stm = null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `garantie` ";
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
