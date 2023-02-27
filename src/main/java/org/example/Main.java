package org.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final byte MONTHS = 12;
    public static final byte PERCENT = 100;
    public static void main(String[] args) {

        int principal = (int) readInput("Principal:  ", 1000,1_000_000);
        float annualInterest = (float) readInput("please enter the interest rate ", 1,30);
        byte mortgageYears = (byte) readInput("please enter the period of morgage(years) ",1,40);

        printMortgage(principal,annualInterest,mortgageYears);
        printPaymentSchedule(principal,annualInterest,mortgageYears);
        }
    public static void printMortgage(int principal, float annualInterest, byte mortgageYears){
        double monthlyMortgage =  calculateMortgage(principal, annualInterest, mortgageYears);
        System.out.println(monthlyMortgage);
        System.out.println("MORTGAGE monthly Payment");
        System.out.println("------------");
        System.out.println(String.format("your monthly payment for the mortgage for the sum of %d with annual interest rate of %f for %d years is %f ", principal,annualInterest, mortgageYears,monthlyMortgage));
    }
    public static void printPaymentSchedule(int principal, float annualInterest, byte mortgageYears){
        System.out.println();
        System.out.println("MORTGAGE monthly Payment");
        System.out.println("------------");
        for( short month=1; month <= mortgageYears*MONTHS; month++){
            double balance = calculateBalance(principal,annualInterest,mortgageYears,month);
            System.out.println(String.format("remaining balance is %s", NumberFormat.getCurrencyInstance(Locale.GERMANY).format(balance)));
        }
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte mortgageYears,
            short paymentsMade
    ){
        float totalPayments =mortgageYears*MONTHS;
        float monthlyInterest = annualInterest /(PERCENT * MONTHS);

        double balance = principal *
                (Math.pow(1+monthlyInterest, totalPayments)
                - Math.pow(1+monthlyInterest, paymentsMade))
                / (Math.pow(1+monthlyInterest, paymentsMade) -1);
        return balance;
    };
    public static double readInput(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double response;
        while(true) {
            System.out.print(prompt);
            response = scanner.nextFloat();
            if (response >= min  && response < max)
                break;
            System.out.println("please enter a value between "+ min + " and "+ max);
        }
        return response;
    }
    public static double calculateMortgage( int principal, float annualInterest, byte mortgageYears){
        float totalPayments =mortgageYears*MONTHS;
        float monthlyInterest = annualInterest /(PERCENT * MONTHS);
        double monthlyMortgage= principal *
                (monthlyInterest * ((Math.pow(monthlyInterest + 1, totalPayments))
                        / (Math.pow(1 + monthlyInterest, totalPayments) - 1)));
        return monthlyMortgage;
    }
    }
