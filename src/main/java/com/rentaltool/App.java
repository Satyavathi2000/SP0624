package com.rentaltool;

import com.rentaltool.model.RentalAgreement;
import com.rentaltool.services.Checkout;

import java.time.LocalDate;

/**
 * Main application for the tool rental service.
 */
public class App {
    public static void main(String[] args) {
        // Example usage
        try {
            String toolCode = "JAKR";
            int rentalDays = 5;
            int discountPercent = 10;
            LocalDate checkoutDate = LocalDate.of(2015, 9, 3);

            RentalAgreement agreement = Checkout.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
            agreement.print();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
