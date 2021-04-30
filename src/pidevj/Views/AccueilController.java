/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class AccueilController implements Initializable {

   @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnSignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         btn_garantie.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          btnCustomers.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          btnMenus.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/disponibilite.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    
}
