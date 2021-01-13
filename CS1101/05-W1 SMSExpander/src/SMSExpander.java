// **********************************************************************************************
// Program Name: SMSExpander.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/30/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will prompt the user to enter a sentence possibly containing one or more
//       SMS texting abbreviations entered in all uppercase. If the user's input string matches
//       any known text message abbreviations that the program handles, it outputs the sentence
//       with the non-abbreviated form.
// **********************************************************************************************

import java.util.*;

public class SMSExpander {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String abbreviatedText;
        String expandedText;

        // get user input and store string
        abbreviatedText = getText(scnr);

        // check for SMS abbreviations, replace them, display message
        expandedText = expandAbbreviation(abbreviatedText);

        // print expanded string
        printExpanded(expandedText);
    }

    // prompt user for text, read it in, and return string
    public static String getText(Scanner scnr) {
        System.out.println("Input the text you'd like to decode below followed by the enter key:\n");
        String untranslatedText = scnr.nextLine();
        return untranslatedText;
    }

    // check text for SMS abbreviations, replace them with expanded forms, display message
    public static String expandAbbreviation(String userString) {

        // if no known abbreviations found, notify user
        if (!(userString.contains("BFF") || userString.contains("BTE") ||
                userString.contains("IDK") || userString.contains("STT"))) {

            // display message
            System.out.println("Could not find any SMS abbreviations I know to expand.");
        }

        // if the following abbreviations are found, replace and display message
        if (userString.contains("BFF")) {
            userString = userString.replace("BFF", "best friend forever");
            System.out.println("Replacing \"BFF\" with \"best friend forever\"");
        }
        if (userString.contains("BTE")) {
            userString = userString.replace("BTE", "blow the exam");
            System.out.println("Replacing \"BTE\" with \"blow the exam\"");
        }
        if (userString.contains("IDK")) {
            userString = userString.replace("IDK", "I don't know");
            System.out.println("Replacing \"IDK\" with \"I don't know\"");
        }
        if (userString.contains("STT")) {
            userString = userString.replace("STT", "spill the tea");
            System.out.println("Replacing \"STT\" with \"spill the tea\"");
        }
        return userString;
    }

    // print resulting string (with all necessary replacements made)
    public static void printExpanded(String result) {
        System.out.println("");
        System.out.println("Expanded: " + result);
    }
}
