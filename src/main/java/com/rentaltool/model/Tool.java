package com.rentaltool.model;

/**
 * Represents a Tool available for rent.
 */
public class Tool {
    private String code;
    private String type;
    private String brand;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    /**
     * Constructs a new Tool.
     *
     * @param code          the tool code
     * @param type          the tool type
     * @param brand         the tool brand
     * @param dailyCharge   the daily rental charge
     * @param weekdayCharge whether the tool has a weekday charge
     * @param weekendCharge whether the tool has a weekend charge
     * @param holidayCharge whether the tool has a holiday charge
     */
    public Tool(String code, String type, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
