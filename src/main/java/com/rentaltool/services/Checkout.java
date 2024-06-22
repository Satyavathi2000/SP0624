package com.rentaltool.services;

import com.rentaltool.model.RentalAgreement;
import com.rentaltool.model.Tool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.ParseException;

public class Checkout {
    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, String checkoutDateStr) throws ParseException {

        if (rentalDays < 1) throw new IllegalArgumentException("Rental days count");
        if (discountPercent < 0 || discountPercent > 100) throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool("CHNS", "Chainsaw", "Stihl", 1.49, false, true, true));
        tools.add(new Tool("LADW", "Ladder", "Werner", 1.99, true, false, true));
        tools.add(new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, false, false, true));
        tools.add(new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, false, false, true));

        Tool selectedTool = null;
        for (Tool tool : tools) {
            if (tool.getCode().equals(toolCode)) {
                selectedTool = tool;
                break;
            }
        }
        if (selectedTool == null) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date checkoutDate = dateFormat.parse(checkoutDateStr);

        return new RentalAgreement(selectedTool, rentalDays, checkoutDate, discountPercent);
    }
}
