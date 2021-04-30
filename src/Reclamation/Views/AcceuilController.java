/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Reclamation;
import Reclamation.Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class AcceuilController implements Initializable {

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
         
        ServiceReclamation Rec = new ServiceReclamation();
        ObservableList<Reclamation> Reclamation = Rec.afficherReclamationList();
       new animatefx.animation.LightSpeedIn(btn_reclamation).setCycleDuration(7000).play();
            new animatefx.animation.LightSpeedIn(btn_garantie).setCycleDuration(4000).play();
        
        btn_garantie.setOnAction(event -> {

            try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_reclamation.setOnAction(event -> {

            try {
                 System.out.println(Rec.nombrerec());
                if(Rec.nombrerec()>0){
                   
                    Notifications.create()
                  .title("Notification")
                  .text("   VOUS AVEZ DES RECLAMATIONS !")
                  .darkStyle()
                  .position(Pos.TOP_RIGHT)
                  .showWarning();
                  }
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          
          
        
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
          
    }
    
}
