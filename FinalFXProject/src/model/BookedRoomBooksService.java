package model;

import java.util.Objects;

public class BookedRoomBooksService {
	
	private String clientID;
	private String roomNumber;
	private int sessionID;
	
	public BookedRoomBooksService(String clientID, String roomNumber, int sessionID) {
		super();
		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.sessionID = sessionID;
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

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String toString() {
		return "BookedRoomBooksService [clientID=" + clientID + ", roomNumber=" + roomNumber + ", sessionID="
				+ sessionID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientID, roomNumber, sessionID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookedRoomBooksService other = (BookedRoomBooksService) obj;
		return Objects.equals(clientID, other.clientID) && Objects.equals(roomNumber, other.roomNumber)
				&& sessionID == other.sessionID;
	}
	
	
}
