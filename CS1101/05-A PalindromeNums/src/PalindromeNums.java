// **********************************************************************************************
// Program Name: PalindromeNums.java
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
//       This program takes in an integer value between 1-999 inclusive as input and determines
//       whether or not the value entered is a numeric palindrome.
// **********************************************************************************************

import java.util.Scanner;

public class PalindromeNums {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int userNum;

        // get and store user number
        userNum = getNumber(scnr);

        // if number is not 1-999, display error message and end program
        if (userNum < 1 || userNum > 999) {
            System.out.println("Input must be 1-999. Program cannot continue.");
            System.exit(0);
        }

        // display whether user's number is a palindrome
        if (isNumericPal(userNum)) {
            System.out.println(userNum + " is a numeric palindrome.");
        }
        else {
            System.out.println(userNum + " is not a numeric palindrome.");
        }
    }

    // prompt user to enter number, read it in, return number
    public static int getNumber(Scanner scnr) {
        System.out.println("Enter your integer number (1-999) on the line below:\n");
        int number = scnr.nextInt();
        return number;
    }

    // check to see if the user's number is a palindrome
    public static boolean isNumericPal(int userNum) {
        int onesDigit;
        int tensDigit;
        int hundredsDigit;

        // if single digit number, then palindrome (true)
        if (userNum < 10) {
            return true;
        }

        // two digit number: palindrome (true) if ones and tens digit match
        else if (userNum < 100) {
            onesDigit = userNum % 10;
            tensDigit = userNum / 10;

            if (onesDigit == tensDigit) {
                return true;
            }
        }

        // three digit number: palindrome (true) if ones and hundreds digit match
        else {
            onesDigit = userNum % 10;
            hundredsDigit = userNum / 100;

            if (onesDigit == hundredsDigit) {
                return true;
            }
        }

        // if none of the above conditions are met, number is not a palindrome (false)
        return false;
    }
}
