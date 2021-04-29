/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;
import Desktop.Services.AvisService;
import Desktop.Services.RatingService;
import Desktop.entities.article;
import Desktop.entities.avis;
import Desktop.entities.commentaires;
import Desktop.entities.rating;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import org.shaded.apache.poi.hssf.usermodel.HSSFRow;
import org.shaded.apache.poi.hssf.usermodel.HSSFSheet;
import org.shaded.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.shaded.apache.poi.ss.usermodel.CellStyle;
import org.shaded.apache.poi.ss.usermodel.CreationHelper;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AvisGUIController implements Initializable {

     
    Connection cn;
    @FXML
    private TextField txtid_service;
    @FXML
    private Button likebtn;
    @FXML
    private Button dislikebtn;
    @FXML
    private TextField txtcomment;
    @FXML
    private Button commentbtn;
    @FXML
    private TableView<avis> avis;
    @FXML
    private TableView<commentaires> comments;
    @FXML
    private TableColumn<avis, Integer> colida;
    @FXML
    private TableColumn<avis, Integer> colids;
    @FXML
    private TableColumn<avis, String> colavis;
    @FXML
    private TableColumn<commentaires, Integer> colidc;
    @FXML
    private TableColumn<commentaires, String> colcomm;
    @FXML
    private TableColumn<commentaires, Date> coldate;
    private Rating rate;
    RatingService ms = new RatingService();
    private int idarticle = Pidev.id_a;
    private int idavis = 0;
    private int idcommentaire = 0;
    private String filename="historique2.txt";
    @FXML
    private TitledPane titledpane1;
    @FXML
    private TitledPane titlepane2;
    private TableColumn<?, ?> AfficheID_c;
    private TableView<?> ArticleTable;
    private TableColumn<?, ?> AfficheID1;
    private TableColumn<?, ?> Affichesujet;
    @FXML
    private Button btn11;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
            PrintWriter outputstream = null;
        try {
            // TODO
           
            afficherAvis();
            afficherCommentaire();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("");
            outputstream.println("Historique du "+dtf.format(now));
            outputstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          //  outputstream.close();
        }
         Notifications notif = Notifications.create().title("NB").text("Toutes vos actions seront enregistrés dans un historique");
//        notif.showWarning();
        titledpane1.setExpanded(false);
        titlepane2.setExpanded(false);
        
    }    

    
    public void afficherEvents() {
       
    }   
    
    public void afficherAvis() {
        AvisService se = new AvisService();
        ObservableList<avis> list  = se.getEventsList1();
        colida.setCellValueFactory(new PropertyValueFactory<avis,Integer>("id_avis"));
        colids.setCellValueFactory(new PropertyValueFactory<avis,Integer>("id_article"));
        colavis.setCellValueFactory(new PropertyValueFactory<avis,String>("type_avis"));
        
        avis.setItems(list);
    }

    public void afficherCommentaire() {
        RatingService se = new RatingService();
        ObservableList<commentaires> list  = se.getEventsList2();
        colidc.setCellValueFactory(new PropertyValueFactory<commentaires,Integer>("id_commentaire"));
        colcomm.setCellValueFactory(new PropertyValueFactory<commentaires,String>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<commentaires,Date>("date_pub"));
        comments.setItems(list);
    }


    private void handleMouseButton(MouseEvent event) {
        
    }

    @FXML
    private void ajouterAvisLike(ActionEvent event) {
        AvisService se = new AvisService();
        avis ev = new avis();
        
          ev.setType_avis("like");
          ev.setId_article(idarticle);
         if (idavis == 0) { se.ajouterAvis(ev); 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes d ajout");
         alert.setHeaderText(null);
         alert.setContentText("avis ajoute avec succes!");

        alert.showAndWait();}
         else {ev.setId_avis(idavis);
             se.modifierAvis(ev);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes de modification");
         alert.setHeaderText(null);
         alert.setContentText("avis modifié avec succes!");

         alert.showAndWait();} 
         
         PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("avis like ajouté sur le service "+String.valueOf(idarticle));
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         afficherEvents();
         afficherAvis();
    }

    @FXML
    private void ajouterAvisDislike(ActionEvent event) {
        AvisService se = new AvisService();
        avis ev = new avis();
        
          ev.setType_avis("dislike");
          ev.setId_article(idarticle);
         if (idavis == 0) { se.ajouterAvis(ev);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes d ajout");
         alert.setHeaderText(null);
         alert.setContentText("avis ajouté avec succes!");

         alert.showAndWait();}
         else { ev.setId_avis(idavis);
             se.modifierAvis(ev);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Succes de modification");
          alert.setHeaderText(null);
          alert.setContentText("avis modifié avec succes!");

          alert.showAndWait();} 
        PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("avis dislike ajouté sur le service "+String.valueOf(idarticle));
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherEvents();
        afficherAvis();
        
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        
        RatingService se = new RatingService();
        commentaires ev = new commentaires();
        ev.setContenu(txtcomment.getText());
        
        if (idcommentaire == 0 ) { se.ajouterCommentaire(ev);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes d ajout");
         alert.setHeaderText(null);
         alert.setContentText("commentaire ajoute avec succes!");

         alert.showAndWait();}
        else { 
               ev.setId_commentaire(idcommentaire);
               se.modifierCommentaire(ev);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Succes de modification");
               alert.setHeaderText(null);
               alert.setContentText("commentaire modifié avec succes!");

               alert.showAndWait();
               
             }
        PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("commentaire est ajouté sur lavis "+String.valueOf(idarticle));
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtcomment.setText("");
        afficherEvents();
        afficherCommentaire();
    }

    @FXML
    public void handleMouseButton1(MouseEvent event) {
        avis s = avis.getSelectionModel().getSelectedItem();
        String id = Integer.toString(s.getId_avis());
        idavis=s.getId_avis();
    }

    @FXML
    public void handleMouseButton2(MouseEvent event) {
        commentaires s = comments.getSelectionModel().getSelectedItem();
     
        String id = Integer.toString(s.getId_commentaire());
        txtcomment.setText(s.getContenu());
        idcommentaire=s.getId_commentaire();
    }

    @FXML
    private void supprimerAvis(ActionEvent event) {
        AvisService se = new AvisService();
        avis ev = new avis();
        ev.setId_avis(idavis);
        se.supprimerAvis(ev);
        afficherEvents();
        afficherAvis();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes de suppression");
        alert.setHeaderText(null);
        alert.setContentText("avis supprime avec succes!");
        alert.showAndWait();
        PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("avis "+String.valueOf(idavis)+" est supprimé");
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerCommentaire(ActionEvent event) {
        RatingService se = new RatingService();
        commentaires ev = new commentaires();
        ev.setId_commentaire(idcommentaire);
        se.supprimerCommentaire(ev);
        txtcomment.setText("");
        afficherEvents();
        afficherCommentaire();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes de suppression");
        alert.setHeaderText(null);
        alert.setContentText("commentaire supprime avec succes!");
        alert.showAndWait();
        PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("commentaire "+String.valueOf(idcommentaire)+" est supprimé");
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void ajouterRating(MouseEvent event) {
        
       RatingService se = new RatingService();
        List <Integer> list  = se.getEventsList3();
        rating r = new rating();
        if (list.contains(idarticle))
        {
            r.setId_rate(se.getidrate(idarticle));
            r.setRate((int)rate.getRating());
          r.setId_article(idarticle);
            se.modifierRate(r);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Succes de modification");
          alert.setHeaderText(null);
          alert.setContentText(" rateS modifié avec succes!");
          alert.showAndWait();
          
           PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("rate "+String.valueOf((int)rate.getRating())+" ajouté sur le service "+String.valueOf(idarticle));
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
           r.setRate((int)rate.getRating());
          r.setId_article(idarticle);
          se.ajouterRate(r); 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes d ajout");
         alert.setHeaderText(null);
         alert.setContentText("rating ajoute avec succes!");
         alert.showAndWait();
         
         PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("rate "+String.valueOf((int)rate.getRating())+" ajouté sur le service "+String.valueOf(idarticle));
             outputstream.close();
        } catch (IOException ex) {
            Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
          
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("statis.fxml")); /* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void excel(ActionEvent event) {
    
         try {

            String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";
       
            cn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM avis");
            //Variable counter for keeping track of number of rows inserted.  
            int counter = 1;
            FileOutputStream fileOut = null;

          

            //Creation of New Work Book in Excel and sheet.  
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            //Creating Headings in Excel sheet.  
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 1).setCellValue("id_avis");//Row Name1  
            rowhead.createCell((short) 2).setCellValue("type_avis");//Row Name2  
            rowhead.createCell((short) 3).setCellValue("id_article");//Row Name3  
          
           
         
            while (rs.next()) {
                //Insertion in corresponding row  
                HSSFRow row = sheet.createRow((int) counter);
                /* Activity, Username, TIME_OF_EVENT are row names  
          * corresponding to table  
          * in related Database. */
                CellStyle dateCellStyle = hwb.createCellStyle();
                CellStyle dateCellStyle1 = hwb.createCellStyle();
                CreationHelper createHelper = hwb.getCreationHelper();
                //Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);
//                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//                dateCellStyle1.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

                row.createCell((short) 1).setCellValue(rs.getInt("id_avis"));
                row.createCell((short) 2).setCellValue(rs.getString("type_avis"));
                row.createCell((short) 3).setCellValue(rs.getInt("id_article"));
               
                

//                row.createCell((short) 3).setCellStyle(dateCellStyle);
//                row.createCell((short) 3).setCellValue(rs.getDate("date"));
//                Cell dateS = row.createCell((short) 4);
//                dateS.setCellValue(rs.getDate("dates"));
//                dateS.setCellStyle(dateCellStyle);
//
//
//                Cell dateE = row.createCell((short) 5);
//                dateE.setCellValue(rs.getDate("datee"));
//                dateE.setCellStyle(dateCellStyle1);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);

                sheet.setZoom(150);

                counter++;
                try {
                    //For performing write to Excel file  
                    fileOut = new FileOutputStream("Avis.xls");
                    hwb.write(fileOut);
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Close all the parameters once writing to excel is compelte.  

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION DIALOG");
            alert.setHeaderText(null);
            alert.setContentText("All courses Has Been Exported in Excel Sheet");
            alert.showAndWait();
            rs.close();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
    
     @FXML
    private void stat(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SpecialisteStat.fxml")); /* Exception */
            Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}