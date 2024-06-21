package com.example.domain;

public enum TimeRange {
    RANGE_8_10("8:00~10:00"),
    RANGE_10_12("10:00~12:00"),
    RANGE_12_14("12:00~14:00"),
    RANGE_14_16("14:00~16:00"),
    RANGE_16_18("16:00~18:00");

    private final String displayName;

    TimeRange(String displayName) {
        this.displayName = displayName;
    }

    public static TimeRange fromDisplayName(String displayName){
        for(TimeRange range : TimeRange.values()){
            if(range.displayName.equals(displayName)){
                return range;
            }
        }
        throw new IllegalArgumentException("No enum constant with display name "+displayName);
    }

    public String getDisplayName() {
        return displayName;
    }
}