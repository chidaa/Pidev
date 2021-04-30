/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;



import Reclamation.Entities.Garantie;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Reclamation.Entities.Reclamation;
import Reclamation.Services.ServiceGarantie;
import Reclamation.Services.ServiceProduit;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class AjoutGarantieController implements Initializable {

    @FXML
    private ComboBox<String> combo_produit;
    @FXML
    private TextField description;
    @FXML
    private Button ok;
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
ServiceUser user  = new ServiceUser();
      ServiceProduit prod  = new ServiceProduit();


        ObservableList<String> listuser = user.getValuesUser();
        combo_user.setItems(listuser);
        
        ObservableList<String> listprod = prod.getValuesProduit();
        combo_produit.setItems(listprod);
        System.out.println("hello");   
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
    private void ajoutgarantie(ActionEvent event) {
             ServiceGarantie sRes = new ServiceGarantie();
        ServiceUser sUser = new ServiceUser();
                ServiceProduit prod = new ServiceProduit();


        Garantie Rec = new Garantie();

        Rec.setUser_id(sUser.load_data(combo_user.getValue()));
         Rec.setId_produit(prod.load_data(combo_produit.getValue()));

        Rec.setDescription(description.getText());
        
       
        
        sRes.addGarantie(Rec);

        System.out.println("hello2");
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
    
   
}
