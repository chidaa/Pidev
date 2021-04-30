/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Livraison;
import gestionpara.entities.Livreur;
import gestionpara.services.LivraisonServices;
import gestionpara.services.LivreurServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class AjoutLivreurController implements Initializable {

    @FXML
    private TextField cin_text;
    @FXML
    private TextField nom_text;
    @FXML
    private TextField prenom_text;
    @FXML
    private TextField dispo_text;
    @FXML
    private Button ajout_btn;
    @FXML
    private ImageView checknum;
    @FXML
    private ImageView checknom;
    @FXML
    private ImageView checkprenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddLivreur(ActionEvent event) throws SQLException, IOException {
        if(testSaisie())
        { Livreur L = new Livreur (Integer.valueOf(cin_text.getText()),nom_text.getText(),prenom_text.getText(),dispo_text.getText());
        LivreurServices LS= new LivreurServices();
        LS.add(L);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffLivreur.fxml"));
        Parent root=loader.load();
        dispo_text.getScene().setRoot(root);
        }
    }
    private Boolean testCin() {
        if (cin_text.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < cin_text.getText().trim().length(); i++) {
                char ch = cin_text.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                checknum.setImage(new Image("Images/checkMark.png") {});
               
                return true;
            } else {
                checknum.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (cin_text.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
                            checknum.setImage(new Image("Images/erreurCheckMark.png"));

            return false;
        }

        return true;

    }
private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom_text.getText().trim().length(); i++) {
            char ch = nom_text.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom_text.getText().trim().length() >= 3) {
            checknom.setImage(new Image("Images/checkMark.png"));
           
            return true;
        } else {
            checknom.setImage(new Image("Images/erreurCheckMark.png"));
         
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
private Boolean testPrenom() {
        
    int nbNonChar = 0;
        for (int i = 1; i < prenom_text.getText().trim().length(); i++) {
            char ch = prenom_text.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom_text.getText().trim().length() >= 3) {
            checkprenom.setImage(new Image("Images/checkMark.png"));
           
           
            return true;
        } else {
           checkprenom.setImage(new Image("Images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
private Boolean testSaisie() {
        String erreur = "";
       
        if (!testCin()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
       
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
         
       

        return   testCin()  && testNom() && testPrenom();
    }

    
}
