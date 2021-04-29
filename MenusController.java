/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MenusController implements Initializable {

    @FXML
    private Button Bempty;
    @FXML
    private Button Bprofile;
    @FXML
    private ImageView LBimguser;
    @FXML
    private Label lblClose;
    @FXML
    private Button Bprofile1;
    @FXML
    private Button Bempty1;
    @FXML
    private Text Tempty;
    @FXML
    private Text Tprofile;
    @FXML
    private Text Tempty3;
    @FXML
    private Text Tempty2;
    @FXML
    private Label LBnomuser;
    @FXML
    private ImageView lbllogout;
    @FXML
    private Text Tempty21;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hayaa(ActionEvent event) {
    }

    @FXML
    private void btnProfile(ActionEvent event) {
    }

    @FXML
    private void service(ActionEvent event) {
    }

    @FXML
    private void blog(ActionEvent event) throws Exception { {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
}
