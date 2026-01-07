package com;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Day2 {
    @Test
    public void MobileLogin() {
        System.out.println("Mobile Login");
    }

    @Test
    public void MobileLogoff() {
        System.out.println("Mobile Logoff");
    }

    @Test
    public void MobileRestart() {
        System.out.println("Mobile Restart");
    }

    @Test
    public void PaymentSuccess() {
        System.out.println("Payment Success");
    }

    @Test
    public void PaymentFailed() {
        System.out.println("Payment Failed");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = {"PaymentFailed"})
    public void PaymentInitiated() {
        System.out.println("Payment Initiated");
    }
}
