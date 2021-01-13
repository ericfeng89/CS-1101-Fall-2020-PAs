// **********************************************************************************************
// Program Name: SMSTranslator.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/22/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will prompt the user to enter an SMS texting abbreviation, then print
//       the corresponding message.
// **********************************************************************************************

import java.util.*;

public class SMSTranslator {

    // assigns long phrases to acronyms to improve readability in decodeAbbreviation() method
    public static final String B_F_F = "Best Friends FOREVER!!! :-)";
    public static final String B_T_E = "Blew The Exam :-(";
    public static final String S_T_T = "Spill The Tea ;-)";
    public static final String UNKNOWN_SMS = "I'm sorry. I don't know that abbreviation yet.";

    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);

        String userString;
        String decodedAbbreviation;

        // stores user's input
        userString = getAbbreviation(scnr);

        // stores and displays decoded (long) phrase
        decodedAbbreviation = decodeAbbreviation(userString);
        printAbbreviation(decodedAbbreviation);
    }

    // prompts user for abbreviation, reads it in, returns string to main
    public static String getAbbreviation(Scanner scnr) {

        // prompt user
        System.out.println("Input the SMS abbreviation and I'll decode it for you: ");

        // looks for next string and returns it to main
        String userInput = scnr.next();
        return userInput;
    }

    // using abbreviated string as input, evaluates text abbreviation and returns decoded string to main
    public static String decodeAbbreviation(String userString) {

        // return corresponding decoded string if input string matches these following strings
        if (userString.equals("BFF")) {
            return B_F_F;
        }
        else if (userString.equals("BTE")) {
            return B_T_E;
        }
        else if (userString.equals("STT")) {
            return S_T_T;
        }

        // if input does not match any of the strings, return "I'm sorry..." message
        else {
            return UNKNOWN_SMS;
        }
    }

    // displays decoded string on screen
    public static void printAbbreviation(String result) {
        System.out.println("Translation: " + result);
    }
}
