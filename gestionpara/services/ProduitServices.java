/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.services;

import gestionpara.entities.Commande;
import gestionpara.entities.Produit;
import gestionpara.utils.BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP ENVY
 */
public class ProduitServices {
    private Connection cnx;
    private Statement ste;

    public ProduitServices() throws SQLException {
        cnx = BD.getInstance().getConnection();
        ste = cnx.createStatement();
    }
    
     public List<Produit> readAll() throws SQLException {
        List<Produit> l = new ArrayList<Produit>();
        PreparedStatement pt = cnx.prepareStatement("select * from produit ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String nom = rs.getString(2);
             float prix = rs.getInt(3);            
            
            Produit C = new Produit(id, nom, prix);
            l.add(C);
        }
        return l;
    }
     
     public Produit rechercheID(int id) throws SQLException
     {
         String query ="select * from produit where id ='"+id+"'";
    
    ResultSet  rs = ste.executeQuery(query);
    Produit pr=new Produit();
            while(rs.next()){
                pr.setId(rs.getInt("id"));
                pr.setNom(rs.getString("nom"));
                pr.setPrix(rs.getInt("prix"));
            }
            
    return pr;
     }
    
}
