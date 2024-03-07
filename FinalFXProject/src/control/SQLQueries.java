package control;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.*;

import model.*;
import utils.FoodItemType;
import utils.KitchenType;
import utils.RoomAmenity;

public class SQLQueries {
	
	//read data from tables
	
	public static ArrayList<FoodItem> readDataFromTblFoodItem() {
        ArrayList<FoodItem> dataList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Consts.READ_TBL_FOOD_ITEM_DATA)) {

                while (resultSet.next()) {
                	int foodItemID = resultSet.getInt("foodItemID");
                	String foodItemName  = resultSet.getString("foodItemName");
                	double foodItemCost = resultSet.getDouble("foodItemCost");
                	FoodItemType foodItemType = FoodItemType.valueOf(resultSet.getString("foodItemType"));
                	KitchenType kitchenType = KitchenType.valueOf(resultSet.getString("kitchenType"));
                	
                    FoodItem foodItem = new FoodItem(foodItemID,foodItemName, foodItemCost, foodItemType,kitchenType);
                    dataList.add(foodItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	AccessDatabaseConnection.disconnect(connection);
        }
        return dataList;
    }
	
	//food order items
	public static ArrayList<FoodOrderItems> readDataFromTblFoodOrderItems() {
        ArrayList<FoodOrderItems> dataList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Consts.READ_TBL_FOOD_ORDER_ITEMS_DATA)) {

                while (resultSet.next()) {
                	int foodItemID = resultSet.getInt("foodItemID");
                	int foodOrderID  = resultSet.getInt("foodOrderID");
                	int quantity = resultSet.getInt("quantity");
                	
                    FoodOrderItems foodOrderItem = new FoodOrderItems(foodItemID,foodOrderID, quantity);
                    dataList.add(foodOrderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	AccessDatabaseConnection.disconnect(connection);
        }
        return dataList;
    }
	
	//food order
	public static ArrayList<FoodOrder> readDataFromTblFoodOrders() {
        ArrayList<FoodOrder> dataList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(Consts.READ_TBL_FOOD_ORDER_DATA)) {

                while (resultSet.next()) {
                    int foodOrderID = resultSet.getInt("foodOrderID");
                    String clientID = resultSet.getString("clientID");
                    int roomNumber = resultSet.getInt("roomNumber");
                    double totalPrice = resultSet.getDouble("totalPrice");
                    LocalDateTime timeOfOrder = resultSet.getTimestamp("timeOfOrder").toLocalDateTime();
                    boolean orderComplete = resultSet.getBoolean("orderComplete");

                    FoodOrder foodOrder = new FoodOrder( clientID, roomNumber, totalPrice, timeOfOrder,orderComplete);
                    foodOrder.setFoodOrderID(foodOrderID);
                    dataList.add(foodOrder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
        return dataList;
    }
	
	public static int readLastFoodOrderByClient(String clientID, int roomNumber) {
	    Connection connection = null;
	    int foodOrderID = -1; 

	    try {
	        connection = AccessDatabaseConnection.connect();
	        PreparedStatement statement = connection.prepareStatement(Consts.READ_LAST_FOOD_ORDER_BY_CLIENT);
	        
	        statement.setString(1, clientID);
	        statement.setInt(2, roomNumber);
	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            foodOrderID = resultSet.getInt("foodOrderID");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccessDatabaseConnection.disconnect(connection);
	    }

	    return foodOrderID;
	}
	
	public static ArrayList<RoomType> readDataFromTblRoomType() {
		ArrayList<RoomType> roomTypes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_ROOM_TYPE_DATA);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int roomTypeID = resultSet.getInt("roomTypeID");
                String roomType = resultSet.getString("roomType");
                int maxNumOfGuests = resultSet.getInt("maxNumOfGuests");
                String roomSize = resultSet.getString("roomSize");
                String keyFeatures = resultSet.getString("keyFeatures");
                String roomTypeDesc = resultSet.getString("roomTypeDesc");
                String bedsDesc = resultSet.getString("bedsDesc");

                RoomType roomTypeObject = new RoomType(roomTypeID,roomType, maxNumOfGuests,roomSize, keyFeatures, roomTypeDesc, bedsDesc);
                roomTypes.add(roomTypeObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                AccessDatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roomTypes;
    }
	
	public static ArrayList<RoomAmenity> readDataFromTblRoomTypeHasRoomAmenities(int givenRoomTypeID){
		ArrayList<RoomAmenity> roomAmenities = new ArrayList<RoomAmenity>();
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_ROOM_TYPE_HAS_ROOM_AMENITIES);
            statement.setInt(1, givenRoomTypeID);
            resultSet = statement.executeQuery();
            

            while (resultSet.next()) {
                RoomAmenity amenity = RoomAmenity.valueOf(resultSet.getString("roomAmenity"));
                
                roomAmenities.add(amenity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                AccessDatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roomAmenities;
	}
	
	public static ArrayList<DonationProject> readDataFromTblDonationProject()
	{
		ArrayList<DonationProject> donationProjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_DONATION_PROJECT_DATA);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DonationProject project = new DonationProject(resultSet.getInt("donationProjectID"),resultSet.getString("donationProjectName"),resultSet.getString("projectDetails"));
                
                donationProjects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                AccessDatabaseConnection.disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return donationProjects;
    }

	
	//insert data into tables
	public static void insertDataIntoTblFoodOrder(FoodOrder foodOrder) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_FOOD_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, foodOrder.getClientID());
            statement.setInt(2, foodOrder.getRoomNumber());
            statement.setDouble(3, foodOrder.getTotalPrice());
            statement.setTimestamp(4, Timestamp.valueOf(foodOrder.getTimeOfOrder()));
            statement.setBoolean(5, foodOrder.isOrderComplete());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("FoodOrder inserted successfully.");
            } else {
                System.out.println("Failed to insert FoodOrder.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	public static void insertDataIntoTblFoodOrderItems(FoodOrderItems foodOrderItem) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_FOOD_ORDER_ITEMS);

            // Set values for parameters
            statement.setInt(1, foodOrderItem.getFoodItemID());
            statement.setInt(2, foodOrderItem.getFoodOrderID());
            statement.setInt(3, foodOrderItem.getQuantity());

            // Execute the INSERT statement
            int rowsInserted = statement.executeUpdate();

            // Check if insertion was successful
            if (rowsInserted > 0) {
                System.out.println("FoodOrderItem inserted successfully.");
            } else {
                System.out.println("Failed to insert FoodOrderItem.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	public static void insertDataIntoTblRequest(Request request) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_REQUESTS, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, request.getRoomNumber());
            statement.setString(2, request.getClientID());
            statement.setTimestamp(3, Timestamp.valueOf(request.getRequestDate()));
            statement.setString(4, String.valueOf(request.getRequestType()));
            statement.setString(5, request.getRequestContent());
            statement.setBoolean(6, request.isComplete());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("FoodOrder inserted successfully.");
            } else {
                System.out.println("Failed to insert FoodOrder.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	public static void insertDataIntoTblDonation(Donation donation) {
	    Connection connection = null;
	    try {
	        connection = AccessDatabaseConnection.connect();
	        PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_DONATION, Statement.RETURN_GENERATED_KEYS);

	        statement.setInt(1, donation.getDonationProjectID());
	        statement.setString(2, donation.getClientID());
	        statement.setInt(3, donation.getRoomNumber());
	        statement.setDouble(4, donation.getDonationSum());
	        statement.setTimestamp(5, Timestamp.valueOf(donation.getDonationDate()));

	        int rowsInserted = statement.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("Donation inserted successfully.");
	        } else {
	            System.out.println("Failed to insert Donation.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccessDatabaseConnection.disconnect(connection);
	    }
	}


}
