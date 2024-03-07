package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Room {
	
	private String roomNumber;
	private int roomTypeID;
	private String roomPhoneNumber;
	
	public Room(String roomNumber, int roomTypeID, String roomPhoneNumber) {
		super();
		this.roomNumber = roomNumber;
		this.roomTypeID = roomTypeID;
		this.roomPhoneNumber = roomPhoneNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getTypeOfRoom() {
		return roomTypeID;
	}

	public void setTypeOfRoom(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getRoomPhoneNumber() {
		return roomPhoneNumber;
	}

	public void setRoomPhoneNumber(String roomPhoneNumber) {
		this.roomPhoneNumber = roomPhoneNumber;
	}



	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomTypeID=" + roomTypeID + ", roomPhoneNumber=" + roomPhoneNumber
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Objects.equals(roomNumber, other.roomNumber);
	}
	
	
	

}
