package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Review {
	
	private int reviewsID;
	private String reviewContent;
	private int rating;
	private String clientID;
	private LocalDateTime reviewsDate;
	
	public Review(String reviewContent, int rating, String clientID, LocalDateTime reviewsDate) {
		super();
		this.reviewContent = reviewContent;
		this.rating = rating;
		this.clientID = clientID;
		this.reviewsDate = reviewsDate;
	}

	public int getReviewsID() {
		return reviewsID;
	}

	public void setReviewsID(int reviewsID) {
		this.reviewsID = reviewsID;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public LocalDateTime getReviewsDate() {
		return reviewsDate;
	}

	public void setReviewsDate(LocalDateTime reviewsDate) {
		this.reviewsDate = reviewsDate;
	}

	@Override
	public String toString() {
		return "Reviews [reviewsID=" + reviewsID + ", reviewContent=" + reviewContent + ", rating=" + rating
				+ ", clientID=" + clientID + ", reviewsDate=" + reviewsDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(reviewsID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return reviewsID == other.reviewsID;
	}
	
	
	
	

}
