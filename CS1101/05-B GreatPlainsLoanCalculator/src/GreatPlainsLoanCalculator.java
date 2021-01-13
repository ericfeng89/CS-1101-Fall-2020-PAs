// **********************************************************************************************
// Program Name: GreatPlainsLoanCalculator.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/1/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program simulates the taking out a $5000 loan, paid off via regular monthly
//       installment payments of an amount that is $250-$850 inclusive. Interest, balance, and
//       and other information will be displayed in a table for each month. At the end, the
//       final payment amount (a payment in the amount of the loan payoff) will be determined.
// **********************************************************************************************

import java.util.*;

public class GreatPlainsLoanCalculator {

    // declare constants
    public static final int TOTAL_MONTHS = 24;
    public static final int MONTHS_IN_YEAR = 12;
    public static final double INTEREST_RATE_PERCENT = 200.0;
    public static final int TOTAL_LOAN = 5000;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get user's monthly payment amount
        double monthlyPayment = getMonthlyPayment(scnr);

        // if monthly payment is not between 250-850, display message and end program
        if (monthlyPayment < 250 || monthlyPayment > 850) {
            System.out.println("Monthly payment must be $250-$850. Program cannot continue.");

            // end program
            System.exit(0);
        }

        // print entire table
        printMortgageSchedule(monthlyPayment);
    }

    // prompt user to enter monthly payment, read it in, return value
    public static double getMonthlyPayment(Scanner scnr) {
        System.out.println("Enter the amount you will pay every month on the line below:");
        double monthlyPayment = scnr.nextDouble();
        return monthlyPayment;
    }

    // print entire table, including header, calculations, and final payment
    public static void printMortgageSchedule(double paymentAmount) {
        int month;
        double interest = 0.0;
        double balance = (double) TOTAL_LOAN;
        double remaining = balance;
        double totalInterest = 0.0;
        double totalPaid = 0.0;

        // print table header
        printReportHeading();

        // finish table header with total loan amount
        System.out.println(TOTAL_LOAN + "\n");
        System.out.println("Month\t\tInt.\t\tBalance\t\tPayment\t\tRemaining\n");

        // calculate month, interest, balance, payment, and remaining for months 1 to 23
        for (int i = 0; i < TOTAL_MONTHS - 1; ++i) {

            // add 1 for month since loop starts at 0
            month = i + 1;

            // calculate interest using formula
            // initial value of "remaining" is the price of the loan ($5000)
            interest = (INTEREST_RATE_PERCENT / 100) * remaining / MONTHS_IN_YEAR;

            // add interest to remaining to calculate balance
            balance = remaining + interest;

            // update remaining value, accounting for this month's payment
            remaining = balance - paymentAmount;

            // print formatted values, rounding to 2 decimal places for money
            System.out.print("  " + month);
            System.out.printf("\t   %,9.2f", interest);
            System.out.printf("\t\t%,9.2f", balance);
            System.out.printf("\t%,9.2f", paymentAmount);
            System.out.printf("\t%,9.2f", remaining);
            System.out.println("");

            // keep track of cumulative interest and payment amount
            // to be used as paramaters for printFinalPayment()
            totalInterest += interest;
            totalPaid += paymentAmount;
        }
        System.out.println("  " + TOTAL_MONTHS + "\t\tSee final payment info below\n");

        // calculate/print final payment, total paid over 24 months, and total interest paid
        printFinalPayment(remaining, interest, totalInterest, totalPaid);
    }

    // print table header
    public static void printReportHeading() {
        System.out.println("\nMortgage Payment Schedule");
        System.out.println("--------------------------");
        System.out.print("Amount borrowed: $" );
    }

    // calculate/print final payment, total paid over 24 months, and total interest paid
    public static void printFinalPayment(double remainingDue, double interestForTheMonth,
                                         double totalInterest, double totalPaid) {
        double lastMonthInterest;
        double finalPayment;
        double totalPaidOver24;
        double totalInterestOver24;

        // calculate interest for 24th month
        lastMonthInterest = (INTEREST_RATE_PERCENT / 100) * remainingDue / MONTHS_IN_YEAR;

        // add 24th month's interest to remaining balance due
        finalPayment = lastMonthInterest + remainingDue;

        // add final payment to sum of monthly payments
        totalPaidOver24 = finalPayment + totalPaid;

        // calculate cumulative interest paid, including 24th month
        totalInterestOver24 = lastMonthInterest + totalInterest;

        // print values, money formatted to 2 decimal places
        System.out.print("Final payment: $");
        System.out.printf("%,8.2f", finalPayment);
        System.out.print("\nTotal paid over 24 months: $");
        System.out.printf("%,9.2f", totalPaidOver24);
        System.out.print("\nTotal interest paid: $");
        System.out.printf("%,9.2f", totalInterestOver24);
        System.out.println("\n");
        System.out.println("Thank you for your business...at Great Plains Loans, we treat you like family!");
    }
}

