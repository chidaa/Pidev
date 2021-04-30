/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Commande;
import gestionpara.entities.Livraison;
import gestionpara.entities.Livreur;
import gestionpara.services.CommandeServices;
import gestionpara.services.LivraisonServices;
import gestionpara.services.LivreurServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class AffLivraisonController implements Initializable {

    @FXML
    private TableView<Livraison> table;
    @FXML
    private TableColumn<Livraison, String> column_etat;
    @FXML
    private TableColumn<Livraison, Date> column_date;
    @FXML
    private TableColumn<Livraison, Integer> column_prix;
    @FXML
    private TableColumn<Livraison,Integer> column_id;
    private TextField id_supp;
    @FXML
    private Button supp_btn;
    @FXML
    private TextField id_mod;
    @FXML
    private TextField prix_mod;
    @FXML
    private DatePicker date_mod;
    @FXML
    private Button mod_btn;
    @FXML
    private TextField etat_mod;
    @FXML
    private Button ajout_btn;
    @FXML
    private Button retour_btn;
    @FXML
    private TableColumn<Livraison, String> commande_id;
    @FXML
    private TableColumn<Livraison, String> livreur_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LivraisonServices LS = new LivraisonServices();
            ObservableList<Livraison> list = FXCollections.observableArrayList();
            
        column_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        column_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        column_date.setCellValueFactory(new PropertyValueFactory< >("datelivraison"));
        column_prix.setCellValueFactory(new PropertyValueFactory<>("Prix_total"));
                        livreur_id.setCellValueFactory(new PropertyValueFactory<>("livreur_id"));

                commande_id.setCellValueFactory(new PropertyValueFactory<>("Commande_id"));

        list.addAll(LS.readAll());
        table.setItems(list);
         
        } catch (SQLException ex) {
            Logger.getLogger(AffLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         mod_btn.setDisable(true);
        supp_btn.setDisable(true);
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Livraison lselected = (Livraison) table.getSelectionModel().getSelectedItem();
                System.out.println("selected value " + lselected);
                if(lselected!=null){
                id_mod.setText(String.valueOf(lselected.getID()));
                id_mod.setDisable(true);
                etat_mod.setText(String.valueOf(lselected.getEtat()));
                date_mod.setValue(LocalDate.of(lselected.getDatelivraison().getYear(),lselected.getDatelivraison().getMonth(),lselected.getDatelivraison().getDay()));
                prix_mod.setText(String.valueOf(lselected.getPrix_total()));
                mod_btn.setDisable(false);
                supp_btn.setDisable(false);
               
                }else{
                    supp_btn.setDisable(true);
                    mod_btn.setDisable(true);
                    id_mod.clear();
                    etat_mod.clear();
                    prix_mod.clear();
                
                }
            }
           
        });
    }


       

    @FXML
    private void supp(ActionEvent event) throws SQLException { 
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer l'utilisateur ?") ;//problem
         LivraisonServices L=new LivraisonServices();
          Livraison lselected = (Livraison) table.getSelectionModel().getSelectedItem();
         L.delete(lselected);
         afficher();
    }
    
    private void afficher()
    {try {
            LivraisonServices LS = new LivraisonServices();
            ObservableList<Livraison> list = FXCollections.observableArrayList();
            
        column_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        column_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        column_date.setCellValueFactory(new PropertyValueFactory< >("datelivraison"));
        column_prix.setCellValueFactory(new PropertyValueFactory<>("Prix_total"));
        list.addAll(LS.readAll());
        table.setItems(list);
         
        } catch (SQLException ex) {
            Logger.getLogger(AffLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
       LivraisonServices LS = new LivraisonServices();
       java.sql.Date date_liv = java.sql.Date.valueOf(date_mod.getValue());
        float prix= Integer.valueOf(prix_mod.getText()); 
        Livraison L = new Livraison (Integer.valueOf(id_mod.getText()),etat_mod.getText(),date_liv,prix);

        LS.update(L);
        afficher();
    }

    @FXML
    private void GoToLivraison(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjoutLivraison.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void GoToDash(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    
    
}
