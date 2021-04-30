/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Views;

import Reclamation.Entities.Garantie;
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
 * @author elyes
 */
public class GarantieController implements Initializable {

    @FXML
    private Button btn_ajout;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnPackages1;
    @FXML
    private TableView<Garantie> tabGarantie;
    @FXML
    private TableColumn<Garantie, String> coldesc;
    @FXML
    private TableColumn<Garantie, Integer> coluser;
    @FXML
    private Button modif_garantie;
    @FXML
    private TableColumn<Garantie, Integer> colgaran;
    @FXML
    private TableColumn<Garantie, Integer> colprod;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_garantie;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnProfil11;
    @FXML
    private TextField tfrechres;
    @FXML
    private Button btn_exit;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private Button btn_bilan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
ServiceGarantie Rec = new ServiceGarantie();
        ObservableList<Garantie> Garantie = Rec.afficherGarantieList();
        afficherGarantieList();
        
           
        
          ObservableList<String> listTriObjPred = FXCollections.observableArrayList("Id", "Produit", "Client");
        cbtriObjPred.setItems(listTriObjPred);
        
        new animatefx.animation.LightSpeedIn(btn_ajout).play();
            new animatefx.animation.LightSpeedIn(modif_garantie).play();
                        new animatefx.animation.LightSpeedIn(btn_exit).play();
                                      new animatefx.animation.ZoomIn(tabGarantie).play();

        
         btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Reclamation/Views/AjoutGarantie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
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
          btnCustomers.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/pidevj/Views/societelivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
 private void afficherGarantieList() {
        ServiceGarantie Rec = new ServiceGarantie();
        ObservableList<Garantie> garantie = Rec.afficherGarantieList();
        colgaran.setCellValueFactory(new PropertyValueFactory<>("id"));
         colprod.setCellValueFactory(new PropertyValueFactory<>("id_produit"));

        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        
        tabGarantie.setItems(garantie);

    }
    @FXML
    private void ajoutgarantie(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void modifier_garantie(ActionEvent event) throws IOException {
          Garantie res= new Garantie();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reclamation/Views/ModifierGarantie.fxml"));
            Parent root = loader.load();
          ModifierGarantieController HomeScene = loader.getController();
          System.out.println("aaaaa");
         // ServiceUser user= new ServiceUser();
          
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                    tabGarantie.getSelectionModel().getSelectedItem().getId(),
                    tabGarantie.getSelectionModel().getSelectedItem().getId_produit(),
                    tabGarantie.getSelectionModel().getSelectedItem().getDescription(),
                    tabGarantie.getSelectionModel().getSelectedItem().getUser_id()
                   );
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_garantie.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }
    
    @FXML
    private void rechercherObjectif(KeyEvent event) {
         System.out.println("recherche");
        ServiceGarantie sop = new ServiceGarantie();
        ObservableList<Garantie> gar = sop.rechercherGarantie(tfrechres.getText());
                 System.out.println("recherche valide");


        tabGarantie.setItems(gar);
    }

     @FXML
    private void selectTriObjPred(ActionEvent event) {
          ServiceGarantie sop = new ServiceGarantie();
        ObservableList<Garantie> gar = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("Id")) {
           gar = sop.trierGarantieid();
        } else if (cbtriObjPred.getValue().equals("Produit")) {
           gar = sop.trierGarantieproduit();
        } else if (cbtriObjPred.getValue().equals("Client")) {
          gar = sop.trierGarantieclient();
        }
        tabGarantie.setItems(gar);
    }

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, FileNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_garantiePDF();
    }
    }   
    @FXML
    private void btn_bilan(ActionEvent event) {
        
        ServiceGarantie gar = new ServiceGarantie();
                ServiceProduit prod = new ServiceProduit();

        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        
        int nombre_produit = prod.nb_produit();
        int nombre_garanties = gar.nb_Garantie();
        
                         System.out.println(nombre_produit);
                 System.out.println(nombre_garanties);

int diff=nombre_produit - nombre_garanties;
                 String msg="Produits sans garanties [ " + diff + " ]";
        pieDataset.setValue(msg, nombre_produit - nombre_garanties);
        pieDataset.setValue("Produits avec garanties [ " + nombre_garanties + " ]",  nombre_garanties);

        //   pieDataset.setValue("Fait", (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id));
        JFreeChart chart = ChartFactory.createPieChart("Bilan de garanties: ", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan garantie", chart);
        frame.setVisible(true);
        frame.setSize(900, 600);
        frame.setLocation(300, 30);
    }
}
