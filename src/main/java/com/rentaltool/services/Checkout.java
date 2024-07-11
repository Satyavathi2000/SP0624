package com.rentaltool.services;

import com.rentaltool.model.Tool;
import com.rentaltool.model.RentalAgreement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for checking out tools and generating rental agreements.
 */
public class Checkout {

    private static Map<String, Tool> tools = new HashMap<>();

    static {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    /**
     * Checks out a tool and generates a rental agreement.
     *
     * @param toolCode       the tool code
     * @param rentalDays     the number of rental days
     * @param discountPercent the discount percent
     * @param checkoutDate   the checkout date
     * @return the rental agreement
     */
    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
        }

        Tool tool = tools.get(toolCode);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(tool, checkoutDate, dueDate);
        double preDiscountCharge = round(chargeDays * tool.getDailyCharge(), 2);
        double discountAmount = round((discountPercent / 100.0) * preDiscountCharge, 2);
        double finalCharge = round(preDiscountCharge - discountAmount, 2);

        return new RentalAgreement(toolCode, tool.getType(), tool.getBrand(), rentalDays, checkoutDate, dueDate,
                tool.getDailyCharge(), chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    /**
     * Calculates the number of chargeable days for a rental period.
     *
     * @param tool         the tool being rented
     * @param checkoutDate the checkout date
     * @param dueDate      the due date
     * @return the number of chargeable days
     */
    private static int calculateChargeDays(Tool tool, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeDays = 0;
        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            boolean isHoliday = isHoliday(date);
            boolean isWeekend = date.getDayOfWeek().getValue() >= 6;
            boolean isChargeableDay = isChargeableDay(tool, date);

            System.out.println("Date: " + date + ", isHoliday: " + isHoliday + ", isWeekend: " + isWeekend + ", isChargeableDay: " + isChargeableDay);

            if (isChargeableDay) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    /**
     * Determines if a given day is chargeable based on the tool and date.
     *
     * @param tool the tool being rented
     * @param date the date to check
     * @return true if the day is chargeable, false otherwise
     */
    private static boolean isChargeableDay(Tool tool, LocalDate date) {
        boolean isHoliday = isHoliday(date);
        boolean isWeekend = date.getDayOfWeek().getValue() >= 6;

        if (isHoliday) {
            return tool.isHolidayCharge();
        } else if (isWeekend) {
            return tool.isWeekendCharge();
        } else {
            return tool.isWeekdayCharge();
        }
    }

    /**
     * Determines if a given date is a holiday.
     *
     * @param date the date to check
     * @return true if the date is a holiday, false otherwise
     */
    private static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) { // Saturday
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek().getValue() == 7) { // Sunday
            independenceDay = independenceDay.plusDays(1);
        }
        LocalDate laborDay = LocalDate.of(year, 9, 1).with(java.time.temporal.TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY));
        return date.equals(independenceDay) || date.equals(laborDay);
    }

    /**
     * Rounds a value to the specified number of decimal places.
     *
     * @param value  the value to round
     * @param places the number of decimal places
     * @return the rounded value
     */
    private static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
