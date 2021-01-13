// **********************************************************************************************
// Program Name: ValidateMe.java
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
//       This program will perform a series of validations based on user input, prompting the user
//       to re-enter their input if it is invalid.
// **********************************************************************************************

import java.util.Scanner;

public class ValidateMe {

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

    // get valid test score (0 - 100), reprompting user if necessary
    public static int getValidIntScore(Scanner scnr) {

        // prompt user and get score
        System.out.println("Enter an integer test score between 0-100 inclusive:");
        int intScore = scnr.nextInt();

        // continue to reprompt user until test score is between 0 - 100
        while (intScore < 0 || intScore > 100) {
            System.out.print("Test score must be a value between 0-100 inclusive. ");
            System.out.println("Enter the test score:");
            intScore = scnr.nextInt();
        }
        return intScore;
    }

    // get valid full name, reprompting user if necessary
    public static String getValidStrName(Scanner scnr) {
        System.out.println("Enter student's full name:");

        // flush out extra carriage return
        scnr.nextLine();

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

    // get class average (0 - 100) accounting for epsilon, reprompting if necessary
    public static double getValidDoubleClassAvg(Scanner console) {
        System.out.println("Enter class average 0-100 inclusive:");
        double classAvg = console.nextDouble();

        // reprompt user if value outside range of -.0001 and 100.0001
        while ((classAvg < 0 - TOLERANCE) || (classAvg > 100 + TOLERANCE)) {
            System.out.print("Class average must be a value between 0 - 100 ");
            System.out.println("inclusive. Enter the class average:");
            classAvg = console.nextDouble();
        }

        // make sure average is 0.00 (not -0.00) if average is between -.0001 and 0
        return Math.abs(classAvg);
    }

    // get letter grade A-F, reprompting if necessary
    public static char getValidCharGrade(Scanner keyboard) {
        System.out.println("Enter the student grade (A, B, C, D, F):");
        char letterGrade = keyboard.next().charAt(0);

        // if letter grade outside range A-F, reprompt
        while (letterGrade < 'A' || letterGrade > 'F') {
            System.out.print("Grade must be one of: A, B, C, D, F. ");
            System.out.println("Enter the student grade:");
            letterGrade = keyb/oard.next().charAt(0);
        }
        return letterGrade;
    }
}

