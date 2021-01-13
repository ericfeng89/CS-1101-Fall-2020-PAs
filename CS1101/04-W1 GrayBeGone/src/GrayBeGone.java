// **********************************************************************************************
// Program Name: GrayBeGone.java
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
//       This program will prompt the user to enter integer values for red, green, and blue
//       and then proceed to remove the gray part and display the resulting RGB.
// **********************************************************************************************

import java.util.*;

public class GrayBeGone {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // prompt user
        System.out.println("Enter the RGB values for red, green and blue each separated by a space");

        // store the three RGB values that the user inputs
        int firstInt = scnr.nextInt();
        int secondInt = scnr.nextInt();
        int thirdInt = scnr.nextInt();

        // find the lowest of the three values
        int lowestInt = Math.min(Math.min(firstInt, secondInt), thirdInt);

        // reduce each RGB value by the lowest of the three values
        firstInt -= lowestInt;
        secondInt -= lowestInt;
        thirdInt -= lowestInt;

        // display results
        System.out.println("");
        System.out.print("The RGB values with the gray gone are: " + firstInt + " " + secondInt);
        System.out.println(" " + thirdInt);
    }
}

