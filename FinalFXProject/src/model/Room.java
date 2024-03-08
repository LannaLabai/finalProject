package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Room {
	
	private int roomNumber;
	private int roomTypeID;
	private int roomPhoneNumber;


	public Room(int roomNumber, int roomTypeID, int roomPhoneNumber) {
		super();
		this.roomNumber = roomNumber;
		this.roomTypeID = roomTypeID;
		this.roomPhoneNumber = roomPhoneNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getRoomTypeID() {
		return roomTypeID;
	}

	public int getRoomPhoneNumber() {
		return roomPhoneNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public void setRoomPhoneNumber(int roomPhoneNumber) {
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
