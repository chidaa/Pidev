package pidev;


import Desktop.Services.ArticlesService;
import Desktop.Services.RatingService;
import Desktop.entities.article;
import Desktop.entities.rating;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Desktop.Services.MyListener;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Rating;


public class Market2Controller implements Initializable {
    @FXML
    private VBox chosenProductCard;

    @FXML
    private Label productNameLable;

   

    @FXML
    private ImageView productImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<article> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Label productPriceLabel;
   
    @FXML
    private Label id;
    
    private TextField qty;

    ItemController i;
    private Label pricelabel1;
    @FXML
    private Rating rate;
     private String filename="historique.txt";
   
    private TextField txt;

    private void setChosenProduct(article product) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("item.fxml"));
        //Node postbox = loader.load();
        ItemController pc = loader.getController();
        pc.setData(product, myListener,this.chosenProductCard);
        productNameLable.setText(product.getSujet());
        txt.setText(product.getDescription());
        //pricelabel1.setText(product.getDescription());
        //text.setText(product.getDescription());
        image = new Image(getClass().getResourceAsStream(product.getImage()));
        productImg.setImage(image);
        String disp = "";
        chosenProductCard.setStyle("-fx-background-color: red" + disp + ";\n" +
                "    -fx-background-radius: 30;");
  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
        // products.addAll(getData());
        if (products.size() > 0) {
            try {
                setChosenProduct(products.get(0));
            } catch (IOException ex) {
                
            }
            myListener = new MyListener() {
                @Override
                public void onClickListener(article product) {
                    try {
                        setChosenProduct(product);
                    } catch (IOException ex) {
                        
                    }
                }

               
            };
        }
        grid.getChildren().clear();
        ArticlesService sp = new ArticlesService();
        List<article> l = sp.afficher();
        int row = 1, cl =0;
        try{
            for(article product : l){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("item.fxml"));
                Node postbox = loader.load();
                ItemController pc = loader.getController();
                pc.setData(product, myListener,this.chosenProductCard);
                if(cl== 3){
                    cl= 0;
                    row++;
                }
                this.grid.add(postbox, cl++, row);
            }
        }catch(IOException e){
            
        }
    }

       
    @FXML
    public void ajouterRating(MouseEvent event) {
        System.out.print(Pidev.id_a);
         RatingService se = new RatingService();
        List <Integer> list  = se.getEventsList3();
        rating r = new rating();
        if (list.contains(Pidev.id_a))
        {
            r.setId_rate(se.getidrate(Pidev.id_a));
            r.setRate((int)rate.getRating());
          r.setId_article(Pidev.id_a);
            se.modifierRate(r);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Succes de modification");
          alert.setHeaderText(null);
          alert.setContentText(" rateS modifié avec succes!");
          alert.showAndWait();
          
           PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("rate "+String.valueOf((int)rate.getRating())+" ajouté sur l'article "+String.valueOf(Pidev.id_a));
             outputstream.close();
        } catch (IOException ex) {
            //Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
        {
           r.setRate((int)rate.getRating());
          r.setId_article(Pidev.id_a);
          se.ajouterRate(r); 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes d ajout");
         alert.setHeaderText(null);
         alert.setContentText("rating ajoute avec succes!");
         alert.showAndWait();
         
         PrintWriter outputstream = null;
        try {
            outputstream = new PrintWriter(new FileWriter(filename, true));
            outputstream.println("rate "+String.valueOf((int)rate.getRating())+" ajouté sur l'article "+String.valueOf(Pidev.id_a));
             outputstream.close();
        } catch (IOException ex) {
           // Logger.getLogger(AvisGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
          
    }
  @FXML
  public void ajouterAvis(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AvisGUI.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
