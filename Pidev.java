/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Desktop.test.MyConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class Pidev extends Application {
     public static int id_a=0;
     private static Pidev instance;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menus.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    public static Pidev getInstance() {
        return instance;}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        MyConnection ds = new MyConnection();
    }
    
}
