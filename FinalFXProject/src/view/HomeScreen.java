package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HomeScreen extends Application {
    @FXML
    private Label dateTimeLabelID;
    @FXML 
    private ImageView logoID;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Pane mainPane;
    @FXML
    private HBox mainHbox;
//showing the date on screen
    @Override
    public void start(Stage mainStage) {
    	Image image = new Image("images/logo.jpg");
        logoID.setImage(image);
        double newWidth=200;
        double newHeight=150; 
        logoID.setFitWidth(newWidth);
        logoID.setFitHeight(newHeight);
        
        mainStage.setTitle("Date and Time App");

        updateDateTime(dateTimeLabelID); // Initial update
        
     // Create a Timeline to update the label every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateDateTime(dateTimeLabelID))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        mainHbox.setSpacing(10); // Set spacing between children
        mainHbox.setAlignment(Pos.CENTER); // Set alignment (adjust as needed)
        
        mainHbox.getChildren().addAll(logoID, dateTimeLabelID);
        mainPane.getChildren().add(mainHbox);
        mainAnchorPane.getChildren().add(mainPane);

        //StackPane root = new StackPane();
        //root.getChildren().add(mainAnchorPane);
        //root.getChildren().add(logoID);
        AnchorPane root = mainAnchorPane;
        mainStage.setScene(new Scene(root, 300, 200));
        mainStage.show();
        
    }

    // Method to update the date and time
    private void updateDateTime(Label dateTimeLabelID) {
    	
    	    LocalDateTime now = LocalDateTime.now(); // Use LocalDateTime instead of LocalTime
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	    String formattedDateTime = now.format(formatter);
    	    dateTimeLabelID.setText(formattedDateTime);
    	
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String formattedDateTime = dateFormat.format(new Date());
    }
//the end
   
    public static void main(String[] args) {
        launch(args);
    }
}





















	


