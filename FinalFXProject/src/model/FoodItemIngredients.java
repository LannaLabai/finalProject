package model;

import utils.FoodItemType;
import utils.KitchenType;

public class FoodItemIngredients extends FoodItem {
	
	private String foodItemIngredients;
	
	public FoodItemIngredients(int foodItemID, String foodItemName, double foodItemCost, FoodItemType foodItemType,
			KitchenType kitchentType, String foodItemIngredients) {
		super(foodItemID, foodItemName, foodItemCost, foodItemType, kitchentType);
		this.foodItemIngredients = foodItemIngredients;
	}

	public String getFoodItemIngredients() {
		return foodItemIngredients;
	}

	public void setFoodItemIngredients(String foodItemIngredients) {
		this.foodItemIngredients = foodItemIngredients;
	}

	@Override
	public String toString() {
		return super.toString()+", FoodItemIngredients [foodItemIngredients=" + foodItemIngredients + "]";
	}
	
	
	
	

}
