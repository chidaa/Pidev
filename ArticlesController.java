/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Desktop.Services.ArticlesService;
import Desktop.Services.CategorieService;
import Desktop.Services.RatingService;
import Desktop.entities.Categorie;
import Desktop.entities.article;
import Desktop.entities.rating;
import Desktop.test.MyConnection;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.teknikindustries.bulksms.SMS;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


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
    private TextField chercherArticle;
    @FXML
    private ComboBox<Integer> gComboBox2;
    @FXML
    private ImageView photo_view;
      private Image image;
    @FXML
    private TextField photo_p;
    @FXML
        private ComboBox<Integer> check;
    ArticlesService ms = new ArticlesService();
     RatingService ms1 = new RatingService();
     ObservableList options2 = FXCollections.observableArrayList();
       ObservableList options = FXCollections.observableArrayList();
       
       ObservableList data1 = FXCollections.observableArrayList();
    @FXML
    private CheckBox ch;
    @FXML
    private CheckBox ch1;
    private ScrollPane scroll;
    private GridPane grid;
  ArticlesService cs = new ArticlesService();

    List <article> recommandationssarticle = cs.afficher();
    @FXML
    private Button buttonajouter;
    @FXML
    private Button gsupp;
    @FXML
    private Button buttonmodifier;
    @FXML
    private Button gretour;
    @FXML
    private Button gtri;
    @FXML
    private Button btn111;
    @FXML
    private TableColumn<rating, Integer> AfficheID1;
    @FXML
    private TableColumn<rating, Integer> AfficheID_c1;
    @FXML
    private TableColumn<rating, Integer> Affichesujet1;
    @FXML
    private TableView<rating> ratingTable1;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Actualisercombox2();
        Actualisercombox1();
       view2();
        view3();
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
    public void AddAction(ActionEvent event) {
        if(verifUserChamps())
        {
              article l = new article();
        String id = gid.getText();
        l.setId(Integer.parseInt(id));

        l.setCategorie_id(gComboBox2.getSelectionModel().getSelectedItem());
        l.setDescription(gdescription.getText());
        l.setSujet(gsujet.getText());
        l.setImage(photo_p.getText().replace('\\', '/'));
       SMS sms =new SMS();
        sms.SendSMS("ziedo", "Ziedkamoun99", "test test", "+21625819730", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
            System.out.println("test sms");
           
       /////////////////////////
      //combobox
        
       //////////////////////////////
       ms.ajouter(l);
            search_user();
        JOptionPane.showMessageDialog(null, "Article ajouté");
        view2();
        Actualisercombox1();
         Actualisercombox2();
         
         Notifications notificationbuilder = Notifications.create().title("Article Ajoute").text("Article Ajouté a la base de donné").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                      System.out.print("cc");}
                  
                  
         });
         
          notificationbuilder.showConfirm();
          
        }
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://localhost:3306/pidev";
        try {
            cn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM article");
        
       
         
            while (rs.next()) {
            	ArticlesController.generate_qr(rs.getString("id"),rs.getString("sujet"));
            }
            } catch (Exception e) {
			System.err.println(e);
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
    public void ActionSupp2(ActionEvent event) {
        
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
    public void ActionModifier2(ActionEvent event) {
        
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
    public void categorieAction(ActionEvent event) throws IOException {
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
    public void TriAction(ActionEvent event) {
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
    public void ch(ActionEvent event) {
        ch1.setSelected(false);
    }

    @FXML
    public void ch1(ActionEvent event) {
        ch.setSelected(false);
    }
    @FXML
    public void uploadimage(ActionEvent event) {
                 FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_view.setImage(image);
                photo_p.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                
            }
    }
    public void MouseButton(MouseEvent event) {
        
        
        article s = ArticleTable.getSelectionModel().getSelectedItem();
       System.out.print("salem");
        File file = new File(s.getImage());
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_view.setImage(image);
                 photo_p.setText(file.getAbsolutePath());
            } 
        catch (IOException ex) {
          Logger.getLogger(ArticlesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    
    }
    @FXML
    public void ImpAction(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(ArticleTable);
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
   
public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\pidev"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

public void view3() {
        ObservableList<rating> list = ms1.afficher();
        
        AfficheID_c1.setCellValueFactory(new PropertyValueFactory<rating, Integer>("rate"));
        Affichesujet1.setCellValueFactory(new PropertyValueFactory<rating, Integer>("id_article"));
        

      ratingTable1.setItems(list);
        
        
    }

}

        


