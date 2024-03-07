package utils;

public enum Ringtone {
    ALERT("Alert"),
    BULLETIN("Bulletin"),
    BY_THE_SEASIDE("By the Seaside"),
    CHIMES("Chimes"),
    CIRCUIT("Circuit"),
    CONSTELLATION("Constellation"),
    COSMIC("Cosmic"),
    CRYSTALS("Crystals"),
    HILLSIDE("Hillside"),
    RADAR("Radar");

    private final String displayName;

    Ringtone(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

