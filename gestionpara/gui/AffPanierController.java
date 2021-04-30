/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Commande;
import gestionpara.entities.Livreur;
import gestionpara.entities.Panier;
import gestionpara.entities.Produit;
import gestionpara.services.CommandeServices;
import gestionpara.services.LivreurServices;
import gestionpara.services.PanierServices;
import gestionpara.services.ProduitServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class AffPanierController implements Initializable {

    @FXML
    private TableView<Panier> table;
    @FXML
    private TableColumn<Panier, String> pr_column;
    @FXML
    private TableColumn<Panier, String> qt_column;
    @FXML
    private Button confirmer_btn;
    @FXML
    private TextField total;
    @FXML
    private Button supp_btn;
    @FXML
    private Button retour;
    @FXML
    private TextField qt_mod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            PanierServices LS = new PanierServices();
            ObservableList<Panier> list = FXCollections.observableArrayList();
        pr_column.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        qt_column.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        list.addAll(LS.readAll());
        table.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AffLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            PanierServices PS;
            PS = new PanierServices();
            float x= PS.CalculTotal();
            total.setText(String.valueOf(x));
                total.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(AffPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }    

    @FXML
    private void passer_commande(ActionEvent event) throws SQLException, IOException {
        PanierServices PS= new PanierServices();
         float x= PS.CalculTotal();
        Commande C= new Commande (x,"en cours");
        CommandeServices CS= new CommandeServices();
        CS.add(C);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));
        Parent root=loader.load();
        total.getScene().setRoot(root);
        
    }
     public void afficher() throws SQLException
{
    PanierServices LS = new PanierServices();
            ObservableList<Panier> list = FXCollections.observableArrayList();
             pr_column.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        qt_column.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        list.addAll(LS.readAll());
        table.setItems(list);
       
}

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer l'utilisateur ?") ;//problem
         PanierServices L=new PanierServices();
           Panier lselected = (Panier) table.getSelectionModel().getSelectedItem();

         
         L.delete(lselected);
         afficher();
         PanierServices PS;
            PS = new PanierServices();
            float x= PS.CalculTotal();
            total.setText(String.valueOf(x));
                total.setDisable(true);
    }

    @FXML
    private void GoToAffProd(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AffProduit.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        PanierServices LS = new PanierServices();
        Panier lselected = (Panier) table.getSelectionModel().getSelectedItem();
        Panier L = new Panier(lselected.getId(),Integer.valueOf(qt_mod.getText()),lselected.getProduit_id());
        LS.update(L);
        afficher();
        PanierServices PS;
            PS = new PanierServices();
            float x= PS.CalculTotal();
            total.setText(String.valueOf(x));
                total.setDisable(true);
    }
    
}
