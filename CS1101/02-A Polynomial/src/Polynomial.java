// **********************************************************************************************
// Program Name: Polynomial.java
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
//       This program is the corrected version of Nadha Skolar's code.
//       The code reads in two roots of a polynomial and displays the canonical form.
// **********************************************************************************************

import java.util.*;

public class Polynomial {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // declare input variables (roots)
        int root1;
        int root2;

        // scan for roots and assign to input variables
        System.out.print("Enter the first root: ");
        root1 = scnr.nextInt();
        System.out.print("Enter the second root: ");
        root2 = scnr.nextInt();

        // print resulting polynomial
        System.out.print("The polynomial is: ");
        System.out.println("x^2 + " + -(root1 + root2) + "x + " + root1 * root2);
    }
}

