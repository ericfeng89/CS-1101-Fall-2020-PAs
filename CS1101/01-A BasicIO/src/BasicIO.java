// **********************************************************************************************
// Program Name: BasicIO.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/3/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//      This program will perform simple input/output and math operations.
// **********************************************************************************************
import java.util.*;

public class BasicIO {
    public static void main(String[] args) {

        /* prompt user to input first integer, then assign their input to a variable */
        System.out.print("Enter an integer: ");
        Scanner scnr = new Scanner(System.in);
        int firstInteger = scnr.nextInt();

        /* display the number the user entered */
        System.out.println("You entered: " + firstInteger);

        /* using input, make calculations and assign to variables that store their values */
        int tripledInteger = 3 * firstInteger;
        int squaredInteger = firstInteger * firstInteger;

        /* display results and Voila! statement */
        System.out.println(firstInteger + " tripled is " + tripledInteger);
        System.out.println(firstInteger + " squared is " + squaredInteger + "!");
        System.out.println("Voila!");

        /* prompt user to enter second integer */
        System.out.print("Enter another integer: ");

        /* assign their second input to a variable */
        int secondInteger = scnr.nextInt();

        /* perform operations using first and second integers, then assign to variables that store their values */
        int integerSum = firstInteger + secondInteger;
        int integerProduct = firstInteger * secondInteger;

        /* display results and ending statement */
        System.out.println(firstInteger + " + " + secondInteger + " is " + integerSum);
        System.out.println(firstInteger + " * " + secondInteger + " is " + integerProduct);
        System.out.println("That is all!");
    }
}