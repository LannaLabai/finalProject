package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class AlarmSettings {
	private String clientID;
	private int roomNumber;
	private int alarmID;
	private LocalDateTime alarmDateTime;
	private Ringtone ringtone;
	private int volume;
	private boolean complete;
	
	public AlarmSettings(String clientID, int roomNumber, LocalDateTime alarmDateTime,
			Ringtone ringtone, int volume, boolean complete) {

		this.clientID = clientID;
		this.roomNumber = roomNumber;
		this.alarmDateTime = alarmDateTime;
		this.ringtone = ringtone;
		this.volume = volume;
		this.complete = complete;
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

	public int getAlarmID() {
		return alarmID;
	}
	
	public void setAlarmID(int alarmID) {
		this.alarmID=alarmID;
	}

	public LocalDateTime getAlarmDateTime() {
		return alarmDateTime;
	}

	public void setAlarmDateTime(LocalDateTime alarmDateTime) {
		this.alarmDateTime = alarmDateTime;
	}

	public Ringtone getRingtone() {
		return ringtone;
	}

	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "AlarmSettings [clientID=" + clientID + ", roomNumber=" + roomNumber + ", alarmID=" + alarmID
				+ ", alarmDateTime=" + alarmDateTime + ", ringtone=" + ringtone + ", volume="
				+ volume + ", complete=" + complete + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(alarmID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmSettings other = (AlarmSettings) obj;
		return alarmID == other.alarmID;
	}

	
	
	
}
