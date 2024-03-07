package control;

import java.sql.*;

public class AccessDatabaseConnection {
	
	public static Connection connect() throws SQLException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            return DriverManager.getConnection(model.Consts.CONNECTION_STRING);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC driver not found", e);
        }
    }

    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
    	connect();
    }

}
