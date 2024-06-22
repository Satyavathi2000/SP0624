package com.rentaltool.model;



import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, Date checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;
        this.dailyRentalCharge = tool.getDailyCharge();
        calculateAgreementDetails();
    }


    private void calculateAgreementDetails() {
        dueDate = new Date(checkoutDate.getTime() + TimeUnit.DAYS.toMillis(rentalDays));
        chargeDays = calculateChargeDays();
        preDiscountCharge = chargeDays * dailyRentalCharge;
        discountAmount = preDiscountCharge * discountPercent / 100.0;

        finalCharge = preDiscountCharge - discountAmount;
    }

    private int calculateChargeDays() {
        int count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        for (int i = 0; i < rentalDays; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
            boolean isHoliday = isHoliday(calendar);

            if ((isWeekend && tool.isChargeOnWeekends()) || (!isWeekend  && !isHoliday && tool.isChargeOnWeekdays()) || (isHoliday && tool.isChargeOnHolidays())) {
                count++;
            }
        }
        return count;
    }

    private boolean isHoliday(Calendar calendar) {
        //independence day
        //
        if (calendar.get(Calendar.MONTH) == Calendar.JULY && (calendar.get(Calendar.DAY_OF_MONTH) == 4 ||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && calendar.get(Calendar.DAY_OF_MONTH) == 3) ||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && calendar.get(Calendar.DAY_OF_MONTH) == 5))) {
            return true;
        }
        //labour day
        if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY &&
            calendar.get(Calendar.DAY_OF_MONTH) <= 7) {
            return true;
        }
        return false;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }
}
