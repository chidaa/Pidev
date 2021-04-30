/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Reclamation;
import Reclamation.Services.ServiceReclamation;
import Reclamation.Services.ServiceUser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
public class AjoutReclamationController implements Initializable {

    @FXML
    private ComboBox<String> email;
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
    private TextField description;
    @FXML
    private TextField theme;
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
        // TODO
         ServiceUser user  = new ServiceUser();
      

        ObservableList<String> listuser = user.getValuesUser();
        email.setItems(listuser);
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
          btnProfil.setOnAction(event -> {

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
    private void ajoutreclamation(ActionEvent event) {
          
        ServiceReclamation sRes = new ServiceReclamation();
        ServiceUser sUser = new ServiceUser();

        Reclamation Rec = new Reclamation();

        Rec.setUser_id(sUser.load_data(email.getValue()));
        Rec.setDescription(description.getText());
        Rec.setTheme(theme.getText());
       
        
        sRes.addReclamation(Rec);
        send();

        System.out.println("hello2");
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
    
     public void send(){
          String ACCOUNT_SID =
            "ACc2a4d0ddf8f51c9f15eee57f920d3a0f";
     String AUTH_TOKEN =
            "51c6801089ffbbb05af3664a79583394";
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      
       Message message = Message.creator(new PhoneNumber("+21654428182"),
        new PhoneNumber("+12057079946"),"Nouvelle reclamation faite!").create();
    
    }
    
}
