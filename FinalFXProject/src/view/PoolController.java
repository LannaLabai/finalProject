package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import control.SQLServerConnection;

public class PoolController implements Initializable {

    @FXML
    private Label label1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Connection connection = SQLServerConnection.connect()) {
            // Use the connection to execute queries
            String sql = "SELECT serviceDesc FROM tblService WHERE serviceID = 25";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Process the result set
                StringBuilder data = new StringBuilder();
                while (resultSet.next()) {
                    String value = resultSet.getString("serviceDesc");
                    data.append(value);
                }
                label1.setText(data.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
