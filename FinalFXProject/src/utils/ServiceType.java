package utils;

public enum ServiceType {
	GYMACTIVITIES("GymActivities"),
    SPA("Spa"),
    MANIPEDI("ManiPedi"),
    TRIPS("Trips"),
    BABYSITTING("Babysitting"),
    ENTERTAINMENT("Entertainment"),
    DINING("Dining"),
    POOL("Pool");

    private final String displayName;

    ServiceType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

