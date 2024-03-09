package model;

import java.io.Serializable;

import java.io.*;
import java.util.*;

import control.SQLQueries;
import model.AlarmSettings;
import model.BillItem;
import model.BookedRoom;
import model.BookedRoomBooksService;
import model.Client;
import model.CreditCard;
import model.Donation;
import model.DonationProject;
import model.FoodItem;
import model.FoodOrder;
import model.FoodOrderItems;
//import model.HashMap;
//import model.Integer;
import model.PaidService;
import model.Place;
import model.Session;
import model.Request;
import model.Review;
import model.Room;
import model.RoomType;
import model.Service;
import model.Update;
//import model.Hotel;
import utils.RoomAmenity;
import utils.ServiceType;

public class Hotel implements Serializable {
	
	private static Hotel hotel = null;

	private static final String businessID = "1234567";
	private static final String hotelName = "Domus Bat Galim";
	private static final String hotelAddress = "Bat Galim 6, Haifa";
	private static final String hotelPhoneNumber = "+972-(0)77-364-8450";
	private static final String hotelEmail = "info@domusbatgalim.co.il";
	
	private static String clientID = "211579677";
	private static int roomNumber = 101;
	
	private static String wifiUsername = "user"+String.valueOf(roomNumber);
	private static final String wifiPassword = "domusbatgalim1233";
	
	private ArrayList<AlarmSettings> alarmSettings=null;
	private HashMap<Integer, BillItem> billItems=null;
	private HashMap<Integer, BookedRoom> bookedRooms=null;
	private ArrayList<BookedRoomBooksService> bookedRoomBooksServices=null;
	private HashMap<Integer, Client> clients=null;
	private HashMap<Integer,CreditCard> creditCards=null;
	private HashMap<Integer, Donation> donations=null;
	private ArrayList<DonationProject> donationProjects=null;
	private ArrayList<FoodItem> foodItems=null;
	private ArrayList<FoodOrder> foodOrders=null;
	private HashMap<FoodOrder, ArrayList<FoodOrderItems>> foodOrderItems=null;////////hashmap within it arraylist
	private HashMap<Integer, Place> places=null;
	private HashMap<Integer, ArrayList<Session>> sessions=null;
	private HashMap<Integer, Request> requests=null;
	private HashMap<Integer,Review> reviews=null;
	
	//private HashMap<Integer, Room> rooms;
	private Room room=null;
	private ArrayList<RoomType> roomTypes=null;
	private HashMap<ServiceType,ArrayList<Service>> services=null;
	private ArrayList<Update> updates=null;
	
	
	/////////////////
	public static Hotel getInstance()
	{
		if(hotel==null)
			hotel = new Hotel();
		return hotel;
	}

	// Private constructor to prevent instantiation from other classes
	private Hotel() {
		
		alarmSettings= SQLQueries.readDataFromTblAlarmSettings(clientID, roomNumber);
		billItems= new HashMap<>();
		
		donationProjects= SQLQueries.readDataFromTblDonationProject();
		foodItems= SQLQueries.readDataFromTblFoodItem();
		
		places= new HashMap<>();
		
		room = SQLQueries.readDataFromTblRoom();
		roomTypes= SQLQueries.readDataFromTblRoomType();
		for(RoomType rt: roomTypes) {
			rt.addAmenities(SQLQueries.readDataFromTblRoomTypeHasRoomAmenities(rt.getRoomTypeID()));
		}
		
		services= SQLQueries.readDataFromTblService();
		
		updates= SQLQueries.readDataFromTblUpdate();
		 
	}


	public void defineFoodOrders() {
		foodOrders= SQLQueries.readDataFromTblFoodOrders(clientID, roomNumber);
	}
	
	public void defineFoodOrderItems() {
		if(foodOrders==null) {
			defineFoodOrders();
			
		}
		foodOrderItems= SQLQueries.readDataFromTblFoodOrderItem();
	}
	
	public void defineSessions() {
		sessions = SQLQueries.readDataFromTblSession();
	}
	
	public void defineBookedRoomBooksServices() {
		bookedRoomBooksServices= SQLQueries.readDataFromTblBookedRoomBooksService(clientID, roomNumber);
	}
	

	public static String getWifiUsername() {
		return wifiUsername;
	}

	public static String getWifipassword() {
		return wifiPassword;
	}

	public static String getBusinessId() {
		return businessID;
	}

	public static String getHotelName() {
		return hotelName;
	}

	public static String getHotelAddress() {
		return hotelAddress;
	}

	public static String getHotelPhoneNumber() {
		return hotelPhoneNumber;
	}

	public static String getHotelEmail() {
		return hotelEmail;
	}

	public static String getClientID() {
		return clientID;
	}

	public static int getRoomNumber() {
		return roomNumber;
	}

	
	
	public ArrayList<AlarmSettings> getAlarmSettings() {
		return alarmSettings;
	}

	public HashMap<Integer, BillItem> getBillItems() {
		return billItems;
	}

	public HashMap<Integer, BookedRoom> getBookedRooms() {
		return bookedRooms;
	}

	public ArrayList<BookedRoomBooksService> getBookedRoomBooksServices() {
		return bookedRoomBooksServices;
	}

	public HashMap<Integer, Client> getClients() {
		return clients;
	}

	public HashMap<Integer, CreditCard> getCreditCards() {
		return creditCards;
	}

	public HashMap<Integer, Donation> getDonations() {
		return donations;
	}

	public ArrayList<DonationProject> getDonationProjects() {
		return donationProjects;
	}

	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}

	public ArrayList<FoodOrder> getFoodOrders() {
		return foodOrders;
	}

	public HashMap<FoodOrder, ArrayList<FoodOrderItems>> getFoodOrderItems() {
		return foodOrderItems;
	}

	public HashMap<Integer, Place> getPlaces() {
		return places;
	}

	public HashMap<Integer, ArrayList<Session>> getSessions() {
		return sessions;
	}
	
	public ArrayList<Session> getSessionsByServiceID(int serviceID) {
		return sessions.get(serviceID);
	}

	public HashMap<Integer, Request> getRequests() {
		return requests;
	}

	public HashMap<Integer, Review> getReviews() {
		return reviews;
	}

	public ArrayList<RoomType> getRoomTypes() {
		return roomTypes;
	}

	public HashMap<ServiceType,ArrayList<Service>> getServices() {
		return services;
	}
	
	public ArrayList<Service> getServiceByType(ServiceType st) {
		return services.get(st);
	}

	public ArrayList<Update> getUpdates() {
		return updates;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Hotel [businessID=" + businessID + ", hotelName=" + hotelName + ", hotelAddress=" + hotelAddress
				+ ", hotelPhoneNumber=" + hotelPhoneNumber + ", hotelEmail=" + hotelEmail + "]";
	}
	
	
}
