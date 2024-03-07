package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pool extends Application {
	
	@FXML
	StackPane mainStack;
	
	@FXML
	Pane mainPane;
	
	@FXML
	AnchorPane mainAnchorPane;
	
	@FXML
	Label label1;
	
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Connect to the database (replace with your database details)
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Execute a query to fetch data
            String sql = "SELECT serviceDesc FROM tblService WHERE serviceID=25";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Create a Label to display the fetched data
                StringBuilder data = new StringBuilder();
                while (resultSet.next()) {
                	if (resultSet.getString("serviceDesc")!=null)
                	{
                		String value = resultSet.getString("serviceDesc");
                        data.append(value);
                	}
                    
                }
                label1 = new Label(data.toString());
                
                mainPane.getChildren().add(label1);
                mainAnchorPane.getChildren().add(mainPane);

                // Create a Scene with the VBox layout
                Scene scene = new Scene(mainAnchorPane, 300, 200);

                // Set the title of the application window
                primaryStage.setTitle("Database Data Display");

                // Set the Scene for the primaryStage
                primaryStage.setScene(scene);

                // Show the application window
                primaryStage.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

