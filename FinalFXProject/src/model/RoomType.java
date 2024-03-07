package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class RoomType {

	private int roomTypeID;
	private String roomType;
	private int maxNumOfGuests;
	private String roomSize;
	private String keyFeatures;
	private String roomTypeDesc;
	private String bedsDesc;
	private ArrayList<RoomAmenity> amenities;
	
	

	public RoomType(int roomTypeID, String roomType, int maxNumOfGuests, String roomSize, String keyFeatures,
			String roomTypeDesc, String bedsDesc) {
		super();
		this.roomTypeID = roomTypeID;
		this.roomType = roomType;
		this.maxNumOfGuests = maxNumOfGuests;
		this.roomSize = roomSize;
		this.keyFeatures = keyFeatures;
		this.roomTypeDesc = roomTypeDesc;
		this.bedsDesc = bedsDesc;
		this.amenities = new ArrayList<>();
	}

	public int getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getMaxNumOfGuests() {
		return maxNumOfGuests;
	}

	public void setMaxNumOfGuests(int maxNumOfGuests) {
		this.maxNumOfGuests = maxNumOfGuests;
	}
	
	

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getKeyFeatures() {
		return keyFeatures;
	}

	public void setKeyFeatures(String keyFeatures) {
		this.keyFeatures = keyFeatures;
	}

	public String getRoomTypeDesc() {
		return roomTypeDesc;
	}

	public void setRoomTypeDesc(String roomTypeDesc) {
		this.roomTypeDesc = roomTypeDesc;
	}

	public String getBedsDesc() {
		return bedsDesc;
	}

	public void setBedsDesc(String bedsDesc) {
		this.bedsDesc = bedsDesc;
	}

	public ArrayList<RoomAmenity> getAmenities() {
		return amenities;
	}

	public void addAmenity(RoomAmenity ra) {
		amenities.add(ra);
	}
	
	public void addAmenities(ArrayList<RoomAmenity> ra) {
		amenities.addAll(ra);
	}

	@Override
	public String toString() {
		return "RoomType [roomTypeID=" + roomTypeID + ", roomType=" + roomType + ", maxNumOfGuests=" + maxNumOfGuests
				+ ", roomSize=" + roomSize + ", keyFeatures=" + keyFeatures + ", roomTypeDesc=" + roomTypeDesc
				+ ", bedsDesc=" + bedsDesc + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(roomTypeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomType other = (RoomType) obj;
		return roomTypeID == other.roomTypeID;
	}
	
	

}
