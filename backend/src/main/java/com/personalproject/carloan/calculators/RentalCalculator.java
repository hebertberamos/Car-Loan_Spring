package com.personalproject.carloan.calculators;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class RentalCalculator {

    public double calculateRentalValue(Instant checkin, Instant checkout, double pricePerHour, double pricePerDay){
        double rentalAmount = 0.0;

        double hours = calculateHoursQuantity(checkin, checkout);
        double days = calculateDaysQuantity(checkin, checkout);

//        check if you are over 12
        if(hours < 12){
        //        If not: calculate the hourly rental value.
            rentalAmount = pricePerHour * hours;
            return rentalAmount;
        }
        //        if so: calculate the rental value per day
        return days * pricePerDay;
    }

    public double calculateRefoundTax(Instant checkoutInstant, Instant refoundInstant, double rentalAmount){
        double taxValue = 0.0;

        if(checkoutInstant.isBefore(refoundInstant)){
            Duration duration = Duration.between(checkoutInstant, refoundInstant);
            long durationInt = duration.toMinutes();

            if(duration.toMinutes() > 15 && duration.toMinutes() <= 30){
                taxValue = calculateTheAmountTax(rentalAmount, 1);
            }
            else if(duration.toMinutes() > 30 && duration.toMinutes() <= 50){
                taxValue = calculateTheAmountTax(rentalAmount, 2);
            }
            else if(duration.toMinutes() > 50){
                taxValue = calculateTheAmountTax(rentalAmount, 3);
            }
        }

        return taxValue;
    }

    private int calculateHoursQuantity(Instant checkin, Instant checkout){
        int hours = 0;
        Duration duration = Duration.between(checkin, checkout);
        hours = (int) duration.toHours();

        return hours;
    }

    private double calculateDaysQuantity(Instant checkin, Instant checkout) {
        double days = 0.0;
        Duration quantityHours = Duration.between(checkin, checkout);

        days = quantityHours.toDays();

        return days;
    }

    private static double calculateTheAmountTax(double amount, int delayLevel){

        return switch (delayLevel) {
            case 1 -> amount * 0.20;
            case 2 -> amount * 0.35;
            case 3 -> amount * 0.60;
            default -> 0.0;
        };
    }


    public static void main(String[] args) {

    }
}