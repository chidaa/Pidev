/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.services;

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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP ENVY
 */
public class LivreurServices implements IService<Livreur>{
    
    private Connection cnx;
    private Statement ste;
    ObservableList<Livreur> obList = FXCollections.observableArrayList();

    public LivreurServices() throws SQLException {
        cnx = BD.getInstance().getConnection();
        ste = cnx.createStatement();
    }
    
    
    
    @Override
     public void add(Livreur L) throws SQLException {
       String requeteInsert = "INSERT INTO livreur (cin, nom, prenom, disponibilite) VALUES ('" + L.getCIN() + "','" + L.getNom() + "', '" + L.getPrenom() + "', '" + L.getDisponibilite() + "');";
       ste=cnx.createStatement();
       ste.executeUpdate(requeteInsert);
    }

   

    @Override
    public void delete(Livreur L) throws SQLException {
         PreparedStatement pt = cnx.prepareStatement("delete from livreur where id =? ");
        pt.setInt(1, L.getID());
        pt.executeUpdate();
    }

    @Override
    public void update(Livreur t) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("update livreur set cin=?, nom=?, prenom=?, disponibilite=?  where id =? ");
        pt.setInt(1, t.getCIN());
        pt.setString(2, t.getNom());
        pt.setString(3, t.getPrenom());
        pt.setString(4, t.getDisponibilite());
        pt.setInt(5, t.getID());
        pt.executeUpdate();
    }

    
     @Override
    public List<Livreur> readAll() throws SQLException {
         List<Livreur> l = new ArrayList<Livreur>();
        PreparedStatement pt = cnx.prepareStatement("select * from livreur ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
                
            Livreur L = new Livreur(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getNString(5));
            l.add(L);
        }
        return l;
    }
    
     public ObservableList readAll2() throws SQLException {
         
         List<Livreur> l = new ArrayList<Livreur>();
        PreparedStatement pt = cnx.prepareStatement("select * from livreur ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
                
            Livreur L = new Livreur(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getNString(5));
            obList.add(L);
        }
        return obList;
    }
     
     public List<Integer> displayids() throws SQLException {
        List<Integer> l = new ArrayList<Integer>();
        PreparedStatement pt = cnx.prepareStatement("select id from livreur ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
             l.add(rs.getInt(1));
        }
        return l;
    }
     
     public ObservableList<Integer> getnb_disponibilite_livreur(){
 
  ObservableList<Integer> Livreur = FXCollections.observableArrayList();
        try {
            String query = "Select l.disponibilite , count(*) as i From Livreur l Group By l.disponibilite  " ;
        //SELECT COUNT(m.id) as nb,s.titre FROM medecin m JOIN medecin_specialite ms JOIN specialite s WHERE ms.medecin_id = m.id and s.id=ms.specialite_id GROUP BY ms.specialite_id
             
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Livreur.add(rs.getInt("i"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return Livreur;
 }
    public int nb_Livreur(){
     
    int i=0;

        try {
            String query = "SELECT * FROM Livreur ";
            ResultSet rst = ste.executeQuery(query);

            while (rst.next()) {
               i++;              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return i;
     }
    
     public ObservableList<String> getValuesDis(){
 
  ObservableList<String> Livreur = FXCollections.observableArrayList();
        try {
            String query = "Select disponibilite From Livreur " ;
             
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Livreur.add(rs.getString("disponibilite"));
            }

        } catch (SQLException ex) {
            System.out.println("erreur get values objectifs (pour comboBox)");
            System.out.println(ex);
        }
        return Livreur;
 }
}
