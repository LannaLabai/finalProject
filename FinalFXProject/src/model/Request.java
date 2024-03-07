package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Request {
	
	private int requestID;
	private int roomNumber;
	private String clientID;
	private LocalDateTime requestDate;
	private RequestType requestType;
	private String requestContent;
	private boolean complete;

	//for reading from database
	public Request(int requestID, int roomNumber, String clientID,LocalDateTime requestDate, RequestType requestType,
			String requestContent, boolean complete) {
		super();
		this.requestID = requestID;
		this.roomNumber = roomNumber;
		this.clientID = clientID;
		this.requestDate = requestDate;
		this.requestType = requestType;
		this.requestContent = requestContent;
		this.complete = complete;
	}
	
	//for inserting into database
	public Request(int roomNumber, String clientID,LocalDateTime requestDate, RequestType requestType,String requestContent) {
		super();
		this.roomNumber = roomNumber;
		this.clientID = clientID;
		this.requestDate = requestDate;
		this.requestType = requestType;
		this.requestContent = requestContent;
		this.complete = false;
	}

	public int getRequestId() {
		return requestID;
	}

	public void setRequestId(int requestID) {
		this.requestID = requestID;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	
	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	
	

	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", roomNumber=" + roomNumber + ", clientID=" + clientID
				+ ", requestDate=" + requestDate + ", requestType=" + requestType + ", requestContent=" + requestContent
				+ ", complete=" + complete + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(requestID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return requestID == other.requestID;
	}
	
	
	

}
