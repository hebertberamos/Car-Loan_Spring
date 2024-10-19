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

    public static void main(String[] args) {
        RentalCalculator calculator = new RentalCalculator();

        double value = calculator.calculateRentalValue(Instant.now(), Instant.now().plus(2, ChronoUnit.DAYS), 100.00, 300.00);

        System.out.println("Valor: " + value);
    }

}