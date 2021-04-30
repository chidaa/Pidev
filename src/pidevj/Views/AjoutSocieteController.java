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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class AjoutSocieteController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private Button ok;
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
    private TextField contact;
    @FXML
    private TextField nom;
    @FXML
    private ImageView check;
    @FXML
    private ImageView check1;
    @FXML
    private ImageView check2;
    @FXML
    private Button btn_soci;
    @FXML
    private Button btn_dispo;

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
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_soci.setOnAction(event -> {

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
         btn_dispo.setOnAction(event -> {

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
    private void ajoutsociete(ActionEvent event) {
           ServiceSocietelivraison sRes = new ServiceSocietelivraison();
        
        societelivraison Soc = new societelivraison();
if(testSaisie()) {
      Soc.setNom(nom.getText());
        Soc.setAdresse(adresse.getText());
        int jml = Integer.parseInt(contact.getText());
        Soc.setContact(jml);
       
        sRes.addSociete(Soc);
        
   Notifications notificationBuilder = Notifications.create()
            .title("AJOUT SOCIETE")
               .text("votre societe a etait bien enregistrer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        
        System.out.println("hello2");
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
    }
    
        
           
        
    private Boolean testNomSoc() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            check.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            check.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    private Boolean testAdresse() {
        int nbNonChar = 0;
        for (int i = 1; i < adresse.getText().trim().length(); i++) {
            char ch = adresse.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && adresse.getText().trim().length() >= 3) {
            check1.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            check1.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

    private Boolean testContact() {
        int nbNonChar = 0;
        for (int i = 1; i < contact.getText().length(); i++) {
            char ch = contact.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && contact.getText().length() >=8) {
            check2.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            check2.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    
     private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNomSoc()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
       
        if (!testAdresse()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
         if (!testContact()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        return  testAdresse() || testAdresse() || testContact() ;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    
}
