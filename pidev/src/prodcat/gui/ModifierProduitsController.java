/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import prodcat.entity.EncapProduits;
import prodcat.entity.Produits;
import prodcat.services.ProduitsServices;
import prodcat.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierProduitsController implements Initializable {

    @FXML
    private TextField nom_txt;
    @FXML
    private TextField quant_txt;
    @FXML
    private TextField prix_txt;
    @FXML
    private ComboBox<String> cat_txt;
    @FXML
    private Button fin;
    private static int id=0;
    @FXML
    private Button retour;
    final FileChooser fileChooser = new FileChooser();
    String imagepath = "null";
    
   String Imagepath ;
    @FXML
    private Button imagee;
    @FXML
    private ImageView imageview;

    public void setId(int id) {
        this.id = id;
    }
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     nom_txt.setText(EncapProduits.getNom());
     quant_txt.setText(Integer.toString(EncapProduits.getQuantite()));
     prix_txt.setText(Integer.toString(EncapProduits.getPrix()));
     cat_txt.setValue(EncapProduits.getCategorie());
     Imagepath = EncapProduits.getImage();
      try {
            String requete = "SELECT nom FROM category ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                cat_txt.getItems().add(rs.getString("nom"));
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
     
    }    

    @FXML
    private void fin(ActionEvent event) {
        Produits p = new Produits(id, nom_txt.getText(), Integer.parseInt(quant_txt.getText()), Integer.parseInt(prix_txt.getText()),cat_txt.getValue(),imagepath);
            ProduitsServices PS = new ProduitsServices();
            PS.modifierProduit(p);
            JOptionPane.showMessageDialog(null, "produit modifié!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            try {
                Parent root = loader.load();
                nom_txt.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        
    }

    @FXML
    private void retour(ActionEvent event) {
        
    }

    @FXML
    private void uploadd(ActionEvent event) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagepath = file.toURI().toString();
            Image image = new Image(imagepath);
             imageview.setImage(image); 
        }
    }

    
    
}
