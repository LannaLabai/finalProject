package utils;

public enum PlaceType {
    ATTRACTION("Attraction"),
    CLUB("Club"),
    RESTAURANT("Restaurant"),
    TRIP("Trip"),
    WATER_SPORT("Water Sport"),
    HIKING("Hiking"),
    SAILING("Sailing"),
    CAFE("Cafe"),
    BEACH("Beach"),
    MUSEUM("Museum"),
    KIDS("Kids"),
    NATURE("Nature"),
    PARK("Park"),
    ZOO("Zoo");

    private final String displayName;

    PlaceType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
