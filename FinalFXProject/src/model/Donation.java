package model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Donation {
	
	private int donationID;
	private int donationProjectID;
	private String clientID;
	private int roomNumber;
	private double donationSum;
	private LocalDateTime donationDate;
	
	//for inserting
	public Donation(int donationProjectID, String clientID, int roomNumber, double donationSum, LocalDateTime donationDate) {
		this.donationProjectID = donationProjectID;
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.donationSum = donationSum;
		this.donationDate = donationDate;
	}

	public int getDonationID() {
		return donationID;
	}

	public void setDonationID(int donationID) {
		this.donationID = donationID;
	}

	public int getDonationProjectID() {
		return donationProjectID;
	}

	public void setDonationProjectID(int donationProjectID) {
		this.donationProjectID = donationProjectID;
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

	public double getDonationSum() {
		return donationSum;
	}

	public void setDonationSum(double donationSum) {
		this.donationSum = donationSum;
	}

	public LocalDateTime getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(LocalDateTime donationDate) {
		this.donationDate = donationDate;
	}

	@Override
	public String toString() {
		return "Donation [donationID=" + donationID + ", donationProjectID=" + donationProjectID + ", clientID="
				+ clientID + ", roomNumber=" + roomNumber + ", donationSum=" + donationSum
				+ ", donationDate=" + donationDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(donationID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donation other = (Donation) obj;
		return donationID == other.donationID;
	}

	
}
