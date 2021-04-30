/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Reclamation;
import Reclamation.Services.ServiceGarantie;
import Reclamation.Services.ServicePdf;
import Reclamation.Services.ServiceProduit;
import Reclamation.Services.ServiceReclamation;
import Reclamation.Services.ServiceUser;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tabReclamation;
    @FXML
    private TableColumn<Reclamation, Integer> colid_rec;
    @FXML
    private TableColumn<Reclamation, String> coldesc;
    @FXML
    private TableColumn<Reclamation, String> coltheme;
    @FXML
    private TableColumn<Reclamation, Integer> coluser;
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
    private Button btn_ajout;
    @FXML
    private Button modif_res;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private TextField tfrechres;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btnProfil11;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private Button btn_email;
    @FXML
    private Button btn_bilan1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            new animatefx.animation.LightSpeedIn(btn_ajout).play();
            new animatefx.animation.LightSpeedIn(modif_res).play();
                        new animatefx.animation.LightSpeedIn(btn_exit).play();

              new animatefx.animation.ZoomIn(tabReclamation).play();
        
        
        ServiceReclamation Rec = new ServiceReclamation();
        ObservableList<Reclamation> Reclamation = Rec.afficherReclamationList();
        afficherReclamationList();
        
        
        ObservableList<String> listTriObjPred = FXCollections.observableArrayList("Id", "Theme", "Client");
        cbtriObjPred.setItems(listTriObjPred);
        
           btn_email.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/envrec.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                       new animatefx.animation.LightSpeedIn(page1).play();

            } catch (IOException ex) {
              Logger.getLogger(GarantieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnProfil11.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                       new animatefx.animation.LightSpeedIn(page1).play();

            } catch (IOException ex) {
              Logger.getLogger(GarantieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/AjoutReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btnProfil1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }
    
     private void afficherReclamationList() {
        ServiceReclamation Rec = new ServiceReclamation();
        ObservableList<Reclamation> reclamation = Rec.afficherReclamationList();
        colid_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        
        tabReclamation.setItems(reclamation);

    }
    @FXML
    private void modifier_rec(ActionEvent event) throws IOException {
    
        Reclamation res= new Reclamation();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reclamation/Views/ModifierReclamation.fxml"));
            Parent root = loader.load();
          ModifierReclamationController HomeScene = loader.getController();
          System.out.println("aaaaa");
          ServiceUser user= new ServiceUser();
          
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                    tabReclamation.getSelectionModel().getSelectedItem().getId(),
                    tabReclamation.getSelectionModel().getSelectedItem().getDescription(),
                    tabReclamation.getSelectionModel().getSelectedItem().getTheme(),
                    tabReclamation.getSelectionModel().getSelectedItem().getUser_id()
                   );
           
            Stage window = (Stage) modif_res.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }
    @FXML
    private void ajoutreclamation(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
@FXML
    private void rechercherObjectif(KeyEvent event) {
         System.out.println("recherche");
        ServiceReclamation sop = new ServiceReclamation();
        ObservableList<Reclamation> rec = sop.rechercherReclamation(tfrechres.getText());
                 System.out.println("recherche valide");


        tabReclamation.setItems(rec);
    }

   @FXML
    private void selectTriObjPred(ActionEvent event) {
          ServiceReclamation sop = new ServiceReclamation();
        ObservableList<Reclamation> reclamation = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("Id")) {
           reclamation = sop.trierReservationid();
        } else if (cbtriObjPred.getValue().equals("Theme")) {
           reclamation = sop.trierReservationtheme();
        } else if (cbtriObjPred.getValue().equals("Client")) {
          reclamation = sop.trierReservationclient();
        }
        tabReclamation.setItems(reclamation);
    }
@FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_reclamationPDF();
        }
        
        
    }
    
    @FXML
    private void btn_bilan(ActionEvent event) {
        
        ServiceReclamation rec = new ServiceReclamation();
                ServiceUser usr = new ServiceUser();

        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
                   System.out.println(rec.nb_clients());
                   
      for (int i = 0; i <rec.nb_clients(); i++)
       { 
           String msg="";
           msg=usr.getValuesUser().get(i) + " [ " + rec.getnb_reclamation_Client().get(i)+ " ] ";
           pieDataset.setValue(msg,rec.getnb_reclamation_Client().get(i) );

       }
      
        //   pieDataset.setValue("Fait", (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id));
        JFreeChart chart = ChartFactory.createPieChart("Bilan des Reclamations: ", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan Reclamations", chart);
        frame.setVisible(true);
        frame.setSize(900, 600);
        frame.setLocation(300, 30);
    }

}