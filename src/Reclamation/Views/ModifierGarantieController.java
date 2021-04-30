/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Garantie;
import Reclamation.Entities.produit;
import Reclamation.Entities.user;
import Reclamation.Services.ServiceGarantie;
import Reclamation.Services.ServiceProduit;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ModifierGarantieController implements Initializable {

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
    @FXML
    private ComboBox<String> combo_produit;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_id;
    @FXML
    private ComboBox<String> combo_user;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnProfil1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceUser user = new ServiceUser();
  ServiceProduit prod = new ServiceProduit();
  
        ObservableList<String> listuser = user.getValuesUser();
        combo_user.setItems(listuser);
        
        ObservableList<String> listprod = prod.getValuesProduit();
        combo_produit.setItems(listprod);
        
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
          btnProfil1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/acceuil.fxml"));
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
        ServiceProduit prod = new ServiceProduit();
        ServiceGarantie sRes = new ServiceGarantie();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Garantie Res = new Garantie();
        Res.setId(sRes.getIdParDesc(txt_id.getText()));
         System.out.println(sRes.getIdParDesc(txt_id.getText()));

        Res.setDescription(txt_description.getText());
        System.out.println(txt_description.getText());

        



        Res.setUser_id(user.load_data( combo_user.getValue()));
                System.out.println(user.load_data( combo_user.getValue()));
                
 Res.setId_produit(prod.load_data(combo_produit.getValue()));
                System.out.println(prod.load_data(combo_produit.getValue()));
        
        sRes.modifierGarantie(Res);
        }else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void suppgarantie(ActionEvent event) {
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceGarantie sRes = new ServiceGarantie();
            System.out.println(txt_id.getText());
             System.out.println(sRes.getIdParDesc(txt_id.getText()));
            sRes.supprimerGarantie(sRes.getIdParDesc(txt_id.getText()));
            
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
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
    

public void selected_item(Integer id ,produit P, String description,user U) {
    
      ServiceUser user = new ServiceUser();
            ServiceProduit prod = new ServiceProduit();

         System.out.println(id + description  + P + U);
        txt_id.setText(""+id);
        combo_produit.setValue(prod.load_data2(P.getId_produit()));
        txt_description.setText(description);
        combo_user.setValue(user.load_data2(U.getIduser()));
    
    }
}