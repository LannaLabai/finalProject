package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class CreditCard {
	
	private String creditCardNumber;
	private String cvv;
	private LocalDate creditCardExp;
	private String ownerFirstName;
	private String ownerLastName;
	private String clientID;
	
	public CreditCard(String creditCardNumber, String cvv, LocalDate creditCardExp, 
			String ownerFirstName, String ownerLastName,String clientID) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
		this.creditCardExp = creditCardExp;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.clientID = clientID;
	}

	
	public String getOwnerFirstName() {
		return ownerFirstName;
	}


	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}


	public String getOwnerLastName() {
		return ownerLastName;
	}


	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}


	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public LocalDate getCreditCardExp() {
		return creditCardExp;
	}

	public void setCreditCardExp(LocalDate creditCardExp) {
		this.creditCardExp = creditCardExp;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", cvv=" + cvv + ", creditCardExp=" + creditCardExp
				+ ", ownerFirstName=" + ownerFirstName + ", ownerLastName=" + ownerLastName + ", clientID=" + clientID
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(clientID, creditCardExp, creditCardNumber, cvv);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(clientID, other.clientID) && Objects.equals(creditCardExp, other.creditCardExp)
				&& Objects.equals(creditCardNumber, other.creditCardNumber) && Objects.equals(cvv, other.cvv);
	}

	


}
