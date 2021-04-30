/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Reclamation;
import Reclamation.Entities.user;
import Reclamation.Services.ServiceReclamation;
import Reclamation.Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private Button ok;
    @FXML
    private Button ok1;
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
 //   private ComboBox<> email;
    private TextField description;
    private TextField theme;
    @FXML
    private ComboBox<String> combo_email;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_theme;
    @FXML
    private TextField txt_id;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ServiceUser user = new ServiceUser();

        ObservableList<String> listuser = user.getValuesUser();
        combo_email.setItems(listuser);
        
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
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
    private void modifierreservation(ActionEvent event) {
       ServiceUser user = new ServiceUser();
        
        ServiceReclamation sRes = new ServiceReclamation();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Reclamation Res = new Reclamation();
            
        Res.setId(sRes.getIdParDesc(txt_id.getText()));
         System.out.println(sRes.getIdParDesc(txt_id.getText()));

        Res.setDescription(txt_description.getText());
        System.out.println(txt_description.getText());

        Res.setTheme(txt_theme.getText());
        System.out.println(txt_theme.getText());



        Res.setUser_id(user.load_data(combo_email.getValue()));
                System.out.println(user.load_data(combo_email.getValue()));

        
        sRes.modifierReclamation(Res);
        }else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void suppreclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceReclamation sRes = new ServiceReclamation();
            System.out.println(txt_id.getText());
             System.out.println(sRes.getIdParDesc(txt_id.getText()));
            sRes.supprimerReclamation(sRes.getIdParDesc(txt_id.getText()));
            
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    public void selected_item(Integer id ,String theme, String description,user U) {
    
      ServiceUser user = new ServiceUser();
         
        txt_id.setText(""+id);
        txt_theme.setText(theme);
        txt_description.setText(description);
        combo_email.setValue(user.load_data2(U.getIduser()));
    
    }
}