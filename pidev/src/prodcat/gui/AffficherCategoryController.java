/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcat.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import prodcat.entity.Category;
import prodcat.entity.EncapCategory;
import prodcat.entity.EncapProduits;
import prodcat.entity.Produits;
import prodcat.services.CategoryServices;
import prodcat.services.ProduitsServices;
import prodcat.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffficherCategoryController implements Initializable {

    @FXML
    private TableView<Category> table1;
    @FXML
    private Button modifier_btn1;
    @FXML
    private Button supprimer_btn1;
    @FXML
    private Button back1;
    ObservableList<Category> list = FXCollections.observableArrayList();
    @FXML
    private TextField rechercher1;
    @FXML
    private Button chart1;
    @FXML
    private Button imprimer1;
    @FXML
    private Button triDESC;
    @FXML
    private Button asc;
    @FXML
    private TableColumn<Category, Integer> id_column1;
    @FXML
    private TableColumn<Category, String> nom_column1;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_column1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        
        try {
            String requete = "SELECT * FROM Category ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"),rs.getString("nom"));
                list.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        table1.setItems(list);
        // TODO
    }    

    @FXML
    private void mod1(ActionEvent event) {
        list=table1.getSelectionModel().getSelectedItems();
        
        EncapCategory.setNom(list.get(0).getNom());
        
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduits.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
            ModifierProduitsController MP=new ModifierProduitsController();
            
          
            int id;
            id=list.get(0).getId();
            MP.setId(id);
            System.out.println("test");
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supp1(ActionEvent event) throws SQLException { Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer le produit?") ;//problem
         CategoryServices pr=new CategoryServices();
         Category c=new Category() ;
         
         list=table1.getSelectionModel().getSelectedItems();
          
            int id;
            id=list.get(0).getId();
            CategoryServices PS=new CategoryServices();
            PS.supprimercategory(id);
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffficherCategory.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour1(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercher1(ActionEvent event) {CategoryServices re= new CategoryServices();
     List<Category>results = new ArrayList<>();
     results = re.readALL();
     FilteredList<Category> filteredData = new FilteredList<>(list , b -> true);
     Category r = new Category();
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Category -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
 
				  if (Category.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}                                 
				     else  
				    	 return false; // Does not match
                                  
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Category> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table1.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table1.setItems(sortedData);
    }

    @FXML
    private void chart1(ActionEvent event) {
    }

    @FXML
    private void imprimer1(ActionEvent event) {
        saveHeader("category.csv");
        try {
            String requete = "SELECT * FROM category ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getString("nom"), "produits.csv");
            }
            JOptionPane.showMessageDialog(null, "Record saved");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        private void saveHeader(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("id      ,     nom   ");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int id,String nom, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println( +id + "," + nom);
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    
    
}
