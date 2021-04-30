/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Reclamation;
import Reclamation.Services.ServiceMail;
import Reclamation.Services.ServiceReclamation;
import Reclamation.Services.ServiceUser;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class EnvrecController implements Initializable {

    @FXML
    private Button btnProfil11;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnProfil1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout11;
   @FXML
    private TableView<Reclamation> tabReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> colid_rec;
    @FXML
    private TableColumn<Reclamation, String> coldesc;
    @FXML
    private TableColumn<Reclamation, String> coltheme;
    @FXML
    private TableColumn<Reclamation, Integer> coluser;
    @FXML
    private Button envoyer_mail_client;
    @FXML
    private TextField object_mail_client;
    @FXML
    private TextArea subject_mail_client;
    @FXML
    private TextField emaill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceReclamation sRes = new ServiceReclamation();
        ObservableList<Reclamation> reclamation = sRes.afficherReclamationList();
        afficherReclamationList();
         btnProfil11.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                       new animatefx.animation.LightSpeedIn(page1).play();

            } catch (IOException ex) {
              Logger.getLogger(GarantieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
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
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
         Reclamation res = tabReclamation.getSelectionModel().getSelectedItem();
                  ServiceUser sc = new ServiceUser();

         
        emaill.setText(sc.load_data2(res.getUser_id().getIduser()));
    }
    
     private void afficherReclamationList() {
        ServiceReclamation Rec = new ServiceReclamation();
        ObservableList<Reclamation> reclamation = Rec.afficherReclamationList();
        colid_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        
        tabReclamation.setItems(reclamation);

    }
       @FXML
    private void envoyer_mail_client(ActionEvent event) {
         ServiceReclamation sc = new ServiceReclamation();
        ServiceMail sm = new ServiceMail();


        //Client c = sc.load_data_modify(id);
       String email = "elyes.ghrairi@esprit.tn";
           //  String email = emaill.getText();

                //emaill.getText();
        String object = object_mail_client.getText();
        String subject = subject_mail_client.getText();
        
               /* System.out.println(emaill.getText());
                System.out.println(object_mail_client.getText());
                System.out.println(subject_mail_client.getText());*/


        sm.envoyerMail(email, object, subject);
       
      // information_Box("Sucees", "Votre mail est bien recu");
     emaill.setText(null);
        subject_mail_client.setText(null);
        object_mail_client.setText(null);
        
    }

}
