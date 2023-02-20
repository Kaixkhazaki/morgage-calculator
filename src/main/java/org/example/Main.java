package org.example;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        principal : desired mortgage amount
        annualInterest: annual interest rate
        mortgageYears: period(years) time of the mortgage deal
        montlyMorgage: monthly payment, variable of interest
        //create scanner object
        */
        Scanner scanner = new Scanner(System.in);
        final byte MONTHS = 12;
        final byte PERCENT = 100;
        double principal = 0.0;
        float totalMonth = 0.0F;
        float monthlyInterest =0.0F;
        float mortgageYears =0;
        double montlyMorgage = 0;
        float annualInterest =0;

        while(true) {
            System.out.print("Please enter the amount of morgage: \n");
            principal = scanner.nextDouble();
            if (principal >= 1_000  && principal < 1_000_000)
                break;
            System.out.println("enter a value between 1_000 and 1_000_000");
        }
        while (true) {
            System.out.println("please enter the period of morgage(years)");
            mortgageYears = scanner.nextFloat(); // multiply it with 12 to get the montly
            if (mortgageYears>1 && mortgageYears<=40){
                totalMonth =mortgageYears*MONTHS;
                System.out.println(totalMonth + " total months");
                break;
            }else {
                System.out.println("please enter a value between 1 and 40");}
        }
        while (true) {
            System.out.println("please enter the interest rate");
            annualInterest = scanner.nextFloat(); // divide it by 12 to get monthly interest rate
            if (annualInterest>=1 && annualInterest<=30){
                monthlyInterest = annualInterest /(PERCENT * MONTHS);
                //System.out.println("monthly interest rate is " + monthlyInterest);
                break;
            }
            else {
                System.out.println("please enter a number between 1 and 30");
            }
        }
        montlyMorgage= principal *
                    (monthlyInterest * ((Math.pow(monthlyInterest + 1, totalMonth))
                            / (Math.pow(1 + monthlyInterest, totalMonth) - 1)));
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("your monthly payment for the morgage for the sum of " + (int)principal +
                "$ with annual interest rate of "+(int)annualInterest+"% for "+(int)mortgageYears+" years is : " + df.format(montlyMorgage) + "$");
        }
    }

        /*
        learnings:
        avoid magic code(hard code), instead use constants like MONTHS_INS_YEAR, PERCENT ext
        do not use magic names for variables like m1, r ext always use meaningful and descriptive names
         */