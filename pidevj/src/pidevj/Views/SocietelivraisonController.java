/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Views;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import pidevj.Entities.societelivraison;
import pidevj.Services.ServiceSocietelivraison;
import pidevj.utils.connexionBD;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class SocietelivraisonController implements Initializable {
    
    Connection cnx = connexionBD.getInstance().getConnection();
    ServiceSocietelivraison SS = new ServiceSocietelivraison();
    
 @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnProfil1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnPackages1;
    private TableColumn<societelivraison, ?> colid_rec;
    @FXML
    private TableColumn<societelivraison, String> colnom;
    @FXML
    private TableColumn<societelivraison, String> coladresse;
    @FXML
    private TableColumn<societelivraison, Integer> colcontact;
    @FXML
    private Button modif_soc;
    @FXML
    private TableColumn<societelivraison, Integer> colid;
    @FXML
    private TableView<societelivraison> tabSociete;
    @FXML
    private TextField SearchBar;
    @FXML
    private Button btn_ajout1;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceSocietelivraison Rec = new ServiceSocietelivraison();
        ObservableList<societelivraison> societe = Rec.afficherSocieteList();
        afficherSocieteList();
        
         btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/AjoutSociete.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_garantie.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/garantie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/reclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          btnMenus1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/disponibilite.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(SocietelivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }  
     private void afficherSocieteList() {
        ServiceSocietelivraison Soc = new ServiceSocietelivraison();
        ObservableList<societelivraison> societe = Soc.afficherSocieteList();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
        tabSociete.setItems(societe);
        search_Soc();

    }
     
     @FXML
    private void triNomASC(ActionEvent event) {
        
        ObservableList<societelivraison> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from societelivraison ORDER BY societelivraison.`nom` ASC";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new societelivraison(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4))); 
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tabSociete.setItems(list);
         colid.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("id"));
       colnom.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("adresse"));
        colcontact.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("contact"));
    
    }
     @FXML
    private void triNomDESC(ActionEvent event) {
        
        ObservableList<societelivraison> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from societelivraison ORDER BY societelivraison.`nom` DESC";
           
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              
   
             list.add(new societelivraison(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4))); 
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tabSociete.setItems(list);
         colid.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("id"));
       colnom.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("adresse"));
        colcontact.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("contact"));
    
    } 
    void search_Soc() {
        
        societelivraison s = new societelivraison();
        colid.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("id"));
       colnom.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("nom"));
        coladresse.setCellValueFactory(new PropertyValueFactory<societelivraison,String>("adresse"));
        colcontact.setCellValueFactory(new PropertyValueFactory<societelivraison,Integer>("contact"));
        ObservableList<societelivraison> dataList;

        dataList = SS.afficherSocieteList();
       
        tabSociete.setItems(dataList);
       
        FilteredList<societelivraison> filteredData = new FilteredList<>(dataList, b -> true);
       
        SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((societelivraison soc) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (soc.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(soc.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
              else if (String.valueOf(soc.getAdresse()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
              else if (String.valueOf(soc.getContact()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<societelivraison> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabSociete.comparatorProperty());
        tabSociete.setItems(sortedData);
    }
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
    
  }
    
    
      @FXML
    private void ajoutreclamation(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
     private void modifier_soc(ActionEvent event) throws IOException {
         societelivraison soc= new societelivraison();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevj/Views/ModifierSociete.fxml"));
            Parent root = loader.load();
          ModifierSocieteController HomeScene = loader.getController();
          System.out.println("aaaaa");
         
          
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                    tabSociete.getSelectionModel().getSelectedItem().getId(),
                    tabSociete.getSelectionModel().getSelectedItem().getNom(),
                    tabSociete.getSelectionModel().getSelectedItem().getAdresse(),
                    tabSociete.getSelectionModel().getSelectedItem().getContact()
                   );
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_soc.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void Imprimer(ActionEvent event) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        
        printNode(tabSociete);
    }


    

    
}

 
  
     

