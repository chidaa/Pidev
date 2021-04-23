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
import prodcat.entity.Category;
import prodcat.utils.DataSource;

/**
 *
 * @author USER
 */
public class CategoryServices {
    
    private Statement ste;
    private PreparedStatement pst;
    
    private Connection connection;
    
    public CategoryServices(){
       connection=DataSource.getInstance().getCnx(); 
    }
    
  
   public void ajoutercategory(Category  c){
       String req="insert into category (nom) values (?)";
        try {            
            pst=connection.prepareStatement(req);
            pst.setString(1,c.getNom());
            pst.executeUpdate();
            System.out.println("categorie ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
   
   public List<Category> readALL(){
       String req="select* from category";
            List<Category> list=new ArrayList<>();
        try {
            ste=connection.createStatement();
           ResultSet rs = ste.executeQuery(req);
           
           while(rs.next()){
               list.add(new Category(rs.getString(1)));
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(CategoryServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list; 
   }
   
   public void supprimercategory(int s) {
        try {
            String requete = "DELETE FROM category WHERE id=?";
            PreparedStatement pst =DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,s);
            pst.executeUpdate();
            System.out.println("catégorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   public void modifiercategory(Category c) {
        try {
            String requete = " UPDATE category SET nom=?  WHERE id=?" ;
            PreparedStatement pst= DataSource.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(5,c.getId());
            pst.setString(1,c.getNom());
           
            System.out.println("catégorie modifiée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   
  
}
