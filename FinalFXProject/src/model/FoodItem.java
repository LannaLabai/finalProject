package model;

import java.io.*;
import java.util.*;
import utils.*;

public class FoodItem {
	
	private int foodItemID;
	private String foodItemName;
	private double foodItemCost;
	private FoodItemType foodItemType;
	private KitchenType kitchentType;
	
	
	public FoodItem(int foodItemID, String foodItemName, double foodItemCost, FoodItemType foodItemType, KitchenType kitchentType) {
		super();
		this.foodItemID = foodItemID;
		this.foodItemName = foodItemName;
		this.foodItemCost = foodItemCost;
		this.foodItemType = foodItemType;
		this.kitchentType = kitchentType;
	}

	public int getFoodItemID() {
		return foodItemID;
	}

	public void setFoodItemID(int foodItemID) {
		this.foodItemID = foodItemID;
	}

	public double getFoodItemCost() {
		return foodItemCost;
	}

	public void setFoodItemCost(double foodItemCost) {
		this.foodItemCost = foodItemCost;
	}

	public KitchenType getKitchentType() {
		return kitchentType;
	}

	public void setKitchentType(KitchenType kitchentType) {
		this.kitchentType = kitchentType;
	}

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}
	
	

	public FoodItemType getFoodItemType() {
		return foodItemType;
	}

	public void setFoodItemType(FoodItemType foodItemType) {
		this.foodItemType = foodItemType;
	}


	@Override
	public String toString() {
		return "FoodItem [foodItemID=" + foodItemID + ", foodItemName=" + foodItemName + ", foodItemCost="
				+ foodItemCost + ", foodItemType=" + foodItemType + ", kitchentType=" + kitchentType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(foodItemID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodItem other = (FoodItem) obj;
		return foodItemID == other.foodItemID;
	}

	
	

}
