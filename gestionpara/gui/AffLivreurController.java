/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import gestionpara.entities.Livraison;
import gestionpara.entities.Livreur;
import gestionpara.services.LivraisonServices;
import gestionpara.services.LivreurServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author HP ENVY
 */
public class AffLivreurController implements Initializable {

    @FXML
    private TableView<Livreur> table;
   
    @FXML
    private TableColumn<Livreur, String> cin_column;
    @FXML
    private TableColumn<Livreur, String> nom_column;
    @FXML
    private TableColumn<Livreur, String> prenom_column;
    @FXML
    private TableColumn<Livreur, String> dispo_column;
    private TextField id_supp;
    @FXML
    private Button supp_btn;
    @FXML
    private TableColumn<Livreur, String> id_column;
    @FXML
    private Button mod_btn;
    @FXML
    private Button retour_btn;
    @FXML
    private Button ajout_btn;
    @FXML
    private TextField id_mod;
    @FXML
    private TextField nom_mod;
    @FXML
    private TextField prenom_mod;
    @FXML
    private TextField dispo_mod;
    @FXML
    private TextField cin_mod;
    @FXML
    private Button mail_btn;
    @FXML
    private TextField filtrefield;
    @FXML
    private Button chart1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            LivreurServices LS = new LivreurServices();
            ObservableList<Livreur> list = FXCollections.observableArrayList();
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cin_column.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_column.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dispo_column.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        list.addAll(LS.readAll());
        table.setItems(list);
        search();
        } catch (SQLException ex) {
            Logger.getLogger(AffLivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         mod_btn.setDisable(true);
        supp_btn.setDisable(true);
         ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Livreur lselected = (Livreur) table.getSelectionModel().getSelectedItem();
                System.out.println("selected value " + lselected);
                if(lselected!=null){
                id_mod.setText(String.valueOf(lselected.getID()));
                cin_mod.setText(String.valueOf(lselected.getCIN()));
                id_mod.setDisable(true);
                nom_mod.setText(String.valueOf(lselected.getNom()));
                prenom_mod.setText(String.valueOf(lselected.getPrenom()));
                dispo_mod.setText(String.valueOf(lselected.getDisponibilite()));

                mod_btn.setDisable(false);
                supp_btn.setDisable(false);
               
                }else{
                    supp_btn.setDisable(true);
                    mod_btn.setDisable(true);
                    id_mod.clear();
                    nom_mod.clear();
                    prenom_mod.clear();
                    dispo_mod.clear();
                
                }
            }
           
        });
         
         
    }    

    @FXML
    private void Supp(ActionEvent event) throws SQLException {
        
         Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer l'utilisateur ?") ;//problem
         LivreurServices L=new LivreurServices();
           Livreur lselected = (Livreur) table.getSelectionModel().getSelectedItem();

         
         L.delete(lselected);
         afficher();
    }
    
    public void afficher() throws SQLException
{
    LivreurServices LS = new LivreurServices();
            ObservableList<Livreur> list = FXCollections.observableArrayList();
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cin_column.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_column.setCellValueFactory(new PropertyValueFactory< >("prenom"));
        dispo_column.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        list.addAll(LS.readAll());
        table.setItems(list);
       
}

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        LivreurServices LS = new LivreurServices();
        Livreur L = new Livreur(Integer.valueOf(id_mod.getText()),Integer.valueOf(cin_mod.getText()),nom_mod.getText(),prenom_mod.getText(),dispo_mod.getText());
        LS.update(L);
        afficher();

        
    }

    @FXML
    private void GoToDash(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

   

    @FXML
    private void GoToLivreur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjoutLivreur.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void GoToMail(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MailingLivreur.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    void search() throws SQLException{
    Livreur m = new Livreur();  
     
        id_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cin_column.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        nom_column.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_column.setCellValueFactory(new PropertyValueFactory< >("prenom"));
        dispo_column.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
       
ObservableList<Livreur> dataList;

  LivreurServices sm= new LivreurServices();    
        dataList =sm.readAll2();
       
        table.setItems(dataList);
       
        FilteredList<Livreur> filteredData = new FilteredList<>(dataList, b -> true);
       
        filtrefield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Livreur promo) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                int casefiltreint=-1;
                try{
                casefiltreint=Integer.parseInt(lowerCaseFilter);
                }catch(Exception e ){
                    casefiltreint=-1;
                }
                if(casefiltreint!=-1&&promo.getID()==casefiltreint){
                return true;
                }else if(String.valueOf(promo.getCIN()).indexOf(lowerCaseFilter) != -1){
                return true; }
               
                else if (promo.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (promo.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
                else if (promo.getDisponibilite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
               
             
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Livreur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void chartaction(ActionEvent event) throws SQLException {
          LivreurServices sr = new LivreurServices();
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        JFreeChart chart =ChartFactory.createPieChart("Nombre de Livreur par Disponibilite", pieDataset, true, true, true);
        PiePlot P = (PiePlot)chart.getPlot();
       // P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame = new ChartFrame("Pie chart",chart);
        frame.setVisible(true);
        frame.setSize(450,500);

       for (int i = 0; i <sr.nb_Livreur(); i++)
       {
           String msg="";
           msg=sr.getValuesDis().get(i) + " [ " + sr.getnb_disponibilite_livreur().get(i)+ " ] ";
           pieDataset.setValue(msg,sr.getnb_disponibilite_livreur().get(i) );
           
        System.out.println(sr.getValuesDis().get(i));
                System.out.println(sr.getnb_disponibilite_livreur().get(i));
            //  System.out.println  (spec.nb_specialite());
       
    }
    }
}
