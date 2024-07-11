package com.rentaltool.services;

import com.rentaltool.model.RentalAgreement;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Checkout service.
 */
public class CheckoutTest {

    /**
     * Test case 1: Invalid discount percent (greater than 100%).
     */
    @Test
    public void testCase1() {
        try {
            RentalAgreement agreement = Checkout.checkout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        } catch (IllegalArgumentException e) {
            assertEquals("Discount percent must be between 0 and 100.", e.getMessage());
        }
    }

    /**
     * Test case 2: Ladder with 10% discount.
     */
    @Test
    public void testCase2() {
        RentalAgreement agreement = Checkout.checkout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
        System.out.println("-------------------Ladder with 10% discount-------------------");
        agreement.print();
        assertEquals("LADW", agreement.getToolCode());
        assertEquals("Ladder", agreement.getToolType());
        assertEquals("Werner", agreement.getToolBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
        assertEquals(2, agreement.getChargeDays());  // Sat 07/04 and Sun 07/05 are chargeable
        assertEquals(3.98, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.40, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.58, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test case 3: Chainsaw with 25% discount.
     */
    @Test
    public void testCase3() {
        RentalAgreement agreement = Checkout.checkout("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
        System.out.println("-------------------Chainsaw with 25% discount-------------------");
        agreement.print();
        assertEquals("CHNS", agreement.getToolCode());
        assertEquals("Chainsaw", agreement.getToolType());
        assertEquals("Stihl", agreement.getToolBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate());
        assertEquals(3, agreement.getChargeDays());  // Only weekday chargeable are 07/02, 07/06 and 07/07
        assertEquals(4.47, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(1.12, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.35, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test case 4: Jackhammer with no discount.
     */
    @Test
    public void testCase4() {
        RentalAgreement agreement = Checkout.checkout("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        System.out.println("-------------------Jackhammer with no discount-------------------");
        agreement.print();
        assertEquals("JAKD", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("DeWalt", agreement.getToolBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate());
        assertEquals(3, agreement.getChargeDays());  // 09/07, 09/08, 09/09
        assertEquals(8.97, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(8.97, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test case 5: Jackhammer with no discount.
     */
    @Test
    public void testCase5() {
        RentalAgreement agreement = Checkout.checkout("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
        System.out.println("-------------------Jackhammer with no discount-------------------");
        agreement.print();
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate());
        assertEquals(5, agreement.getChargeDays());  // Chargeable days: 07/06, 07/07, 07/08, 07/09, 07/10
        assertEquals(14.95, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(14.95, agreement.getFinalCharge(), 0.01);
    }

    /**
     * Test case 6: Jackhammer with 50% discount.
     */
    @Test
    public void testCase6() {
        RentalAgreement agreement = Checkout.checkout("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
        System.out.println("-------------------Jackhammer with 50% discount-------------------");
        agreement.print();
        assertEquals("JAKR", agreement.getToolCode());
        assertEquals("Jackhammer", agreement.getToolType());
        assertEquals("Ridgid", agreement.getToolBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate());
        assertEquals(1, agreement.getChargeDays());  // Chargeable day: 07/06
        assertEquals(2.99, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(1.50, agreement.getDiscountAmount(), 0.01);
        assertEquals(1.49, agreement.getFinalCharge(), 0.01);
    }
}
