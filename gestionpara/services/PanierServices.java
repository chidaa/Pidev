/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.services;

import gestionpara.entities.Panier;
import gestionpara.entities.Produit;
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
public class PanierServices implements IService<Panier>{
    private Connection cnx;
    private Statement ste;

    public PanierServices() throws SQLException {
        cnx = BD.getInstance().getConnection();
        ste = cnx.createStatement();
    }


    @Override
    public void add(Panier t) throws SQLException {
       String requeteInsert = "INSERT INTO panier (quantite, produit_id) VALUES ('" + t.getQuantite() + "','" + t.getProduit_id() + "');";
       ste=cnx.createStatement();
       ste.executeUpdate(requeteInsert);
        }

        
    @Override
    public void delete(Panier t) throws SQLException {
        try{
        String requete = "DELETE FROM panier WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, t.getId());
        pst.executeUpdate();
        System.out.println("Cart Supprimé !");
        }catch(SQLException ex){
        System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public void update(Panier t) throws SQLException {
        System.out.print(t.toString());
        PreparedStatement pt = cnx.prepareStatement("update panier set quantite =? where id =? ");
        int x = t.getQuantite();
        pt.setInt(1, x);
        pt.setInt(2, t.getId());
        pt.executeUpdate(); 
     
    }

    @Override
    public List<Panier> readAll() throws SQLException {
            List<Panier> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM panier";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                list.add(new Panier(rs.getInt(1),rs.getInt(2),rs.getInt(3))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    public float CalculTotal() throws SQLException {
            List<Panier> list = new ArrayList<>();
            list= readAll();
            float S=0;
            ProduitServices PS= new ProduitServices();
            for(int i = 0 ; i < list.size(); i++)
            {   Produit pr= PS.rechercheID(list.get(i).getProduit_id());
                S=list.get(i).getQuantite()*pr.getPrix()+S;
            }
            System.out.print(S);
            return S;
    }
    
     public int rechercheID(int id) throws SQLException
     { List<Panier> list = new ArrayList<>();
         String query ="select * from panier where produit_id ='"+ id +"'";
          System.out.print(query);
    ResultSet  rs = ste.executeQuery(query);
    if (rs.first()!=false)
    {      
          
               System.out.print("HIIIII");
                
                Panier p =new Panier(rs.getInt("id"),(rs.getInt("quantite")+1),rs.getInt("produit_id")); 
                System.out.print("ici c le panier "+ p);
                update(p);
            
           
               
            //   Panier p = new Panier (list.get(0).getId(),list.get(0).getQuantite(),list.get(0).getProduit_id());
          //System.out.print(p.toString());
//
            
            return 1;
    }
            
    return 0;
     }
        
    
}
