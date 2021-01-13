// **********************************************************************************************
// Program Name: ValidateMeII.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/14/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will perform a series of validations based on user input, prompting the user
//       to re-enter their input if it is invalid.
// **********************************************************************************************

import java.util.Scanner;

public class ValidateMeII {

    // epsilon constant for comparing doubles
    private static final double TOLERANCE = 0.0001;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get and print valid test score
        int intScore = getValidIntScore(scnr);
        System.out.println("You entered a valid test score of: " + intScore);

        // get and print student's full name
        String fullName = getValidStrName(scnr);
        System.out.println("You entered the student: " + fullName);

        // get and print class average, rounded to two decimal places
        double classAvg = getValidDoubleClassAvg(scnr);
        System.out.print("You entered a valid avg: ");
        System.out.printf("%.2f", classAvg);
        System.out.println("");

        // get and print letter grade
        char letterGrade = getValidCharGrade(scnr);
        System.out.println("You entered a valid grade: " + letterGrade);
    }

    // get valid test score (0 - 100), reprompting user if invalid entry
    public static int getValidIntScore(Scanner scnr) {
        int intScore;
        String userString;
        Scanner strScanner;

        // prompt user
        System.out.println("Enter an integer test score between 0-100 inclusive:");

        // infinite loop until valid digit is returned
        while (true) {

            // read in input as a String to be safe
            userString = scnr.nextLine();

            // Now set a Scanner to point to the string.
            // Then we can use the hasNextInt() with the String
            // Second scanner ok since it's not pointing to console input
            strScanner = new Scanner(userString);

            // check to see if the user enter anything at all
            if (userString.length() != 0) {

                // check the String and see if it was an integer
                // if there's an integer, get its value
                if (strScanner.hasNextInt()) {
                    intScore = strScanner.nextInt();

                    // if integer score is within range, return it
                    if (intScore >= 0 && intScore <= 100) {
                        return intScore;
                    }
                }
            }

            // prompt user again if value entered was invalid
            System.out.print("Test score must be a value between 0-100 inclusive. ");
            System.out.println("Enter the test score:");
        }
    }

    // get valid full name, reprompting user if necessary
    public static String getValidStrName(Scanner scnr) {
        System.out.println("Enter student's full name:");

        // get name as next line in scanner
        // .trim() to remove whitespace before and after
        String fullName = scnr.nextLine().trim();

        // keep prompting user until non-empty and non-blank string entered
        while (fullName.isEmpty() || fullName.isBlank()) {
            System.out.print("Name must be non-empty and non-blank. ");
            System.out.println("Enter the student's full name:");
            fullName = scnr.nextLine().trim();
        }
        return fullName;
    }

    // get class average (0 - 100) accounting for epsilon
    // reprompt if invalid entry
    public static double getValidDoubleClassAvg(Scanner console) {
        double classAvg;
        String userString;
        Scanner strScanner;

        // prompt user
        System.out.println("Enter class average 0-100 inclusive:");

        while (true) {
            // read in input as a String to be safe
            userString = console.nextLine();

            // set a second Scanner to point to the string
            strScanner = new Scanner(userString);

            // did the user enter anything at all?
            if (userString.length() != 0) {

                // check the String and see if it was an integer
                // if there's a double, get its value
                if (strScanner.hasNextDouble()) {
                    classAvg = strScanner.nextDouble();

                    // if double entered is within the range, return it
                    if ((classAvg >= 0 - TOLERANCE) && (classAvg <= 100 + TOLERANCE)) {
                        return Math.abs(classAvg);
                    }
                }
            }

            // prompt user again if value entered was invalid
            System.out.print("Class average must be a value between 0 - 100 ");
            System.out.println("inclusive. Enter the class average:");
        }
    }

    // get letter grade A-F, reprompting if necessary
    public static char getValidCharGrade(Scanner keyboard) {
        String letterString;
        char letterGrade;

        // prompt user
        System.out.println("Enter the student grade (A, B, C, D, F):");

        // once line starting w/ string found, store value of entire line
        letterString = keyboard.nextLine();

        // if string input is empty or blank, reprompt
        // if letter grade outside range A-F (case insensitive), reprompt
        while (letterString.isBlank() || letterString.isEmpty() ||
                (letterString.charAt(0) < 'A' || letterString.charAt(0) > 'F') &&
                        (letterString.charAt(0) < 'a' || letterString.charAt(0) > 'f')) {
            System.out.print("Grade must be one of: A, B, C, D, F. ");
            System.out.println("Enter the student grade:");
            letterString = keyboard.nextLine();
        }

        // find first character of string and make uppercase
        letterGrade = letterString.charAt(0);
        letterGrade = Character.toUpperCase(letterGrade);

        return letterGrade;
    }
}
