/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.services;

import gestionpara.entities.Commande;
import gestionpara.entities.Livraison;
import gestionpara.entities.Livreur;
import gestionpara.iservices.IService;
import gestionpara.utils.BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP ENVY
 */
public class LivraisonServices implements IService<Livraison> {
     private Connection cnx;
    private Statement ste;

    public LivraisonServices() throws SQLException {
        cnx = BD.getInstance().getConnection();
        ste = cnx.createStatement();
    }
    
     @Override
     public void add(Livraison L) throws SQLException {
         java.sql.Date date_sql = new java.sql.Date(L.getDatelivraison().getTime());
       String requeteInsert = "INSERT INTO `livraison` (etat, datelivraison, prixtotal,livreur_id,commande_id) VALUES ('" + L.getEtat() + "','"+ date_sql + "', '" + L.getPrix_total() + "','"+ L.getLivreur_id()+"', '"  + L.getCommande_id() +  "');";
       ste=cnx.createStatement();
       ste.executeUpdate(requeteInsert);
    }

   
  

    @Override
    public void delete(Livraison L) throws SQLException {
         PreparedStatement pt = cnx.prepareStatement("delete from livraison where id =? ");
        pt.setInt(1, L.getID());
        pt.executeUpdate();
    }

    @Override
    public void update(Livraison t) throws SQLException {
         PreparedStatement pt = cnx.prepareStatement("update livraison set etat=?, datelivraison=?, prixtotal=?  where id =? ");
        pt.setString(1, t.getEtat());
        pt.setDate(2, new java.sql.Date(t.getDatelivraison().getTime()));
        pt.setFloat(3, t.getPrix_total());
        pt.setInt(4, t.getID());
        pt.executeUpdate();
    }

    @Override
    public List<Livraison> readAll() throws SQLException {
         List<Livraison> l = new ArrayList<Livraison>();
        PreparedStatement pt = cnx.prepareStatement("select * from livraison ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
                
            Livraison L = new Livraison(rs.getInt(1) , rs.getString(2), rs.getDate(3), rs.getInt(4),rs.getInt("livreur_id"),rs.getInt(6));
            l.add(L);
            System.out.println(L);
        }
        return l;
    }

    boolean chercherParIdComande(int Id) {
        boolean test=false;
 List<Integer> l = new ArrayList<Integer>();
        PreparedStatement pt;
         try {
             pt = cnx.prepareStatement("select commande_id from livraison where commande_id=? ");
            pt.setInt(1, Id);
             ResultSet rs = pt.executeQuery();
        while(rs.next()){
             l.add(rs.getInt(1));
             return true;
        }
         } catch (SQLException ex) {
             Logger.getLogger(LivraisonServices.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return test;
    }
    
}
