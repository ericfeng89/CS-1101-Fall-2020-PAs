// **********************************************************************************************
// Program Name: HighwayNumbers.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/24/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       Given a highway number, this program will indicate whether it is a primary or auxiliary
//       highway. If auxiliary, indicate what primary highway it serves. Also indicate whether
//       the (primary) highway runs north/south or east/west.
// **********************************************************************************************

import java.util.Scanner;

public class HighwayNumbers {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get user input (highway number)
        int highwayNum = getHighway(scnr);

        // find primary highway that it either serves or is
        int primaryNum = highwayNum % 100;

        // check if highway input is valid
        if (isValidHighway(highwayNum)) {

            // check if highway is auxiliary
            if (isAuxiliaryHighway(highwayNum)) {

                // display auxiliary highway information message (everything besides the direction)
                System.out.print("The " + highwayNum + " is an auxiliary highway, servicing the ");
                System.out.print(primaryNum + " running ");
            }

            // if highway is not auxiliary, print primary highway information message
            else {
                System.out.print("The " + highwayNum + " is a primary highway running ");
            }

            // calculate and display highway direction
            calculateAndDisplayDirection(highwayNum);
        }

        // if highway input is not valid, notify user
        else {
            System.out.println(highwayNum + " is not a valid interstate highway number. Program cannot continue.");
        }
    }

    // prompt user for highway number, read it in, and return number
    public static int getHighway(Scanner scnr) {
        System.out.print("Enter the highway number: ");
        int highwayNumber = scnr.nextInt();
        return highwayNumber;
    }

    // check if highway is valid (between 0 and 1000, exclusive)
    public static boolean isValidHighway(int highwayNumber) {
        return (highwayNumber > 0 && highwayNumber < 1000);
    }

    // check if highway is auxiliary (between 100 and 999, inclusive)
    public static boolean isAuxiliaryHighway(int highwayNumber) {
        return highwayNumber > 99;
    }

    // find and display direction of highway
    public static void calculateAndDisplayDirection(int primaryNumber) {

        // set initial direction of highway as north-to-south
        String highwayDirection = "north-to-south.";

        // if highway number is even, set direction to east-to-west
        if (primaryNumber % 2 == 0) {
            highwayDirection = "east-to-west.";
        }

        // display direction
        System.out.println(highwayDirection);
        return;
    }
}
