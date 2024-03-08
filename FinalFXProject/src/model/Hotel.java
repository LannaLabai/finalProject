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
	
	private HashMap<Integer, AlarmSettings> alarmSettings;
	private HashMap<Integer, BillItem> billItems;
	private HashMap<Integer, BookedRoom> bookedRooms;
	private HashMap<Integer, BookedRoomBooksService> bookedRoomBooksServices;
	private HashMap<Integer, Client> clients;
	private HashMap<Integer,CreditCard> creditCards;
	private HashMap<Integer, Donation> donations;
	private ArrayList<DonationProject> donationProjects;
	private ArrayList<FoodItem> foodItems;
	private HashMap<Integer,FoodOrder> foodOrders;
	private HashMap<Integer, FoodOrderItems> foodOrderItems;
	private HashMap<Integer,PaidService> paidServices;
	private HashMap<Integer, Place> places;
	private HashMap<Integer, Session> providedServices;
	private HashMap<Integer, Request> requests;
	private HashMap<Integer,Review> reviews;
	
	//private HashMap<Integer, Room> rooms;
	private Room room;
	private ArrayList<RoomType> roomTypes;
	private HashMap<Integer,Service> services;
	private HashMap<Integer, Update> updates;
	
	
	/////////////////
	public static Hotel getInstance()
	{
		if(hotel==null)
			hotel = new Hotel();
		return hotel;
	}

	// Private constructor to prevent instantiation from other classes
	private Hotel() {
		
		alarmSettings= new HashMap<>();
		billItems= new HashMap<>();
		bookedRooms= new HashMap<>();
		bookedRoomBooksServices= new HashMap<>();
		clients= new HashMap<>();
		creditCards= new HashMap<>();
		donations= new HashMap<>();
		donationProjects= SQLQueries.readDataFromTblDonationProject();
		foodItems= SQLQueries.readDataFromTblFoodItem();
		foodOrders= new HashMap<>();
		foodOrderItems= new HashMap<>();
		paidServices= new HashMap<>();
		places= new HashMap<>();
		//providedServices= SQLQueries.read;
		requests= new HashMap<>();
		reviews= new HashMap<>();
		//rooms= new HashMap<>();
		room = SQLQueries.readDataFromTblRoom();
		roomTypes= SQLQueries.readDataFromTblRoomType();
		for(RoomType rt: roomTypes) {
			rt.addAmenities(SQLQueries.readDataFromTblRoomTypeHasRoomAmenities(rt.getRoomTypeID()));
		}
		
		services= new HashMap<>();
		updates= new HashMap<>();
		 
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

	
	
	public HashMap<Integer, AlarmSettings> getAlarmSettings() {
		return alarmSettings;
	}

	public HashMap<Integer, BillItem> getBillItems() {
		return billItems;
	}

	public HashMap<Integer, BookedRoom> getBookedRooms() {
		return bookedRooms;
	}

	public HashMap<Integer, BookedRoomBooksService> getBookedRoomBooksServices() {
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

	public HashMap<Integer, FoodOrder> getFoodOrders() {
		return foodOrders;
	}

	public HashMap<Integer, FoodOrderItems> getFoodOrderItems() {
		return foodOrderItems;
	}

	public HashMap<Integer, PaidService> getPaidServices() {
		return paidServices;
	}

	public HashMap<Integer, Place> getPlaces() {
		return places;
	}

	public HashMap<Integer, Session> getProvidedServices() {
		return providedServices;
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

	public HashMap<Integer, Service> getServices() {
		return services;
	}

	public HashMap<Integer, Update> getUpdates() {
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
