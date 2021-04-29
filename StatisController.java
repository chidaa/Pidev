/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatisController implements Initializable {

    @FXML
    private PieChart stat;
    @FXML
    private LineChart<String, Double> statcourbe;
    
    
     
    ObservableList<PieChart.Data> piechartdata;
    XYChart.Series<String, Double> linechartdata = new XYChart.Series();
  
    Connection cnx;
    ResultSet rs;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
           // Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        try {
            loadDataLine();
        } catch (SQLException ex) {
           // Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statcourbe.getData().add(linechartdata);
    }    
    public void loadDataPie() throws SQLException{
        int i=0;
        int j=0;
        piechartdata = FXCollections.observableArrayList();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/pidev";
        
  
        
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from avis");
        rs=pst.executeQuery();
        
        while(rs.next()){
            if(rs.getString("type_avis").equals("like"))
            {
                i++;
                
            }
           if(rs.getString("type_avis").equals("dislike"))
            {
                j++;
                
            }
                            
        } 
        piechartdata.add(new PieChart.Data("like",i));
        piechartdata.add(new PieChart.Data("dislike",j));
    }
    public void loadDataLine() throws SQLException{
       // linechartdata = FXCollections.observableArrayList();
       String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/pidev";
        
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from rating");
        rs=pst.executeQuery();
        
        while(rs.next()){
            String s;
            s=String.valueOf(rs.getInt("id_article"));
            linechartdata.getData().add(new XYChart.Data<String, Double>(s,rs.getDouble("rate")));
//            name.add(rs.getString("nom_local"));
//            cap.add(rs.getInt("capacite"));             
        } 
//        linechart.getData().add(linechartdata);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml")); /* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
