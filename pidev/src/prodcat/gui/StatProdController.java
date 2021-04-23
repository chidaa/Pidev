/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import prodcat.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatProdController implements Initializable {
   @FXML
    private PieChart pieChart;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<PieChart.Data> pieChartData=FXCollections.observableArrayList();
                //new PieChart.Data("Cars",13),
                //new PieChart.Data("Sirate",13)

                //);
        //pieChart.setData(pieChartData
       String query = "SELECT nom, SUM(quantite) AS nbpre" +
"  FROM Produits" +
"  GROUP BY nom; ";
        try {
            PreparedStatement pst =DataSource.getInstance().getCnx().prepareStatement(query);
             ResultSet rs;
             try {
                //System.out.println("AHAYYYAA!!!!");
                //st=conn.createStatement();
                //System.out.println("AHAYYYAA222!!!!");
                rs = pst.executeQuery(query);
              
                      while (rs.next()) {
                          
                        pieChartData.add(new PieChart.Data(rs.getString(String.valueOf("nom")),rs.getInt("nbpre")));
               pieChart.setData(pieChartData);
               pieChart.setLegendSide(Side.RIGHT);
                      }
                      } catch (Exception ex) {
                //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void retourr(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root=loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
