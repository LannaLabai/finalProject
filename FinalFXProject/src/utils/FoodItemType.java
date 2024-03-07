package utils;

public enum FoodItemType {
    BREAKFAST("Breakfast"),
    MEAL("Meal"),
    DESSERT("Dessert"),
    SOFT_DRINK("Soft Drink"),
    ALCOHOLIC_BEVERAGE("Alcoholic Beverage"),
    SNACK("Snack"),
    HOT_BEVERAGE("Hot Beverage"),
    COLD_BEVERAGE("Cold Beverage"),
    APPETIZER("Appetizer");

    private final String displayName;

    FoodItemType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
