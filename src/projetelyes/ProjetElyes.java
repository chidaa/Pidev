/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetelyes;

import Reclamation.utils.ConnexionSingleton;
import Reclamation.utils.JavaMailUtil;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author elyes
 */
public class ProjetElyes extends Application {
    
   private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws IOException, Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("/Reclamation/Views/acceuil.fxml"));
         Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
       new animatefx.animation.LightSpeedIn(root).play();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       // ConnexionSingleton BD = new ConnexionSingleton();
    }
    
}
