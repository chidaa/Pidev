/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Livraison;
import gestionpara.services.CommandeServices;
import gestionpara.services.LivraisonServices;
import gestionpara.services.LivreurServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class AjoutLivraisonController implements Initializable {

    @FXML
    private TextField etat_text;
    @FXML
    private DatePicker date_text;
    @FXML
    private TextField prix_text;
    @FXML
    private Button Ajouter_btn;
    @FXML
    private ComboBox<Integer> commandeids;
    @FXML
    private ComboBox<Integer> livreurids;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CommandeServices Cs = new CommandeServices();
            commandeids.getItems().addAll(Cs.displayids());
            LivreurServices ls = new LivreurServices();
             livreurids.getItems().addAll(ls.displayids());
          
        } catch (SQLException ex) {
            Logger.getLogger(AjoutLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @FXML
    void AddAction(ActionEvent event) throws SQLException, IOException {
        String etat = etat_text.getText();
        Date date_liv = Date.valueOf(date_text.getValue());
        float prix= Integer.valueOf(prix_text.getText()); 
        Livraison L = new Livraison (etat,date_liv,prix,Integer.valueOf(livreurids.getValue()),Integer.valueOf(commandeids.getValue()));
        LivraisonServices LS= new LivraisonServices();
        LS.add(L);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffLivraison.fxml"));
        Parent root=loader.load();
        etat_text.getScene().setRoot(root);
        
        
        
    }
       
}
