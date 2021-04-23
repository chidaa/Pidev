/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import prodcat.entity.Produits;
import prodcat.services.ProduitsServices;
import prodcat.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterProduitsController implements Initializable {

    
    @FXML
    private TextField nom_text;
    @FXML
    private TextField quantite_text;
    @FXML
    private TextField prix_text;
   // @FXML
   // private ComboBox<?> category_text;
    
   @FXML
    private Button Ajouter_btn;
   final FileChooser fileChooser = new FileChooser();
    String imagepath = "null";
    @FXML
    private Button image;
    @FXML
    private ImageView imageview;
    @FXML
    private ComboBox<String> cat_up;
    @FXML
    private ImageView nomcheck;
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String requete = "SELECT nom FROM category ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                cat_up.getItems().add(rs.getString("nom"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        // TODO
    } 
    @FXML
    void Add(ActionEvent event) throws SQLException, IOException {
        if (testSaisie()){
            
        
        String nom = nom_text.getText();
        int quantite= Integer.valueOf(quantite_text.getText());
        int prix= Integer.valueOf(prix_text.getText()); 
        String categorie = cat_up.getValue();        
        Produits p = new Produits(nom,quantite,prix, categorie,imagepath);
        ProduitsServices PS= new ProduitsServices();
        PS.ajouterproduit(p);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
        Parent root=loader.load();
        nom_text.getScene().setRoot(root);
    
}

    @FXML
    private void upload(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagepath = file.toURI().toString();
            Image image = new Image(imagepath);
             imageview.setImage(image); 
        }
        
        
    }
    private boolean testNom() {
     
        int nbNonChar = 0;
        for (int i = 1; i < nom_text.getText().trim().length(); i++) {
            char ch = nom_text.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom_text.getText().trim().length() >= 3) {
          nomcheck.setImage(new Image("images/checkmark.png"));
            return true;
        } else {
           nomcheck.setImage(new Image("images/alertemark.png"));
               
            return false;

        }
        
    }

    @FXML
    private void nomcheck(KeyEvent event) {
        testNom();
    }
    private Boolean testSaisie() {
        String erreur = "";
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        
        

        if ( (!testNom())   ) {
           
           
           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
       
       
        }
        return testNom();
    }
    
}
    
    

