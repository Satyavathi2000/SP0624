package com.rentaltool.services;

import com.rentaltool.model.RentalAgreement;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CheckoutTest {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    @Test(expected = IllegalArgumentException.class)
    public void test1() throws ParseException {
        Checkout.checkout("JAKR", 5, 101, "09/03/15");
    }

    @Test
    public void test2() throws ParseException {
        RentalAgreement agreement = Checkout.checkout("LADW", 3, 10, "07/02/20");
        assertNotNull(agreement);

        assertEquals("LADW", agreement.getTool().getCode());
        assertEquals("Ladder", agreement.getTool().getType());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(dateFormat.parse("07/02/20"), agreement.getCheckoutDate());
        assertEquals(dateFormat.parse("07/05/20"), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyRentalCharge(), 0.01);
        assertEquals(2, agreement.getChargeDays());
        assertEquals(3.98, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.40, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.58, agreement.getFinalCharge(), 0.01);
    }

    @Test
    public void test3() throws ParseException {
        RentalAgreement agreement = Checkout.checkout("CHNS", 5, 25, "07/02/15");
        assertNotNull(agreement);
        assertEquals("CHNS", agreement.getTool().getCode());
        assertEquals("Chainsaw", agreement.getTool().getType());
        assertEquals("Stihl", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(dateFormat.parse("07/02/15"), agreement.getCheckoutDate());
        assertEquals(dateFormat.parse("07/07/15"), agreement.getDueDate());
        assertEquals(1.49, agreement.getDailyRentalCharge(), 0.01);
        assertEquals(4, agreement.getChargeDays());
        assertEquals(5.96, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(25, agreement.getDiscountPercent());
        assertEquals(1.49, agreement.getDiscountAmount(), 0.01);
        assertEquals(4.47, agreement.getFinalCharge(), 0.01);

    }


    @Test
    public void test4() throws ParseException {
        RentalAgreement agreement = Checkout.checkout("JAKD", 6, 0, "09/03/15");
        assertNotNull(agreement);
        assertEquals("JAKD", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType());
        assertEquals("DeWalt", agreement.getTool().getBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals(dateFormat.parse("09/03/15"), agreement.getCheckoutDate());
        assertEquals(dateFormat.parse("09/09/15"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.01);
        assertEquals(3, agreement.getChargeDays());
        assertEquals(8.97, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(8.97, agreement.getFinalCharge(), 0.01);

    }

    @Test
    public void test5() throws ParseException {
        RentalAgreement agreement = Checkout.checkout("JAKR", 9, 0, "07/02/15");
        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals(dateFormat.parse("07/02/15"), agreement.getCheckoutDate());
        assertEquals(dateFormat.parse("07/11/15"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.01);
        assertEquals(5, agreement.getChargeDays());
        assertEquals(14.95, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(0, agreement.getDiscountPercent());
        assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
        assertEquals(14.95, agreement.getFinalCharge(), 0.01);

    }

    @Test
    public void test6() throws ParseException {
        RentalAgreement agreement = Checkout.checkout("JAKR", 4, 50, "07/02/20");
        assertNotNull(agreement);
        assertEquals("JAKR", agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getType());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(4, agreement.getRentalDays());
        assertEquals(dateFormat.parse("07/02/20"), agreement.getCheckoutDate());
        assertEquals(dateFormat.parse("07/06/20"), agreement.getDueDate());
        assertEquals(2.99, agreement.getDailyRentalCharge(), 0.01);
        assertEquals(1, agreement.getChargeDays());
        assertEquals(2.99, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(50, agreement.getDiscountPercent());
        assertEquals(1.495, agreement.getDiscountAmount(), 0.01);
        assertEquals(1.495, agreement.getFinalCharge(), 0.01);

    }
}
