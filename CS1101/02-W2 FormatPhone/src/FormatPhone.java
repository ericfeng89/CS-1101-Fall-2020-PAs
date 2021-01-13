// **********************************************************************************************
// Program Name: FormatPhone.java
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
//       This program will accept a non-formatted phone number as input and output
//       Assumes that the number entered is a valid 10-digit number, no need to verify
// **********************************************************************************************
import java.util.Scanner;

public class FormatPhone {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // declare variable that stores value of user input
        long phoneNumber;

        // declare variables that will assist in phone number reformatting
        int lastFour;
        int leadingSix;
        int middleThree;
        int areaCode;

        // prompt user
        System.out.println("Enter the unformatted phone number:");

        phoneNumber = scnr.nextLong();

        // extract last four digits
        lastFour = (int) (phoneNumber % 10000);

        // extract middle three digits
        leadingSix = (int) ((phoneNumber - lastFour) / 10000);
        middleThree = leadingSix % 1000;

        // extract area code
        areaCode = (leadingSix - middleThree) / 1000;

        // print formatted phone number
        System.out.println("The formatted phone number is: " + areaCode + "-" + middleThree + "-" + lastFour);
    }
}

