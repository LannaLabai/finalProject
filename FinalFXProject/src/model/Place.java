package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Place {
	
	private int placeID;
	private String placeName;
	private String placeAddress;
	private String placePhoneNumber;
	private String placeEmail;
	private String placeDetails;
	private PlaceType placeType;
	
	public Place(int placeID, String placeName, String placeAddress, String placePhoneNumber, String placeEmail,
			String placeDetails, PlaceType placeType) {
		super();
		this.placeID = placeID;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placePhoneNumber = placePhoneNumber;
		this.placeEmail = placeEmail;
		this.placeDetails = placeDetails;
		this.placeType = placeType;
	}

	public int getPlaceID() {
		return placeID;
	}

	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public String getPlacePhoneNumber() {
		return placePhoneNumber;
	}

	public void setPlacePhoneNumber(String placePhoneNumber) {
		this.placePhoneNumber = placePhoneNumber;
	}

	public String getPlaceEmail() {
		return placeEmail;
	}

	public void setPlaceEmail(String placeEmail) {
		this.placeEmail = placeEmail;
	}

	public String getPlaceDetails() {
		return placeDetails;
	}

	public void setPlaceDetails(String placeDetails) {
		this.placeDetails = placeDetails;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	@Override
	public String toString() {
		return "Place [placeID=" + placeID + ", placeName=" + placeName + ", placeAddress=" + placeAddress
				+ ", placePhoneNumber=" + placePhoneNumber + ", placeEmail=" + placeEmail + ", placeDetails="
				+ placeDetails + ", placeType=" + placeType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(placeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		return placeID == other.placeID;
	}
	
	
	

}
