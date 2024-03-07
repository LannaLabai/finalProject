package model;

import java.io.*;
import java.util.*;

public class FoodOrderItems {

	private int foodItemID;
	private int foodOrderID;
	private int quantity;
	
	public FoodOrderItems(int foodItemID, int quantity) {
		super();
		this.foodItemID = foodItemID;
		this.foodOrderID=0;
		this.quantity = quantity;
	}
	
	public FoodOrderItems(int foodItemID, int foodOrderID, int quantity) {
		super();
		this.foodItemID = foodItemID;
		this.foodOrderID = foodOrderID;
		this.quantity = quantity;
	}

	public int getFoodItemID() {
		return foodItemID;
	}

	public void setFoodItemID(int foodItemID) {
		this.foodItemID = foodItemID;
	}

	public int getFoodOrderID() {
		return foodOrderID;
	}

	public void setFoodOrderID(int foodOrderID) {
		this.foodOrderID = foodOrderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FoodOrderItems [foodItemID=" + foodItemID + ", foodOrderID=" + foodOrderID + ", quantity=" + quantity
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(foodItemID, foodOrderID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodOrderItems other = (FoodOrderItems) obj;
		return foodItemID == other.foodItemID && foodOrderID == other.foodOrderID;
	}
	
	

}
