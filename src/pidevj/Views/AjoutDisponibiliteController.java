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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidevj.Entities.disponibilite;
import pidevj.Services.ServiceDisponibilite;
import pidevj.Services.ServiceSocietelivraison;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class AjoutDisponibiliteController implements Initializable {

     @FXML
    private ComboBox<String> combo_societe;
    @FXML
    private TextField jours;
    @FXML
    private Button ok;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnProfil;
    @FXML
    private Button btnSignout;
    @FXML
    private TextField region;
    private ImageView check;
    private ImageView check1;
    private ImageView check2;
    @FXML
    private Button btn_Dispo;
    @FXML
    private ImageView check0;
    @FXML
    private ImageView check11;
    @FXML
    private ImageView check21;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceSocietelivraison soci  = new ServiceSocietelivraison();
      

        ObservableList<String> listsoci = soci.getValuesSociete();
        combo_societe.setItems(listsoci);
        System.out.println("hello");
        btn_garantie.setOnAction(event -> {

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
    private void ajoutdisponibilite(ActionEvent event) {
    
        ServiceDisponibilite sRes = new ServiceDisponibilite();
        ServiceSocietelivraison sUser = new ServiceSocietelivraison();

        disponibilite Rec = new disponibilite();
if(testSaisie()) {
        Rec.setSocietes_id(sUser.load_data(combo_societe.getValue()));
        
        Rec.setJours(jours.getText());
        Rec.setRegion(region.getText());
       

        sRes.addDisponibilite(Rec);
         Notifications notificationBuilder = Notifications.create()
            .title("AJOUT DISPONIBILITE")
               .text("votre disponibilite a etait bien enregistrer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        
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
    }
       private Boolean testSaisie() {
        String erreur = "";
      
        if (!testCombo_Soc()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >=3 \n");
        }
       
        if (!testJours()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >=3 \n");
        }
         if (!testRegion()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >=3 \n");
        }
        return  testCombo_Soc() && testJours() && testRegion() ;
    }
    
   private Boolean testCombo_Soc() {
        
        if (combo_societe.getValue() != null) {
                check0.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                check0.setImage(new Image("Images/erreurCheckMark.png"));
            }
                return false;
    }
    private Boolean testJours() {
        int nbNonChar = 0;
        for (int i = 1; i < jours.getText().trim().length(); i++) {
            char ch = jours.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && jours.getText().trim().length() >= 1) {
            check11.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            check11.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

    private Boolean testRegion() {
        int nbNonChar = 0;
        for (int i = 1; i < region.getText().trim().length(); i++) {
            char ch = region.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && region.getText().trim().length() >=3) {
            check21.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            check21.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
    
  

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
