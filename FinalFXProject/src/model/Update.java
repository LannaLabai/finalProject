package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Update {
	
	private int updateID;
	private String updateContent;
	public LocalDateTime publicationDate;
	public int publicationFrequentcyInDays;
	public LocalDateTime showUpdateUntilDate;
	
	public Update(int updateID, String updateContent, LocalDateTime publicationDate, int publicationFrequentcyInDays) {
		this.updateID = updateID;
		this.updateContent = updateContent;
		this.publicationDate = publicationDate;
		this.publicationFrequentcyInDays = publicationFrequentcyInDays;
	}

	public int getUpdateID() {
		return updateID;
	}

	public void setUpdateID(int updateID) {
		this.updateID = updateID;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getPublicationFrequentcyInDays() {
		return publicationFrequentcyInDays;
	}

	public void setPublicationFrequentcyInDays(int publicationFrequentcyInDays) {
		this.publicationFrequentcyInDays = publicationFrequentcyInDays;
	}

	public LocalDateTime getShowUpdateUntilDate() {
		return showUpdateUntilDate;
	}

	public void setShowUpdateUntilDate(LocalDateTime showUpdateUntilDate) {
		this.showUpdateUntilDate = showUpdateUntilDate;
	}

	@Override
	public String toString() {
		return "Update [updateID=" + updateID + ", updateContent=" + updateContent + ", publicationDate="
				+ publicationDate + ", publicationFrequentcyInDays=" + publicationFrequentcyInDays
				+ ", showUpdateUntilDate=" + showUpdateUntilDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(updateID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Update other = (Update) obj;
		return updateID == other.updateID;
	}

	
	
}
