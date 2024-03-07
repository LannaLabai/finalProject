package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class BillItem {

	private int billItemID; 
	private String clientID;
	private String roomNumber;
	private int serviceID;
	
	//reading from database
	public BillItem(int billItemID, String clientID, String roomNumber, int serviceID) {
		super();
		this.billItemID = billItemID;
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.serviceID = serviceID;
	}
	
	//saving to database
	public BillItem(String clientID, String roomNumber, int serviceID) {
		super();
		this.billItemID = billItemID;
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.serviceID = serviceID;
	}

	public int getBillItemID() {
		return billItemID;
	}

	public void setBillItemID(int billItemID) {
		this.billItemID = billItemID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public String toString() {
		return "BillItem [billItemID=" + billItemID + ", clientID=" + clientID + ", roomNumber=" + roomNumber
				+ ", serviceID=" + serviceID + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(billItemID, clientID, roomNumber, serviceID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillItem other = (BillItem) obj;
		return billItemID == other.billItemID;
	}
	
}
