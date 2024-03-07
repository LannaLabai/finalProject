package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FoodOrder {
	
	private int foodOrderID; 
	private String clientID;
	private int roomNumber;
	private double totalPrice;
	private LocalDateTime timeOfOrder;
	private boolean orderComplete;
	private ArrayList<FoodOrderItems> orderContent;
	
	//for saving data
	public FoodOrder(String clientID, int roomNumber, double totalPrice,LocalDateTime timeOfOrder) {
		super();
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.totalPrice = totalPrice;
		this.timeOfOrder = timeOfOrder;
		this.orderComplete = false;
		this.orderContent = new ArrayList<>();
	}
	
	//for reading data
	public FoodOrder(String clientID, int roomNumber, double totalPrice,LocalDateTime timeOfOrder, boolean orderComplete) {
		super();
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.totalPrice = totalPrice;
		this.timeOfOrder = timeOfOrder;
		this.orderComplete = orderComplete;
		this.orderContent = new ArrayList<>();
	}

	public int getFoodOrderID() {
		return foodOrderID;
	}

	public void setFoodOrderID(int foodOrderID) {
		this.foodOrderID = foodOrderID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getTimeOfOrder() {
		return timeOfOrder;
	}

	public void setTimeOfOrder(LocalDateTime timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}

	public boolean isOrderComplete() {
		return orderComplete;
	}

	public void setOrderComplete(boolean orderComplete) {
		this.orderComplete = orderComplete;
	}

	public ArrayList<FoodOrderItems> getOrderContent() {
		return orderContent;
	}

	public void addOrderContent(FoodOrderItems item) {
		this.orderContent.add(item);
	}

	@Override
	public String toString() {
		return "FoodOrder [foodOrderID=" + foodOrderID + ", clientID=" + clientID + ", roomNumber=" + roomNumber
				+ ", totalPrice=" + totalPrice + ", timeOfOrder=" + timeOfOrder + ", orderComplete=" + orderComplete
				+ ", orderContent=" + orderContent + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(foodOrderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodOrder other = (FoodOrder) obj;
		return foodOrderID == other.foodOrderID;
	}
	
	
	

}
