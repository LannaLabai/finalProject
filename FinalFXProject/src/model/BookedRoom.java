package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class BookedRoom {
	
	private String clientID;
	private String roomNumber;
	private LocalDateTime checkinDate;
	private LocalDateTime checkoutDate;
	private int numOfGuests;

	public BookedRoom(String clientID, String roomNumber, LocalDateTime checkinDate, LocalDateTime checkoutDate,
			int numOfGuests) {
		super();
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.numOfGuests = numOfGuests;
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

	public LocalDateTime getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDateTime checkinDate) {
		this.checkinDate = checkinDate;
	}

	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	@Override
	public String toString() {
		return "BookedRoom [clientID=" + clientID + ", roomNumber=" + roomNumber + ", checkinDate=" + checkinDate
				+ ", checkoutDate=" + checkoutDate + ", numOfGuests=" + numOfGuests + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkinDate, clientID, roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookedRoom other = (BookedRoom) obj;
		return Objects.equals(checkinDate, other.checkinDate) && Objects.equals(clientID, other.clientID)
				&& Objects.equals(roomNumber, other.roomNumber);
	}

	

}
