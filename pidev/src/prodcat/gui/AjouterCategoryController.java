/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import prodcat.entity.Category;
import prodcat.entity.Produits;
import prodcat.services.CategoryServices;
import prodcat.services.ProduitsServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterCategoryController implements Initializable {

    @FXML
    private Button Ajouter_btn;
    @FXML
    private TextField nom_text;
    @FXML
    private ImageView nomcheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add(ActionEvent event) {
        try {
            String nom = nom_text.getText();
            
            Category p = new Category(nom);
            CategoryServices PS= new CategoryServices();
            PS.ajoutercategory(p);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategory.fxml"));
            Parent root=loader.load();
            nom_text.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void nomcheck(KeyEvent event) {
    }
    

    
    
}
