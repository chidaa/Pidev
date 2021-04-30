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
import java.util.List;

/**
 *
 * @author HP ENVY
 */
public class CommandeServices implements IService<Commande> {
     private Connection cnx;
    private Statement ste;

    public CommandeServices () throws SQLException {
        cnx = BD.getInstance().getConnection();
        ste = cnx.createStatement();
    }
    
    @Override
     public void add(Commande C) throws SQLException {
       String requeteInsert = "INSERT INTO commande (prixtotal, etat) VALUES ('" + C.getPrix_Total() + "', '" + C.getEtat() + "');";
       ste=cnx.createStatement();
       ste.executeUpdate(requeteInsert);
    }

    
 
    @Override
    public void delete(Commande C) throws SQLException {
         PreparedStatement pt = cnx.prepareStatement("delete from commande where id =? ");
        pt.setInt(1, C.getID());
        pt.executeUpdate();
    }

    @Override
    public void update(Commande t) throws SQLException {
        PreparedStatement pt = cnx.prepareStatement("update commande set etat =? where id =? ");
        pt.setString(1, t.getEtat());
        pt.setInt(2, t.getID());
        pt.executeUpdate();
    }

    @Override
    public List<Commande> readAll() throws SQLException {
        List<Commande> l = new ArrayList<Commande>();
        PreparedStatement pt = cnx.prepareStatement("select * from commande ");
        ResultSet rs = pt.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            float prixtotal = rs.getInt(2);
             String etat = rs.getString(3);            
            
            Commande C = new Commande(id, prixtotal, etat);
            l.add(C);
        }
        return l;
    }

    public void update(Livraison C) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public List<Integer> displayids() throws SQLException {
        List<Integer> l = new ArrayList<Integer>();
        PreparedStatement pt = cnx.prepareStatement("SELECT id from commande");
        ResultSet rs = pt.executeQuery();
        LivraisonServices Ls = new LivraisonServices();
        while(rs.next()){
            if(Ls.chercherParIdComande(rs.getInt("id"))==false)
             l.add(rs.getInt(1));
        }
        return l;
    }

}
