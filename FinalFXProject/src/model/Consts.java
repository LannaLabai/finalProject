package model;

import java.time.LocalDateTime;

import utils.RequestType;

public class Consts {

	//database connection
	public static final String CONNECTION_STRING = "jdbc:ucanaccess://HotelServiceSystem.accdb";
	
	//read data from tables
	public static final String READ_TBL_FOOD_ITEM_DATA = "SELECT * FROM tblFoodItem";
	public static final String READ_TBL_FOOD_ORDER_ITEMS_DATA = "SELECT * FROM tblFoodOrderItems";
	public static final String READ_TBL_FOOD_ORDER_DATA = "SELECT * FROM tblFoodOrder";
	public static final String READ_TBL_ROOM_TYPE_DATA = "SELECT * FROM tblRoomType";
	public static final String READ_TBL_DONATION_PROJECT_DATA = "SELECT * FROM tblDonationProject";
	public static final String READ_TBL_ROOM_TYPE_HAS_ROOM_AMENITIES = "SELECT * FROM tblRoomTypeHasRoomAmenities"
			+ " WHERE roomTypeID = ?";
	
	public static final String READ_LAST_FOOD_ORDER_BY_CLIENT = "SELECT TOP 1 * FROM tblFoodOrder"
			+ " WHERE clientID = ? AND roomNumber = ?"
			+ " ORDER BY foodOrderID DESC;";
	
	public static final String READ_ROOM_INFO = "SELECT TOP 1 * FROM tblRoom WHERE roomNumber = ? ";
	
	//insert data into tables
	public final static String SQL_INSERT_INTO_FOOD_ORDER = "INSERT INTO tblFoodOrder (clientID, roomNumber, totalPrice, timeOfOrder,orderComplete) VALUES (?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_FOOD_ORDER_ITEMS = "INSERT INTO tblFoodOrderItems (foodItemID, foodOrderID, quantity) VALUES (?,?,?)";
	public final static String SQL_INSERT_INTO_REQUESTS = "INSERT INTO tblRequest (roomNumber, clientID,requestDate, requestType, requestContent,complete) VALUES (?,?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_DONATION = "INSERT INTO tblDonation (donationProjectID, clientID, roomNumber, donationSum, donationDate) VALUES (?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_BOOKED_ROOM_BOOKS_SERVICE = "INSERT INTO tblBookedRoomBooksService (clientID, roomNumber, sessionID) VALUES (?,?,?)";
	public final static String SQL_INSERT_INTO_SESSION = "INSERT INTO tblSession (serviceID, serviceDate, numOfParticipants) VALUES (?,?,?)";
	//public final static String SQL_INSERT_INTO_ALARM_SETTINGS = "INSERT INTO tblAlarmSettings (serviceID, serviceDate, numOfParticipants) VALUES (?,?,?)";

	
}
