/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Commande;
import gestionpara.entities.Livreur;
import gestionpara.services.CommandeServices;
import gestionpara.services.LivreurServices;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class AffCommandeController implements Initializable {

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> id_column;
    @FXML
    private TableColumn<Commande, Integer> prix_column;
    @FXML
    private TableColumn<Commande, String> etat_column;
    @FXML
    private Button supp_btn;
    @FXML
    private TextField id_mod;
    @FXML
    private TextField etat_mod;
    @FXML
    private Button mod_btn;
    @FXML
    private Button retour_btn;
    @FXML
    private Pagination pagination;
    private final static int rowsPerPage = 5;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            CommandeServices CS = new CommandeServices();
             ObservableList<Commande> list = FXCollections.observableArrayList();
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("Prix_Total"));
        etat_column.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        list.addAll(CS.readAll());
        table.setItems(list);
       pagination.setPageFactory(this::createPage);  

        } catch (SQLException ex) {
            Logger.getLogger(AffCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mod_btn.setDisable(true);
        supp_btn.setDisable(true);
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Commande cselected = (Commande) table.getSelectionModel().getSelectedItem();
                System.out.println("selected value " + cselected);
                if(cselected!=null){
                id_mod.setText(String.valueOf(cselected.getID()));
                id_mod.setDisable(true);
                etat_mod.setText(String.valueOf(cselected.getEtat()));
                mod_btn.setDisable(false);
                supp_btn.setDisable(false);
               
                }else{
                    supp_btn.setDisable(true);
                    mod_btn.setDisable(true);
                    id_mod.clear();
                    etat_mod.clear();
                
                }
            }
           
        });
           
    }    

    @FXML
    private void supp(ActionEvent event) throws SQLException {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer l'utilisateur ?") ;//problem
         
        CommandeServices L=new CommandeServices();
        Commande cselected = (Commande) table.getSelectionModel().getSelectedItem();

         L.delete(cselected);
         afficher();
    }
    
    private void afficher() throws SQLException
    { CommandeServices CS = new CommandeServices();
             ObservableList<Commande> list = FXCollections.observableArrayList();
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("Prix_Total"));
        etat_column.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        list.addAll(CS.readAll());
        table.setItems(list);}

   

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Commande cselected = (Commande) table.getSelectionModel().getSelectedItem();

        Commande C = new Commande(cselected.getID(),etat_mod.getText());
        CommandeServices CS = new CommandeServices();
        CS.update(C);
       
       //afficher();
        pagination.setPageFactory(this::createPage); 
    }

    private void GoToAjout(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjoutCommande.fxml"));
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
    
    private Node createPage(int pageIndex)  {
          CommandeServices sm;    
        try {
            sm = new CommandeServices();
             ObservableList<Commande> list = FXCollections.observableArrayList();
         id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        prix_column.setCellValueFactory(new PropertyValueFactory<>("Prix_Total"));
        etat_column.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        list.addAll(sm.readAll());
      
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
       
        } catch (SQLException ex) {
            Logger.getLogger(AffCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }

    @FXML
    private void pdfreport(ActionEvent event) throws  FileNotFoundException, IOException, DocumentException {
       
          Document pdfReport = new Document();
           PdfWriter.getInstance(pdfReport, new FileOutputStream("LesCommandes.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("Commandes"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
         
           
         
          PdfPTable my_report_table = new PdfPTable(3);
       
         
           PdfPCell  tableCellColumn = new PdfPCell(new Phrase("ID"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("PRIX"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("ETAT"));
            my_report_table.addCell(tableCellColumn);
         
           
           
            double h= 0;
            table.getItems().forEach((Commande inter) -> {
               
             
                String idc = "" + inter.getID();
                PdfPCell  tableCell = new PdfPCell(new Phrase(idc));
                my_report_table.addCell(tableCell);
               
                
                
                  String prix = "" + inter.getPrix_Total();

                   tableCell = new PdfPCell(new Phrase(prix));
                my_report_table.addCell(tableCell);
               
               
                  tableCell = new PdfPCell(new Phrase(inter.getEtat()));
                my_report_table.addCell(tableCell);
                   
            });
            /* Attach report table to PDF */
            pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
         
           
           
           
           
            pdfReport.close();
           
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: C:\\Users\\HP ENVY\\Documents\\NetBeansProjects\\PIDEV");
            Desktop.getDesktop().open(new File("./LesCommandes.pdf"));
            alertReservation.showAndWait();
    }

    
}
