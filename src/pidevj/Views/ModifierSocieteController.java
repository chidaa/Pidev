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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidevj.Entities.societelivraison;
import pidevj.Services.ServiceSocietelivraison;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class ModifierSocieteController implements Initializable {

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
    private TextField txt_adresse;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_contact;
    @FXML
    private TextField txt_nom;
    @FXML
    private Button btn_Soc;
    @FXML
    private Button btn_Dispo;
    @FXML
    private ImageView check02;
    @FXML
    private ImageView check12;
    @FXML
    private ImageView check22;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        
             btn_garantie.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void modifiersociete(ActionEvent event) {
        
        ServiceSocietelivraison sRes = new  ServiceSocietelivraison();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cette societe?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            societelivraison Res = new societelivraison();
        Res.setId(sRes.getIdParId(txt_id.getText()));
         System.out.println(sRes.getIdParId(txt_id.getText()));
         

        Res.setNom(txt_nom.getText());
        System.out.println(txt_nom.getText());

        Res.setAdresse(txt_adresse.getText());
        System.out.println(txt_adresse.getText());
       
      
int jml = Integer.parseInt(txt_contact.getText());
        Res.setContact(jml);         System.out.println(sRes.getcontactParId(txt_contact.getText()));


        
        sRes.modifierSociete(Res);
         Notifications notificationBuilder = Notifications.create()
            .title("MODIFIER SOCIETE")
               .text("votre societe a etait bien mise a jour  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        }else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    

    @FXML
    private void suppsociete(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
       ServiceSocietelivraison sRes = new ServiceSocietelivraison();
            System.out.println(txt_id.getText());
             System.out.println(sRes.getIdParId(txt_id.getText()));
            sRes.supprimerSociete(sRes.getIdParId(txt_id.getText()));
                        //System.out.println("****"+sRes.getIdParDesc(txt_id.getText()));
      Notifications notificationBuilder = Notifications.create()
            .title("SUPPRESSION SOCIETE")
               .text("votre societe a etait bien supprimer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    public void selected_item(Integer id ,String nom, String adresse,Integer contact) {
    
         
        txt_id.setText(""+id);
        txt_nom.setText(nom);
        txt_adresse.setText(adresse);
        txt_contact.setText(""+contact);
    
    }
    
}
