/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Desktop.Services.ArticlesService;
import Desktop.Services.CategorieService;
import Desktop.entities.Categorie;
import Desktop.entities.article;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ArticlesController implements Initializable {

    /**
     * Initializes the controller class.
     */
     Connection cn;
   @FXML
    private TableColumn<article, Integer> AfficheID;
   @FXML
    private TableColumn<article, Integer> AfficheID_c;
    @FXML
    private TableColumn<article, String> Affichesujet;
    @FXML
    private TableColumn<article, String> Affichedescrtiption;
    @FXML
    private TableView<article> ArticleTable;
    @FXML
    private TextField gid;
    @FXML
    private TextField gsujet;
    @FXML
    private TextArea gdescription;
    @FXML
    private Button buttonajouter;
    @FXML
    private Button gsupp;
    @FXML
    private TextField chercherArticle;
       @FXML
    private ComboBox<Integer> gComboBox2;
       @FXML
    private ImageView photo_view;
      
  @FXML
    private TextField photo_p;
       @FXML
        private ComboBox<Integer> check;
    ArticlesService ms = new ArticlesService();
     ObservableList options2 = FXCollections.observableArrayList();
       ObservableList options = FXCollections.observableArrayList();
       
       ObservableList data1 = FXCollections.observableArrayList();
    @FXML
    private Button buttonmodifier;
    @FXML
    private Button gretour;
    @FXML
    private CheckBox ch;
    @FXML
    private CheckBox ch1;
    @FXML
    private Button gtri;
    @FXML
    private Button btn111;
  
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Actualisercombox2();
        Actualisercombox1();
       view2();
       //gComboBox2.getItems().addAll(1,2);
    }    
    
    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

    
        
        gid.setStyle(styledefault);
        gsujet.setStyle(styledefault);
        gdescription.setStyle(styledefault);
        gComboBox2.setStyle(styledefault);
           
  

        if (gid.getText().equals("")) {
            gid.setStyle(style);
            verif = 1;
        }
        
        if (gsujet.getText().equals("")) {
            gsujet.setStyle(style);
            verif = 1;
        }

       

        if (gdescription.getText().equals("")) {
            gdescription.setStyle(style);
            verif = 1;
        }
          
        
        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Remplis tous les champs!");
        return false;
    }
    public void Actualisercombox2()
    {
        //combobox
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";
        try {
            cn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options2 = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cn.createStatement().executeQuery("SELECT id FROM categorie");
            while (rs.next()) {
                //get string from db,whichever way 
                    options2.add(new Categorie(rs.getInt("id")).getId());
            }
            
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        gComboBox2.setItems(null);
        gComboBox2.setItems(options2);
        
    }
    public void Actualisercombox1()
    {
        //combobox
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";
        try {
            cn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cn.createStatement().executeQuery("SELECT id FROM article");
            while (rs.next()) {
                //get string from db,whichever way 
                    options.add(new article(rs.getInt("id")).getId());
            }
            
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
        check.setItems(null);
        check.setItems(options);
        
    }

   
    @FXML
    private void AddAction(ActionEvent event) {
        if(verifUserChamps())
        {
              article l = new article();
        String id = gid.getText();
        l.setId(Integer.parseInt(id));

        l.setCategorie_id(gComboBox2.getSelectionModel().getSelectedItem());
        l.setDescription(gdescription.getText());
        l.setSujet(gsujet.getText());
        l.setImage(photo_p.getText().replace('\\', '/'));
       
        
       ms.ajouter(l);
        
        JOptionPane.showMessageDialog(null, "Article ajouté");
        view2();
        Actualisercombox1();
         Actualisercombox2();
        } 
        }
    public void view2() {
        ObservableList<article> list = ms.afficher();
        AfficheID.setCellValueFactory(new PropertyValueFactory<article, Integer>("id"));
        AfficheID_c.setCellValueFactory(new PropertyValueFactory<article, Integer>("categorie_id"));
        Affichesujet.setCellValueFactory(new PropertyValueFactory<article, String>("sujet"));
        Affichedescrtiption.setCellValueFactory(new PropertyValueFactory<article, String>("description"));

        
        ArticleTable.setItems(list);
        search_user();
        
        
    }
    @FXML
    private void ActionSupp2(ActionEvent event) {
        
        ArticlesService sp = new ArticlesService();
        article l = new article();

//    String id = gid.getText();
//        l.setId_membre(Integer.parseInt(id));
        l.setId(check.getSelectionModel().getSelectedItem());
        
        sp.supprimer(l);
        Actualisercombox1();
         Actualisercombox2();
        view2();
                

    }
    @FXML
    private void ActionModifier2(ActionEvent event) {
        
        ArticlesService sp = new ArticlesService();
        String s = gid.getText();
        int si = Integer.parseInt(s);
 
        sp.modifier(new article(si,gComboBox2.getSelectionModel().getSelectedItem(),gsujet.getText(), gdescription.getText()));
        JOptionPane.showMessageDialog(null, "Article modifiée !");
        view2();
          Actualisercombox1();
         Actualisercombox2();
    }

    @FXML
    private void categorieAction(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Myview.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    void search_user() {
        article l = new article();
        AfficheID.setCellValueFactory(new PropertyValueFactory<article, Integer>("id"));
        AfficheID_c.setCellValueFactory(new PropertyValueFactory<article, Integer>("categorie_id"));
        Affichesujet.setCellValueFactory(new PropertyValueFactory<article, String>("sujet"));
        Affichedescrtiption.setCellValueFactory(new PropertyValueFactory<article, String>("description"));
        
ObservableList<article> dataList;

        dataList = ms.afficher();
       
        ArticleTable.setItems(dataList);
       
        FilteredList<article> filteredData = new FilteredList<>(dataList, b -> true);
       
        chercherArticle.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((article person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
               
//                 else if (Integer.valueOf(person.getId_local()).equals(searchTerm.toLowerCase()) ) {
//                    return true;// Filter matches email
//              }
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<article> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ArticleTable.comparatorProperty());
        ArticleTable.setItems(sortedData);
    }
    @FXML
    private void TriAction(ActionEvent event) {
             if(ch.isSelected()){
           
        try {
            ArticlesService sc = new ArticlesService();
        

           ArrayList<article> lv;


            lv = (ArrayList<article>) sc.Tria();
            ObservableList<article> data1 = FXCollections.observableArrayList(lv);
            AfficheID.setCellValueFactory(new PropertyValueFactory<article,Integer>("id"));
            AfficheID_c.setCellValueFactory(new PropertyValueFactory<article,Integer>("categorie_id"));
            Affichesujet.setCellValueFactory(new PropertyValueFactory<article,String>("sujet"));
            Affichedescrtiption.setCellValueFactory(new PropertyValueFactory<article,String>("description"));
            
            ArticleTable.setItems(data1);
          
        } catch (Exception ex) {
            Logger.getLogger(ArticlesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  if(ch1.isSelected()){
    ch.setSelected(false);
        try {
            ArticlesService sc = new ArticlesService();
        

           ArrayList<article> lv;


            lv = (ArrayList<article>) sc.TriDesc();
            ObservableList<article> data;
        data = FXCollections.observableArrayList(lv);
            AfficheID.setCellValueFactory(new PropertyValueFactory<article,Integer>("id"));
            AfficheID_c.setCellValueFactory(new PropertyValueFactory<article,Integer>("categorie_id"));
            Affichesujet.setCellValueFactory(new PropertyValueFactory<article,String>("sujet"));
            Affichedescrtiption.setCellValueFactory(new PropertyValueFactory<article,String>("description"));
            
            ArticleTable.setItems(data);
          
        } catch (Exception ex) {
            Logger.getLogger(ArticlesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    

    @FXML
    private void ch(ActionEvent event) {
        ch1.setSelected(false);
    }

    @FXML
    private void ch1(ActionEvent event) {
        ch.setSelected(false);
    }
