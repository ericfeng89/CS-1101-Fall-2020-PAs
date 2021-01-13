// **********************************************************************************************
// Program Name: PianoMan.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/15/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program accepts as input an initial frequency for a key and outputs the next 3 note
//       frequencies.
// **********************************************************************************************

import java.util.Scanner;

public class PianoMan {

    // method truncates number to two decimal places
    public static double truncate(double d) {
        double truncatedNum;

        // multiply by 100 then round number down using Math.floor function
        // divide by 100 to get 2 decimal places
        truncatedNum = Math.floor(d * 100) / 100;

        return truncatedNum;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        double initialFreq;
        double secondFreq;
        double thirdFreq;
        double fourthFreq;

        // ratio between frequencies is a constant "r" equal to 2^(1/12)
        final double FREQUENCY_RATIO = Math.pow(2.0, 1.0/12.0);

        initialFreq = scnr.nextDouble();

        // calculate the next three frequencies by multiplying the previous by the ratio
        secondFreq = initialFreq * FREQUENCY_RATIO;
        thirdFreq = secondFreq * FREQUENCY_RATIO;
        fourthFreq = thirdFreq * FREQUENCY_RATIO;

        // prompt user
        System.out.println("Enter the initial frequency:");

        // display frequencies, truncated to 2 decimal places
        System.out.println("The three frequencies that follow " + initialFreq + " are:");
        System.out.print(truncate(secondFreq) + ", " + truncate(thirdFreq));
        System.out.println(", " + truncate(fourthFreq));
    }
}