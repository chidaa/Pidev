/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Marker;
import gestionpara.entities.Mapa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class DashboardController implements Initializable {

    @FXML
    private Button livreur_btn;
    @FXML
    private ImageView imgView;
    @FXML
    private Button com_btn;
    @FXML
    private Button livraison_btn;
    @FXML
    private Button map_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Image img= new Image(getClass().getResourceAsStream("1(1).png"));
       imgView.setImage(img);
    }    

    @FXML
    private void GoToLivreur(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AffLivreur.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void GoToCom(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AffCommande.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void GoToLivraison(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AffLivraison.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void GoToMap(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MapCord.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
       
    }
    
    
}
