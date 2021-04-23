/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.services;

import prodcat.entity.Produits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import prodcat.utils.DataSource;

/**
 *
 * @author USER
 */
public class ProduitsServices {
    
    private Statement ste;
    private PreparedStatement pst;
    
    private Connection connection;
    
    public ProduitsServices(){
       connection=DataSource.getInstance().getCnx(); 
    }
    
  
   public void ajouterproduit(Produits p){
       String req="insert into produits (nom,quantite,prix,categorie,image) values(?,?,?,?,?)";
        try {            
            pst=connection.prepareStatement(req);
            pst.setString(1,p.getNom());
            pst.setInt(2,p.getQuantite());
            pst.setInt(3,p.getPrix());
            pst.setString(4,p.getCategorie());
            pst.setString(5,p.getImage());
            
            
            pst.executeUpdate();
            System.out.println("produit ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
   
   public List<Produits> readALL(){
       String req="select* from produits";
            List<Produits> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
           ResultSet rs = ste.executeQuery(req);
           
           while(rs.next()){
               list.add(new Produits(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
   }
   
   public void supprimerPorduits(int s) {
        try {
            String requete = "DELETE FROM produits WHERE id=?";
            PreparedStatement pst =DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,s);
            pst.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   public void modifierProduit(Produits p) {
        try {
            String requete = " UPDATE produits SET nom=?, quantite=?,prix=?,categorie=? WHERE id=?" ;
            PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(5,p.getId());
            pst.setString(1,p.getNom());
            pst.setInt(2,p.getQuantite());
            pst.setInt(3,p.getPrix());
            pst.setString(4,p.getCategorie());
            pst.executeUpdate();
            System.out.println("produit modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
   
   
  

