package utils;

public enum KitchenType {
    BAR("Bar"),
    RESTAURANT("Restaurant"),
    HOTEL_KITCHEN("Hotel Kitchen"),
    SNACKS_KIOSK("Snacks Kiosk");

    private final String displayName;

    KitchenType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
