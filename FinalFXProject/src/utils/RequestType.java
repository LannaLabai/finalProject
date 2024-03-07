package utils;

public enum RequestType {
    COLLECT_LAUNDRY("Collect Laundry"),
    CLEAN_ROOM("Clean Room"),
    BRING_NEW_TOWELS("Bring New Towels"),
    REFILL_SOAP("Refill Soap"),
    BRING_WATER("Bring Water"),
    BRING_TOILET_PAPER("Bring Toilet Paper"),
    CHANGE_SHEETS("Change Sheets"),
    VACUUM_ROOM("Vacuum Room"),
    TAKE_OUT_TRASH("Take Out Trash"),
    IRONING("Ironing"),
    DRY_CLEANING("Dry Cleaning"),
    DO_NOT_DISTURB("Do Not Disturb"),
    OTHER("Other");

    private final String displayName;

    RequestType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

