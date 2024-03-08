package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Session {
	
	private int sessionID; 
	private int serviceID;
	private LocalDateTime sessionDate;
	private int numOfParticipants;
	
	//for inserting
	public Session(int serviceID, LocalDateTime sessionDate, int numOfParticipants) {
		super();
		this.serviceID = serviceID;
		this.sessionDate = sessionDate;
		this.numOfParticipants = numOfParticipants;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public LocalDateTime getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(LocalDateTime sessionDate) {
		this.sessionDate = sessionDate;
	}

	public int getNumOfParticipants() {
		return numOfParticipants;
	}

	public void setNumOfParticipants(int numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
	}

	@Override
	public String toString() {
		return "Session [sessionID=" + sessionID + ", serviceID=" + serviceID + ", sessionDate=" + sessionDate
				+ ", numOfParticipants=" + numOfParticipants + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return sessionID == other.sessionID;
	}
	
	

}
