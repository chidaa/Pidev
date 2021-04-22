/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Views;

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
import pidevj.Entities.disponibilite;
import pidevj.Entities.societelivraison;
import pidevj.Services.ServiceDisponibilite;
import pidevj.Services.ServiceSocietelivraison;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ModifierDisponibiliteController implements Initializable {

     @FXML
    private Button ok;
    @FXML
    private Button ok1;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnSignout;
    @FXML
    private ComboBox<String> combo_societe;
    @FXML
    private TextField txt_jours;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_region;
    @FXML
    private Button btn_Soc;
    @FXML
    private Button btn_Dispo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSocietelivraison societe = new ServiceSocietelivraison();

        ObservableList<String> listsoci = societe.getValuesSociete();
        combo_societe.setItems(listsoci);
        
              btn_garantie.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          btn_Soc.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
           btn_Dispo.setOnAction(event -> {

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
    private void modifierdisponibilite(ActionEvent event) {
        ServiceSocietelivraison soci = new ServiceSocietelivraison();
        
        ServiceDisponibilite sRes = new ServiceDisponibilite();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cette disponibilite?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            disponibilite Res = new disponibilite();
            
        Res.setId(sRes.getIdParId(txt_id.getText()));
         System.out.println(sRes.getIdParId(txt_id.getText()));
         
         Res.setSocietes_id(soci.load_data(combo_societe.getValue()));
                System.out.println(soci.load_data(combo_societe.getValue()));
                
        Res.setJours(txt_jours.getText());
        System.out.println(txt_jours.getText());
        
        Res.setRegion(txt_region.getText());
        System.out.println(txt_region.getText());



        

        
        sRes.modifierDisponibilite(Res);
        }else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/disponibilite.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void suppdisponibilite(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceDisponibilite sRes = new ServiceDisponibilite();
            System.out.println(txt_id.getText());
             System.out.println(sRes.getIdParId(txt_id.getText()));
            sRes.supprimerDisponibilite(sRes.getIdParId(txt_id.getText()));
            
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/disponibilite.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    

   public void selected_item(Integer id ,societelivraison s, String jours,String region) {
    
      ServiceSocietelivraison soci = new ServiceSocietelivraison();
         
        txt_id.setText(""+id);
        txt_jours.setText(jours);
        txt_region.setText(region);
       // combo_societe.setValue(soci.load_data2(s.getId()));
    combo_societe.setValue(soci.load_data2(s.getId()));
    }
    
}
