package model;

import java.time.LocalDateTime;

import utils.RequestType;
import utils.Ringtone;

public class Consts {

	//database connection
	public static final String CONNECTION_STRING = "jdbc:ucanaccess://HotelServiceSystem.accdb";
	
	//read data from tables
	public static final String READ_TBL_FOOD_ITEM_DATA = "SELECT * FROM tblFoodItem";
	public static final String READ_TBL_FOOD_ORDER_ITEMS_DATA = "SELECT * FROM tblFoodOrderItems WHERE foodOrderID = ?";
	public static final String READ_TBL_FOOD_ORDER_DATA = "SELECT * FROM tblFoodOrder WHERE clientID = ? AND roomNumber = ?";
	public static final String READ_TBL_ROOM_TYPE_DATA = "SELECT * FROM tblRoomType";
	public static final String READ_TBL_DONATION_PROJECT_DATA = "SELECT * FROM tblDonationProject";
	public static final String READ_TBL_ROOM_TYPE_HAS_ROOM_AMENITIES = "SELECT * FROM tblRoomTypeHasRoomAmenities"
			+ " WHERE roomTypeID = ?";
	
	public static final String READ_LAST_FOOD_ORDER_BY_CLIENT = "SELECT TOP 1 * FROM tblFoodOrder"
			+ " WHERE clientID = ? AND roomNumber = ?"
			+ " ORDER BY foodOrderID DESC;";
	
	public static final String READ_ROOM_INFO = "SELECT TOP 1 * FROM tblRoom WHERE roomNumber = ? ";
	public static final String READ_TBL_UPDATE = "SELECT * FROM tblUpdate";
	
	public static final String READ_TBL_SERVICE = "SELECT s.*, p.serviceCost FROM tblService s LEFT JOIN tblPaidService p ON s.serviceID = p.serviceID WHERE s.serviceType = ?";
	public static final String READ_TBL_SESSION = "SELECT * FROM tblSession WHERE serviceID = ?";
	public static final String READ_TBL_BOOKED_ROOM_BOOKS_SERVICE = "SELECT * FROM tblBookedRoomBooksService WHERE clientID = ? AND roomNumber = ?";
	public static final String READ_TBL_ALARM_SETTINGS = "SELECT * FROM tblAlarmSettings WHERE clientID = ? AND roomNumber = ?";
	
	
	//insert data into tables
	public final static String SQL_INSERT_INTO_FOOD_ORDER = "INSERT INTO tblFoodOrder (clientID, roomNumber, totalPrice, timeOfOrder,orderComplete) VALUES (?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_FOOD_ORDER_ITEMS = "INSERT INTO tblFoodOrderItems (foodItemID, foodOrderID, quantity) VALUES (?,?,?)";
	public final static String SQL_INSERT_INTO_REQUESTS = "INSERT INTO tblRequest (roomNumber, clientID,requestDate, requestType, requestContent,complete) VALUES (?,?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_DONATION = "INSERT INTO tblDonation (donationProjectID, clientID, roomNumber, donationSum, donationDate) VALUES (?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_BOOKED_ROOM_BOOKS_SERVICE = "INSERT INTO tblBookedRoomBooksService (clientID, roomNumber, sessionID) VALUES (?,?,?)";
	public final static String SQL_INSERT_INTO_SESSION = "INSERT INTO tblSession (serviceID, serviceDate, numOfParticipants) VALUES (?,?,?)";
	public final static String SQL_INSERT_INTO_ALARM_SETTINGS = "INSERT INTO tblAlarmSettings (clientID, roomNumber, alarmDateTime,ringtone, volume, complete) VALUES (?,?,?,?,?,?)";
	public final static String SQL_INSERT_INTO_REVIEW = "INSERT INTO tblReview (reviewContent, rating, clientID, reviewsDate) VALUES (?,?,?,?)";

	//update
	public final static String SQL_UPDATE_ALARM_SETTINGS = "UPDATE tblAlarmSettings SET " +
            "alarmDateTime = ?, ringtone = ?, volume = ?, complete = ? WHERE alarmID = ?";
	public final static String SQL_UPDATE_SESSION = "UPDATE tblSession SET " +
            "sessionDate = ?, numOfParticipants = ? WHERE sessionID = ?";
	
	//delete
	public final static String SQL_DELETE_FROM_TBL_ALARM_SETTINGS = "DELETE FROM tblAlarmSettings WHERE alarmID = ?";
	public final static String SQL_DELETE_FROM_TBL_BOOKED_ROOM_BOOKS_SERVICE = "DELETE FROM tblBookedRoomBooksService WHERE sessionID = ?";
	public final static String SQL_DELETE_FROM_TBL_SESSION = "DELETE FROM tblSession WHERE sessionID = ?";
	//public final static String SQL_DELETE_FROM_TBL_BILL_ITEM = "DELETE FROM tblAlarmSettings WHERE alarmID = ?";
	
}
