package model;

import java.io.*;
import java.util.*;

public class Client {
	
	private String clientID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	
	
	public Client(String clientID, String firstName, String lastName, String phoneNumber, String email) {
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}


	public String getClientId() {
		return clientID;
	}


	public void setClientId(String clientID) {
		this.clientID = clientID;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(clientID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientID, other.clientID);
	}
	

	
}
