package utils;

public enum ServiceType {
    GYM_ACTIVITIES("Gym Activities"),
    SPA("Spa"),
    MANI_PEDI("Manicure and Pedicure"),
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

