// **********************************************************************************************
// Program Name: BirthdayStats.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/9/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program takes in the user's birthday and returns a list of statistics about what it
//       means to reach that age.
// **********************************************************************************************

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.*;

public class BirthdayStats {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // all input assumed valid
        // declare birthday info variables (user input)
        int monthBorn;
        int dateBorn;
        int yearBorn;

        // declare variables that store calculations
        int yearsAlive;
        int hoursAlive;
        int minutesAlive;
        long secondsAlive;
        int totalBlinks;
        int totalBreaths;
        int daysOnHold;

        // initialize fixed rates/conversions
        final int CURRENT_YEAR = 2020;
        final int HOURS_IN_DAY = 24;
        final int MINUTES_IN_HOUR = 60;
        final int SECONDS_IN_MINUTE = 60;
        final int BLINKS_PER_DAY = 28800;
        final int BREATHS_PER_MINUTE = 16;
        final int HOLD_HOURS_PER_YEAR = 13;

        // prompt user for birthday info
        System.out.println("Enter the month you were born (1-12): ");
        monthBorn = scnr.nextInt();
        System.out.println("Enter the day you were born (1-31): ");
        dateBorn = scnr.nextInt();
        System.out.println("Enter the year you were born (1900-2019): ");
        yearBorn = scnr.nextInt();

        //*** START: The following code segment was provided by Prof. Dan Arena for this PA ***

        //convert birthday to LocalDate object
        LocalDate birthdate = LocalDate.of(yearBorn, monthBorn, dateBorn);

        //compute how many days alive to birthdate and add 1 day
        LocalDate targetDate = LocalDate.of(2020, 1, 21);
        int daysAlive = (int) ChronoUnit.DAYS.between(birthdate, targetDate) + 1;

        //*** END code segment provided by Prof. Dan Arena

        System.out.println("");
        System.out.println("Your Birthday Fun Facts...");
        System.out.println("");

        // calculate age using current year (2020) and birth year
        yearsAlive = CURRENT_YEAR - yearBorn;
        System.out.print("As of January 21st of this year, you will have been alive approximately ");
        System.out.println(yearsAlive + " years.");

        System.out.println("");
        System.out.println("That is... ");
        System.out.println("1. " + daysAlive + " days");

        // convert days alive to hours, minutes, and seconds
        hoursAlive = HOURS_IN_DAY * daysAlive;
        minutesAlive = MINUTES_IN_HOUR * hoursAlive;
        secondsAlive = SECONDS_IN_MINUTE * (long) minutesAlive;

        System.out.println("2. " + hoursAlive + " hours");
        System.out.println("3. " + minutesAlive + " minutes");
        System.out.println("4. " + secondsAlive + " seconds");
        System.out.println("");

        // calculate miscellaneous facts
        totalBlinks = BLINKS_PER_DAY * daysAlive;
        totalBreaths = BREATHS_PER_MINUTE * minutesAlive;
        daysOnHold = (HOLD_HOURS_PER_YEAR * yearsAlive) / HOURS_IN_DAY;

        System.out.println("Did you know...");
        System.out.println("1. You have blinked your eyes about " + totalBlinks + " times");
        System.out.println("2. You have breathed about " + totalBreaths + " times");
        System.out.print("3. You will have waited on hold on the phone for a total of about ");
        System.out.println(daysOnHold + " days");
    }
}