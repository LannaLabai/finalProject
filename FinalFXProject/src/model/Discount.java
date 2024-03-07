package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Discount {
	
	private int discountID;
	private int serviceID;
	private double discountPercentage;
	private int serviceAmountOrderedForDiscount;
	private LocalDateTime discountValidUntil;
	
	public Discount(int discountID, int serviceID, double discountPercentage, int serviceAmountOrderedForDiscount,
			LocalDateTime discountValidUntil) {
		super();
		this.discountID = discountID;
		this.serviceID = serviceID;
		this.discountPercentage = discountPercentage;
		this.serviceAmountOrderedForDiscount = serviceAmountOrderedForDiscount;
		this.discountValidUntil = discountValidUntil;
	}

	public int getDiscountID() {
		return discountID;
	}

	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public int getServiceAmountOrderedForDiscount() {
		return serviceAmountOrderedForDiscount;
	}

	public void setServiceAmountOrderedForDiscount(int serviceAmountOrderedForDiscount) {
		this.serviceAmountOrderedForDiscount = serviceAmountOrderedForDiscount;
	}

	public LocalDateTime getDiscountValidUntil() {
		return discountValidUntil;
	}

	public void setDiscountValidUntil(LocalDateTime discountValidUntil) {
		this.discountValidUntil = discountValidUntil;
	}

	@Override
	public String toString() {
		return "Discount [discountID=" + discountID + ", serviceID=" + serviceID + ", discountPercentage="
				+ discountPercentage + ", serviceAmountOrderedForDiscount=" + serviceAmountOrderedForDiscount
				+ ", discountValidUntil=" + discountValidUntil + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(discountID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		return discountID == other.discountID;
	}
	
	

}
