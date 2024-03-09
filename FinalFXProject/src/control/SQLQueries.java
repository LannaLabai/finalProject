package control;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.*;

import model.*;
import utils.FoodItemType;
import utils.KitchenType;
import utils.Ringtone;
import utils.RoomAmenity;
import utils.ServiceType;

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
	public static HashMap<FoodOrder, ArrayList<FoodOrderItems>> readDataFromTblFoodOrderItem(){
		HashMap<FoodOrder, ArrayList<FoodOrderItems>> foodOrderItems = new HashMap<FoodOrder, ArrayList<FoodOrderItems>>();
		for(FoodOrder fo : Hotel.getInstance().getFoodOrders()) {
			foodOrderItems.put(fo, readDataFromTblFoodOrderItems(fo.getFoodOrderID()));
		}
		return foodOrderItems;
	}
	
	public static ArrayList<FoodOrderItems> readDataFromTblFoodOrderItems(int foodOrderID) {
        ArrayList<FoodOrderItems> dataList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_FOOD_ORDER_ITEMS_DATA);
            statement.setInt(1, foodOrderID);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int foodItemID = resultSet.getInt("foodItemID");
                int quantity = resultSet.getInt("quantity");

                FoodOrderItems foodOrderItem = new FoodOrderItems(foodItemID, foodOrderID, quantity);
                dataList.add(foodOrderItem);
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
        return dataList;
    }
	
	//food order
	public static ArrayList<FoodOrder> readDataFromTblFoodOrders(String clientID, int roomNumber) {
        ArrayList<FoodOrder> dataList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_FOOD_ORDER_DATA);
            statement.setString(1, clientID);
            statement.setInt(2, roomNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int foodOrderID = resultSet.getInt("foodOrderID");
                double totalPrice = resultSet.getDouble("totalPrice");
                LocalDateTime timeOfOrder = resultSet.getTimestamp("timeOfOrder").toLocalDateTime();
                boolean orderComplete = resultSet.getBoolean("orderComplete");

                FoodOrder foodOrder = new FoodOrder(clientID, roomNumber, totalPrice, timeOfOrder, orderComplete);
                foodOrder.setFoodOrderID(foodOrderID);
                dataList.add(foodOrder);
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
	
	public static Room readDataFromTblRoom()
	{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Room room=null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_ROOM_INFO);
            statement.setInt(1, Hotel.getRoomNumber());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                room = new Room(resultSet.getInt("roomNumber"),resultSet.getInt("roomTypeID"),resultSet.getInt("roomPhoneNumber"));
                
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

        return room;
    }
	
	public static ArrayList<Update> readDataFromTblUpdate() {
        ArrayList<Update> updates = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_UPDATE);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int updateID = resultSet.getInt("updateID");
                String updateContent = resultSet.getString("updateContent");
                LocalDateTime publicationDate = resultSet.getTimestamp("publicationDate").toLocalDateTime();
                int publicationFrequencyInDays = resultSet.getInt("publicationFrequencyInDays");
                
                Update updateObject = new Update(updateID, updateContent, publicationDate, publicationFrequencyInDays);
                
                if(resultSet.getTimestamp("showUpdateUntilDate")!=null)
                {
                	LocalDateTime showUpdateUntilDate = resultSet.getTimestamp("showUpdateUntilDate").toLocalDateTime();
                	updateObject.setShowUpdateUntilDate(showUpdateUntilDate);
                }
                
                updates.add(updateObject);
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

        return updates;
    }
	
	//service
	public static HashMap<ServiceType,ArrayList<Service>> readDataFromTblService(){
		HashMap<ServiceType,ArrayList<Service>> services = new HashMap<ServiceType,ArrayList<Service>>();
		for(ServiceType st: ServiceType.values()) {
			services.put(st, readDataFromTblServiceByServiceType(st));
		}
		return services;
	}
	
	public static ArrayList<Service> readDataFromTblServiceByServiceType(ServiceType serviceType) {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Service> services = new ArrayList<>();

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_SERVICE);
            statement.setString(1, serviceType.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Extract data from result set row
                int serviceID = resultSet.getInt("serviceID");
                String serviceName = resultSet.getString("serviceName");
                ServiceType serviceTypeEnum = ServiceType.valueOf(resultSet.getString("serviceType"));
                int maxNumOfParticipants = resultSet.getInt("maxNumOfParticipants");
                String serviceDesc = resultSet.getString("serviceDesc");
                double serviceCost = resultSet.getDouble("serviceCost");

                // Check if it's a paid service
                if (!resultSet.wasNull()) {
                    
                    services.add(new PaidService(serviceID, serviceName, serviceTypeEnum, maxNumOfParticipants, serviceDesc, serviceCost));
                } else {
                    // It's a regular service
                    services.add(new Service(serviceID, serviceName, serviceTypeEnum, maxNumOfParticipants, serviceDesc));
                }
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

        return services;
    }
	
	//session
	public static HashMap<Integer, ArrayList<Session>> readDataFromTblSession(){
		ArrayList<Integer> serviceIDs = new ArrayList<>();
		HashMap<Integer, ArrayList<Session>> sessions = new HashMap<Integer, ArrayList<Session>>();
		
		for(ServiceType st: Hotel.getInstance().getServices().keySet()) {
			for(Service s: Hotel.getInstance().getServices().get(st)) {
				if(s.getSerivceName()!="Spa" && s.getSerivceName()!="Gym") {
					serviceIDs.add(s.getServiceID());
				}
			}
			
		}
		for(Integer i : serviceIDs) {
			sessions.put(i, readDataFromTblSessionByServiceID(i));
		}
		return sessions;
	}
	
	public static ArrayList<Session> readDataFromTblSessionByServiceID(int serviceID) {
        ArrayList<Session> sessions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_SESSION);
            statement.setInt(1, serviceID); // Set the serviceID parameter
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int sessionID = resultSet.getInt("sessionID");
                LocalDateTime sessionDate = resultSet.getTimestamp("sessionDate").toLocalDateTime();
                int numOfParticipants = resultSet.getInt("numOfParticipants");

                Session session = new Session(serviceID, sessionDate, numOfParticipants);
                session.setSessionID(sessionID);
                sessions.add(session);
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

        return sessions;
    }
	
	//booked room books service
	public static ArrayList<BookedRoomBooksService> readDataFromTblBookedRoomBooksService(String clientID, int roomNumber) {
        ArrayList<BookedRoomBooksService> bookedRooms = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_BOOKED_ROOM_BOOKS_SERVICE);
            statement.setString(1, clientID);
            statement.setInt(2, roomNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int sessionID = resultSet.getInt("sessionID");
                BookedRoomBooksService bookedRoom = new BookedRoomBooksService(clientID, roomNumber, sessionID);
                bookedRooms.add(bookedRoom);
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

        return bookedRooms;
    }
	
	//alarm
	public static ArrayList<AlarmSettings> readDataFromTblAlarmSettings(String clientID, int roomNumber) {
        ArrayList<AlarmSettings> alarmSettingsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = AccessDatabaseConnection.connect();
            statement = connection.prepareStatement(Consts.READ_TBL_ALARM_SETTINGS);
            statement.setString(1, clientID);
            statement.setInt(2, roomNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int alarmID = resultSet.getInt("alarmID");
                LocalDateTime alarmDateTime = resultSet.getTimestamp("alarmDateTime").toLocalDateTime();
                Ringtone ringtone = Ringtone.valueOf(resultSet.getString("ringtone"));
                int volume = resultSet.getInt("volumeLevel");
                boolean complete = resultSet.getBoolean("complete");

                // Create a new instance of AlarmSettings with retrieved data
                AlarmSettings alarmSettings = new AlarmSettings(clientID, roomNumber, alarmDateTime, ringtone, volume, complete);
                alarmSettings.setAlarmID(alarmID);
                alarmSettingsList.add(alarmSettings);
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

        return alarmSettingsList;
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
	
	//session
	public static void insertDataIntoTblSession(Session session) {
	    Connection connection = null;
	    try {
	        connection = AccessDatabaseConnection.connect();
	        PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_SESSION, Statement.RETURN_GENERATED_KEYS);

	        statement.setInt(1, session.getServiceID());
	        statement.setTimestamp(2, Timestamp.valueOf(session.getSessionDate()));
	        statement.setInt(3, session.getNumOfParticipants());

	        int rowsInserted = statement.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("Session inserted successfully.");
	        } else {
	            System.out.println("Failed to insert Session.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccessDatabaseConnection.disconnect(connection);
	    }
	}
	
	//alarm settings
	public static void insertDataIntoTblAlarmSettings(AlarmSettings alarmSettings) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_ALARM_SETTINGS, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, alarmSettings.getClientID());
            statement.setInt(2, alarmSettings.getRoomNumber());
            statement.setTimestamp(3, Timestamp.valueOf(alarmSettings.getAlarmDateTime()));
            statement.setString(4, alarmSettings.getRingtone().toString());
            statement.setInt(5, alarmSettings.getVolume());
            statement.setBoolean(6, alarmSettings.isComplete());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Alarm settings inserted successfully.");
            } else {
                System.out.println("Failed to insert alarm settings.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	//review
	public static void insertDataIntoTblReview(Review review) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_REVIEW, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, review.getReviewContent());
            statement.setInt(2, review.getRating());
            statement.setString(3, review.getClientID());
            statement.setTimestamp(4, Timestamp.valueOf(review.getReviewsDate()));

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Review inserted successfully.");
            } else {
                System.out.println("Failed to insert review.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }

	
	//booked room books service
	public static void insertDataIntoTblBookedRoomBooksService(BookedRoomBooksService booking) {
	    Connection connection = null;
	    try {
	        connection = AccessDatabaseConnection.connect();
	        PreparedStatement statement = connection.prepareStatement(Consts.SQL_INSERT_INTO_BOOKED_ROOM_BOOKS_SERVICE, Statement.RETURN_GENERATED_KEYS);

	        statement.setString(1, booking.getClientID());
	        statement.setInt(2, booking.getRoomNumber());
	        statement.setInt(3, booking.getSessionID());

	        int rowsInserted = statement.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("Booking inserted successfully.");
	        } else {
	            System.out.println("Failed to insert Booking.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccessDatabaseConnection.disconnect(connection);
	    }
	}
	
	//update functions
	//alarm settings
	
	public static void updateAlarmSettings(AlarmSettings alarmSettings) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_UPDATE_ALARM_SETTINGS);

            statement.setTimestamp(1, Timestamp.valueOf(alarmSettings.getAlarmDateTime()));
            statement.setString(2, alarmSettings.getRingtone().toString());
            statement.setInt(3, alarmSettings.getVolume());
            statement.setBoolean(4, alarmSettings.isComplete());
            statement.setInt(5, alarmSettings.getAlarmID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Alarm settings updated successfully.");
            } else {
                System.out.println("Failed to update alarm settings.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	public static void updateSession(Session session) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_UPDATE_SESSION);

            statement.setTimestamp(1, Timestamp.valueOf(session.getSessionDate()));
            statement.setInt(2, session.getNumOfParticipants());
            statement.setInt(3, session.getSessionID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Session updated successfully.");
            } else {
                System.out.println("Failed to update session.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	//delete
	//session
	public static void deleteSession(int sessionID) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_DELETE_FROM_TBL_SESSION);

            statement.setInt(1, sessionID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Session deleted successfully.");
            } else {
                System.out.println("No session found with the specified sessionID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	//alarm settings
	public static void deleteAlarmSettings(int alarmID) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_DELETE_FROM_TBL_ALARM_SETTINGS);

            statement.setInt(1, alarmID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Alarm settings deleted successfully.");
            } else {
                System.out.println("No alarm settings found with the specified alarmID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }
	
	//booked room books service
	public static void deleteBookedRoomBooksService(int sessionID) {
        Connection connection = null;
        try {
            connection = AccessDatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(Consts.SQL_DELETE_FROM_TBL_BOOKED_ROOM_BOOKS_SERVICE);

            statement.setInt(1, sessionID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Booked room books service deleted successfully.");
            } else {
                System.out.println("No booked room books service found with the specified sessionID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AccessDatabaseConnection.disconnect(connection);
        }
    }

}
