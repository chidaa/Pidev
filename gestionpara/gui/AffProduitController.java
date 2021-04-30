/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Livreur;
import gestionpara.entities.Panier;
import gestionpara.entities.Produit;
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class AffProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> id_column;
    @FXML
    private TableColumn<Produit, String> nom_column;
    @FXML
    private TableColumn<Produit, String> prix_column;
    @FXML
    private Button ajout_btn;
    @FXML
    private Button panier_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ProduitServices LS = new ProduitServices();
            ObservableList<Produit> list = FXCollections.observableArrayList();
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        list.addAll(LS.readAll());
        table.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AffLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Produit lselected = (Produit) table.getSelectionModel().getSelectedItem();
                System.out.println("selected value " + lselected);
                
            }
           
        });
    }    

    @FXML
    private void ajouter_panier(ActionEvent event) throws SQLException {
       
        PanierServices sr = new PanierServices();
        Produit lselected = (Produit) table.getSelectionModel().getSelectedItem();
        
        
        if (sr.rechercheID(lselected.getId())==0){
        Panier P = new Panier(7,1,lselected.getId());
        sr.add(P);
        }
       
    }

    @FXML
    private void GoToPanier(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AffPanier.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
}
