/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import Desktop.Services.CategorieService;
import Desktop.entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyviewController implements Initializable {

   CheckBox check;
    @FXML
    private TextField gid;
    @FXML
    private TextField gnom;
    @FXML
    private TextField gtype;
    @FXML
    private Button buttonajouter;
    @FXML
    private Button buttonmodfier;
    CategorieService ms = new CategorieService();
    @FXML
    private TableColumn<Categorie, Integer> AfficheID;
    @FXML
    private TableColumn<Categorie, String> AfficheNom;
    @FXML
    private TableColumn<Categorie, String> AfficheType;
    @FXML
    private TableView<Categorie> CategoriesTable;
     ObservableList options = FXCollections.observableArrayList();
      @FXML
    private Button gsupp;
    @FXML
    private Button garticle;

    /**
     * Initializes the controller class.
     */
   
    Connection cn;
    @FXML
    private ComboBox<Integer> gComboBox;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Actualisercombox();
       view();
    }    
    public void Actualisercombox()
    {
        //combobox
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";
        try {
            cn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cn.createStatement().executeQuery("SELECT id FROM categorie");
            while (rs.next()) {
                //get string from db,whichever way 
                    options.add(new Categorie(rs.getInt("id")).getId());
            }
            
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        gComboBox.setItems(null);
        gComboBox.setItems(options);
        
    }

    private void sayhello(ActionEvent event) {
        System.out.println("");
    }
    @FXML
    private void AddAction(ActionEvent event) {
        
              Categorie l = new Categorie();
        String id = gid.getText();
        l.setId(Integer.parseInt(id));

       
        l.setNom(gnom.getText());
        l.setType(gtype.getText());
       
        
       ms.ajouter(l);
        
        JOptionPane.showMessageDialog(null, "Categorie ajouté");
        
            view();
            Actualisercombox();
        }
    
    public void view() {
        ObservableList<Categorie> list = ms.afficher();
        AfficheID.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
        AfficheNom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));
        AfficheType.setCellValueFactory(new PropertyValueFactory<Categorie, String>("type"));
        
        
        CategoriesTable.setItems(list);
        
        
    }
    @FXML
    private void ActionModfier(ActionEvent event) {
        
        CategorieService sp = new CategorieService();
        String s = gid.getText();
        int si = Integer.parseInt(s);
 
        sp.modifier(new Categorie(si, gnom.getText(), gtype.getText()));
        //JOptionPane.showMessageDialog(null, "Membre modifiée !");
        view();
        Actualisercombox();
          
    }
      @FXML
    private void ActionSupp(ActionEvent event) {
        
        CategorieService sp = new CategorieService();
        Categorie l = new Categorie();

//    String id = gid.getText();
//        l.setId_membre(Integer.parseInt(id));
        l.setId(gComboBox.getSelectionModel().getSelectedItem());
        
        sp.supprimer(l);
         Actualisercombox();
        view();
                

    }
        
    @FXML
    private void articleAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Articles.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
    }

    

