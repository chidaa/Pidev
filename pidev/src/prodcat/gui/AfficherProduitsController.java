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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class AfficherProduitsController implements Initializable {
    
     Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TableView<Produits> table;
    @FXML
    private Button modifier_btn;
    @FXML
    private TableColumn<Produits, Integer> id_column;
    @FXML
    private TableColumn<Produits, String> nom_column;
    @FXML
    private TableColumn<Produits,Integer> quantite_column;
    @FXML
    private TableColumn<Produits,Integer> prix_column;
    @FXML
    private TableColumn<Produits,String> categorie_column;
    @FXML
    private Button supprimer_btn;
    ObservableList<Produits> list = FXCollections.observableArrayList();
    @FXML
    private Button back;
    @FXML
    private TextField rechercher;
    @FXML
    private TableColumn<Produits,String> image_column;
    @FXML
    private Button chart;
    @FXML
    private Button imprimer;
    @FXML
    private Button asc;
    @FXML
    private Button triDESC;
    @FXML
    private Button backk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
            
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantite_column.setCellValueFactory(new PropertyValueFactory< >("quantite"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("prix"));
        categorie_column.setCellValueFactory(new PropertyValueFactory<>("categorie"));
         image_column.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        try {
            String requete = "SELECT * FROM produits ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Produits p = new Produits(rs.getInt("id"),rs.getString("nom"),rs.getInt("quantite"),rs.getInt("prix"),rs.getString("categorie"),rs.getString("image"));
                list.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        table.setItems(list);
        
        
        
           
    }
     @FXML
    private void supp(ActionEvent event) throws SQLException { Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer le produit?") ;//problem
         ProduitsServices pr=new ProduitsServices();
         Produits p=new Produits() ;
         
         list=table.getSelectionModel().getSelectedItems();
          
            int id;
            id=list.get(0).getId();
            ProduitsServices PS=new ProduitsServices();
            PS.supprimerPorduits(id);
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduits.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void mod(ActionEvent event) {
        list=table.getSelectionModel().getSelectedItems();
        
        EncapProduits.setNom(list.get(0).getNom());
        EncapProduits.setQuantite(list.get(0).getQuantite());
        EncapProduits.setPrix(list.get(0).getPrix());
        EncapProduits.setCategorie(list.get(0).getCategorie());
        EncapProduits.setImage(list.get(0).getImage());
        
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
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduits.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rechercher(ActionEvent event) {
       
        ProduitsServices re= new ProduitsServices();
     List<Produits>results = new ArrayList<>();
     results = re.readALL();
     FilteredList<Produits> filteredData = new FilteredList<>(list , b -> true);
     Produits r = new Produits();
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Produits -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
 
				  if (Produits.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                 
//                                else if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				} 
//                                  if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//                                                                 
//                                else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				} 
				
                                
                                 
				     else  
				    	 return false; // Does not match
                                  
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produits> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }

    @FXML
    private void chart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatProd.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imprimer(ActionEvent event) {
        saveHeader("produits.csv");
        try {
            String requete = "SELECT * FROM produits ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getString("nom"), rs.getInt("quantite"), rs.getInt("prix"), rs.getString("categorie"),rs.getString("image"), "produits.csv");
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

            pw.println("id,nom,quantite,prix,categorie,image");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int id,String nom, int quantite , int prix , String categorie ,String image, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(id + "," + nom + "," + quantite + "," + prix + "," + categorie + "," + image );
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    @FXML
    private void triIdASC(ActionEvent event) {
        ObservableList<Produits> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from Produits ORDER BY Produits.`nom` DESC";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
            // list.add(new Produits(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4))); 
            list.add(new Produits(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        table.setItems(list);

        id_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("nom"));
        quantite_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer >("quantite"));
        prix_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("prix"));
        categorie_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("categorie"));
         image_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("image"));
    
    }

    @FXML
    private void triDESC(ActionEvent event) {
        ObservableList<Produits> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from Produits ORDER BY Produits.`nom` ASC";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
            // list.add(new Produits(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4))); 
            list.add(new Produits(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        table.setItems(list);
        id_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("id"));
        nom_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("nom"));
        quantite_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer >("quantite"));
        prix_column.setCellValueFactory(new PropertyValueFactory<Produits,Integer>("prix"));
        categorie_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("categorie"));
         image_column.setCellValueFactory(new PropertyValueFactory<Produits,String>("image"));
        
    }

    @FXML
    private void retourr(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root=loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }
    

    

    
    
         

    

