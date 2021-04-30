/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpara.gui;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Marker;
import gestionpara.entities.Mapa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP ENVY
 */
public class MapCordController implements Initializable {

    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude1;
    @FXML
    private TextField latitude1;
    @FXML
    private CheckBox cercle_check;
    boolean test= false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherMap(ActionEvent event) {
         Mapa example = new Mapa("MAP");
          // LatLng pos= new LatLng(Double.valueOf(longitude.getText()), Double.valueOf(latitude.getText()));
          //LatLng pos2= new LatLng(Double.valueOf(longitude1.getText()), Double.valueOf(latitude1.getText()));
LatLng pos= new LatLng(10.0,10.0);
LatLng pos2 = new LatLng(15.2, 15.5);
           Marker m = example.generateMarker(pos);
           if (test)
           {example.generateArea(pos,3.0);}
           example.generateSimplePath(pos,pos2, true);
    }

    @FXML
    private void TestCercle(ActionEvent event) {
        test=true;
    }
    
}
