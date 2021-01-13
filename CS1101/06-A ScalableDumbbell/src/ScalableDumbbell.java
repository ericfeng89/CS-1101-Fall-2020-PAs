// **********************************************************************************************
// Program Name: ScalableDumbbell.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/7/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will draw a scalable ASCII graphic of a dumbbell.
// **********************************************************************************************

import java.util.Scanner;

public class ScalableDumbbell {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get integer for the scale
        int scaleInt = getScaleInt(scnr);

        // print ball on top
        printBall(scaleInt);

        // print connecting bar
        printBar(scaleInt);

        // print ball on bottom
        printBall(scaleInt);
    }

    // get integer (2-6) for scale, reprompting if necessary
    public static int getScaleInt(Scanner scnr) {
        System.out.println("Enter an integer 2-6 for the scale:");
        int scaleInt = scnr.nextInt();

        // if integer outside range 2-6, reprompt
        while (scaleInt < 2 || scaleInt > 6) {
            System.out.println("Scale must be a value between 2-6 inclusive. Enter the scale:");
            scaleInt = scnr.nextInt();
        }
        return scaleInt;
    }

    // aggregate three methods to print ball of dumbbell
    public static void printBall(int scaleInt) {

        // print top third of ball
        printTopThird(scaleInt);

        // print middle of ball (equal signs)
        printDumbbellEquator(scaleInt);

        // print bottom third of ball
        printBottomThird(scaleInt);
    }

    // print top third of ball given scale
    public static void printTopThird(int scaleInt) {
        int i;
        int j;
        int k;
        int counter = 0;

        // print newline after each line, scaleInt times
        for (i = scaleInt; i > 0; i--) {

            // print spaces in groups of 2
            // located before symbols on each line
            // amount of spaces decreases by 2 each line
            for (j = i - 1; j > 0; j--) {
                System.out.print("  ");
            }

            // print left side of ball
            System.out.print("/=");

            // print slashes in groups of 2 (backward + forward)
            // 2 additional groups of slashes (4 total) for each line
            for (k = 0; k <= counter + scaleInt; k++) {
                System.out.print("\\/");
            }

            // print right side of ball, newline
            System.out.print("=\\");
            System.out.println("");

            // since 2 extra slash groups per line, have counter that increments
            // by 2 to use in for loop upper limit
            counter += 2;
        }
    }

    // print equal signs at middle of ball
    public static void printDumbbellEquator(int scaleInt) {
        int i;
        int j;

        // print newline after each of the two rows
        for (i = 0; i < 2; i++) {

            // print certain amount of equal signs, depending on scaleInt
            for (j = 0; j < 3 + scaleInt * 6; j++) {
                System.out.print("=");
            }
            System.out.println("");
        }
    }

    public static void printBottomThird(int scaleInt) {
        int i;
        int j;
        int k;
        int counter = -2;

        // print newline after each line, scaleInt times
        for (i = 0; i < scaleInt; i++) {

            // print spaces in groups of 2
            // amount of spaces increases by 2 each line
            for (j = i - 1; j >= 0; j--) {
                System.out.print("  ");
            }

            // print left side of ball
            System.out.print("\\=");

            // print slashes in groups of 2 (forward + backward)
            // 2 fewer groups of slashes (4 total) for each line
            // top row always has 3 * scaleInt - 2 slashes
            for (k = counter + 3 * scaleInt; k >= 0; k--) {
                System.out.print("/\\");
            }

            // print right side of ball, newline
            System.out.print("=/");
            System.out.println("");

            // since 2 fewer slash groups per line, have counter that decrements
            // by 2 to use in for loop starting value
            counter = counter - 2;
        }
    }

    // print bar connecting two balls together
    public static void printBar(int scaleInt) {
        int i;
        int j;
        int k;

        // print scaleInt * scaleInt amount of rows
        for (i = 0; i < scaleInt * scaleInt; i++) {

            // print (2 * scaleInt) + 1 amount of spaces before bar
            for (j = 0; j < (2 * scaleInt) + 1; j++) {
                System.out.print(" ");
            }

            // print outside bar
            System.out.print("|");

            // print (scaleInt - 2) amount of X's
            for (k = 0; k < scaleInt - 2; k++) {
                System.out.print("X");
            }

            // print two middle bars
            System.out.print("||");

            // again, print (scaleInt - 2) amount of X's
            for (k = 0; k < scaleInt - 2; k++) {
                System.out.print("X");
            }

            // print outside bar, newline
            System.out.print("|");
            System.out.println("");
        }
    }
}

