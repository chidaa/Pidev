/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Views;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;
import pidevj.Entities.disponibilite;
import pidevj.Entities.societelivraison;
import pidevj.Services.ServiceDisponibilite;
import pidevj.Services.ServiceMail;
import pidevj.Services.ServicePdf;
import pidevj.Services.ServiceSocietelivraison;
import pidevj.utils.connexionBD;

/**
 * FXML Controller class
 *
 * @author Abdelalim Mahfoudh
 */
public class DisponibiliteController implements Initializable {
    
     Connection cnx = connexionBD.getInstance().getConnection();
    ServiceDisponibilite SD = new ServiceDisponibilite();

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
    @FXML
    private TableColumn<disponibilite, Integer> colid;
    @FXML
    private TableColumn<disponibilite, Integer> colsocietes;
    @FXML
    private TableColumn<disponibilite, String> coljours;
    @FXML
    private TableColumn<disponibilite, String> colregion;
    @FXML
    private Button modif_res;
    @FXML
    private TableView<disponibilite> tabDisponibilite;
    @FXML
    private TextField SearchBar;
    @FXML
    private Button btn_ajout1;
    @FXML
    private TextField emaill;
    private TextField object_mail_client;
    private TextArea subject_mail_client;
    @FXML
    private TextField object_mail_admin;
    @FXML
    private TextArea subject_mail_admin;
    @FXML
    private Button envoyer_mail_admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceDisponibilite Dis = new  ServiceDisponibilite ();
        ObservableList<disponibilite> disponibilite = Dis.afficherDisponibiliteList();
        afficherDisponibiliteList();
        
         btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/AjoutDisponibilite.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btnCustomers1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(DisponibiliteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
private void afficherDisponibiliteList() {
        ServiceDisponibilite Rec = new ServiceDisponibilite();
        ObservableList<disponibilite> disponibilite = Rec.afficherDisponibiliteList();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsocietes.setCellValueFactory(new PropertyValueFactory<>("societes_id"));
        coljours.setCellValueFactory(new PropertyValueFactory<>("jours"));
        colregion.setCellValueFactory(new PropertyValueFactory<>("region"));
        
        tabDisponibilite.setItems(disponibilite);
        search_Dispo();

    }

    @FXML
    private void triIdASC(ActionEvent event) {
        
        ObservableList<disponibilite> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from disponibilite ORDER BY disponibilite.`id` ASC";
           ServiceSocietelivraison SS = new ServiceSocietelivraison();
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              disponibilite dd = new disponibilite();
              dd.setId(rs.getInt(1));
              dd.setSocietes_id(SS.load_data8(rs.getString(2)));
              dd.setJours(rs.getString(3));
              dd.setRegion(rs.getString(4));
   
        list.add(dd);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tabDisponibilite.setItems(list);
         colid.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("id"));
        colsocietes.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("societes_id"));
        coljours.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("jours"));
        colregion.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("region"));
    }

    @FXML
 private void triIdDESC(ActionEvent event) {
        
        ObservableList<disponibilite> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from disponibilite ORDER BY disponibilite.`id` DESC";
           ServiceSocietelivraison SS = new ServiceSocietelivraison();
              PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              disponibilite dd = new disponibilite();
              dd.setId(rs.getInt(1));
              dd.setSocietes_id(SS.load_data8(rs.getString(2)));
              dd.setJours(rs.getString(3));
              dd.setRegion(rs.getString(4));
   
        list.add(dd);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        tabDisponibilite.setItems(list);
         colid.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("id"));
        colsocietes.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("societes_id"));
        coljours.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("jours"));
        colregion.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("region"));
    }
 
  void search_Dispo() {
        
        disponibilite d = new disponibilite();
        colid.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("id"));
        colsocietes.setCellValueFactory(new PropertyValueFactory<disponibilite,Integer>("societes_id"));
        coljours.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("jours"));
        colregion.setCellValueFactory(new PropertyValueFactory<disponibilite,String>("region"));
        ObservableList<disponibilite> dataList;

        dataList = SD.afficherDisponibiliteList();
       
        tabDisponibilite.setItems(dataList);
       
        FilteredList<disponibilite> filteredData = new FilteredList<>(dataList, b -> true);
       
        SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((disponibilite dispo) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (dispo.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (String.valueOf(dispo.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
              else if (String.valueOf(dispo.getSocietes_id()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
               else if  (dispo.getJours().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
            
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<disponibilite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabDisponibilite.comparatorProperty());
        tabDisponibilite.setItems(sortedData);
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
    private void ajoutdisponibilite(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void modifier_rec(ActionEvent event) throws IOException {
        disponibilite res= new disponibilite();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidevj/Views/ModifierDisponibilite.fxml"));
            Parent root = loader.load();
          ModifierDisponibiliteController HomeScene = loader.getController();
          System.out.println("aaaaa");
          ServiceSocietelivraison soci= new ServiceSocietelivraison ();
          
         
            HomeScene.selected_item(
                    tabDisponibilite.getSelectionModel().getSelectedItem().getId(),
                    tabDisponibilite.getSelectionModel().getSelectedItem().getSocietes_id(),
                    tabDisponibilite.getSelectionModel().getSelectedItem().getJours(),
                    tabDisponibilite.getSelectionModel().getSelectedItem().getRegion()
                   );
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_res.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void Imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(tabDisponibilite);
    }

    @FXML
    private void envoyer_mail_ad(ActionEvent event) {
         ServiceDisponibilite SD = new ServiceDisponibilite();
        ServiceMail sm = new ServiceMail();
System.out.println("envoyer !!!");

        //Client c = sc.load_data_modify(id);
        String email = emaill.getText();
        String object = object_mail_admin.getText();
        String subject = subject_mail_admin.getText();
                System.out.println(emaill.getText());
                System.out.println(object_mail_admin.getText());
                System.out.println(subject_mail_admin.getText());

        

        sm.envoyerMail(email, object, subject);
        
        Notifications notificationBuilder = Notifications.create()
            .title("ENVOI D'UN MAIL")
               .text("votre mail a etait bien envoyer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
       
        System.out.println("envoyer !!!");
      
     emaill.setText(null);
        subject_mail_client.setText(null);
        object_mail_client.setText(null);
        
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos disponibilites?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
             
        sp.liste_disponibilitePDF();
        
        Notifications notificationBuilder = Notifications.create()
            .title("CREATION DU PDF")
               .text("votre PDF a etait bien créer  !")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        }
        
    }

    @FXML
    private void btn_bilan(ActionEvent event) {
        
        
        ServiceDisponibilite sd = new ServiceDisponibilite();
DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();       
 System.out.println(sd.nb_Region());
        for (int i = 0; i <sd.nb_Region(); i++)
       { 
           String msg="";
           msg=sd.getValuesSociete().get(i) + " [ " + sd.getnb_disponibilite_Region().get(i)+ " ] ";
         
       System.out.println(sd.getValuesSociete().get(i));
           pieDataset.setValue(msg,sd.getnb_disponibilite_Region().get(i) );   
       }
     
        JFreeChart chart = ChartFactory.createPieChart("Nombre de disponibilite par region", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan", chart);
        frame.setVisible(true);
        frame.setSize(900, 600);
        frame.setLocation(300, 30);
        
    }
  
}
