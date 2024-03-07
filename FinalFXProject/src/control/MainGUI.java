package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
	
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
     
            // Load your FXML and set up the scene
            Parent root = FXMLLoader.load(getClass().getResource("/view/Pool.fxml"));
            Scene scene = new Scene(root, 800, 600);

            // Set up the stage and show it
            primaryStage.setTitle("Your Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        
    }
    /*public static void main(String[] args) {
        launch(args);
    }*/
}