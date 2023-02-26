package org.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final byte MONTHS = 12;
    public static final byte PERCENT = 100;
    public static void main(String[] args) {

        int principal = (int)readNumber("Principal ", 1000,1_000_000);
        float annualInterest = (float) readNumber("please enter the interest rate ", 1,30);
        byte mortgageYears = (byte)readNumber("please enter the period of morgage(years) ",1,40);

        double monthlyMortgage =  calculateMortgage(principal, annualInterest, mortgageYears);
        System.out.println(monthlyMortgage);
        System.out.println("MORTGAGE momthly Payment");
        System.out.println("------------");
        for( short month=1; month <= mortgageYears*MONTHS; month++){
            double balance = calculateBalance(principal,annualInterest,mortgageYears,month);
            System.out.println("remaining balance is "+ NumberFormat.getCurrencyInstance(Locale.GERMANY).format(balance));
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
    public static double readNumber(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double response;
        while(true) {
            System.out.print(prompt);
            response = scanner.nextFloat();
            if (response >= min  && response < max)
                break;
            System.out.println("enter a value between"+ min+ "and"+ max);
        }
        return response;
    }
    public static double calculateMortgage( int principal, float annualInterest, byte mortgageYears){
        float totalPayments =mortgageYears*MONTHS;
        float monthlyInterest = annualInterest /(PERCENT * MONTHS);
        double monthlyMortgage= principal *
                (monthlyInterest * ((Math.pow(monthlyInterest + 1, totalPayments))
                        / (Math.pow(1 + monthlyInterest, totalPayments) - 1)));
        System.out.println("your monthly payment for the morgage for the sum of " + (int)principal +
                "$ with annual interest rate of "+(int)annualInterest+"% for "+(int)mortgageYears+" years is : " + NumberFormat.getCurrencyInstance(Locale.GERMANY).format(monthlyMortgage));
        return monthlyMortgage;
    }
    }
