// **********************************************************************************************
// Program Name: DoTheMath.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/8/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will accept three floating-point numbers as input and output several calculations using those numbers.
// **********************************************************************************************
import java.util.Scanner;

public class DoTheMath {
    public static void main(String[] args) {

        // declare variables to be assigned to scanner inputs
        double x;
        double y;
        double z;

        // declare variables that store values of immediate and final calculations
        double xPowerY;
        double yPowerZ;
        double xPowerYPowerZ;
        double absValX;
        double finalCalculation;

        // prompt user to enter 3 doubles, then print extra newline
        System.out.println("Enter the values for x, y, z:");
        System.out.println("");

        // scan for 3 doubles as input
        Scanner scnr = new Scanner(System.in);
        x = scnr.nextDouble();
        y = scnr.nextDouble();
        z = scnr.nextDouble();

        // calculate x^y, print result
        xPowerY = Math.pow(x, y);
        System.out.println("x to the power y is " + xPowerY);

        // calculate x^y^z, print result
        yPowerZ = Math.pow(y, z);
        xPowerYPowerZ = Math.pow(x, yPowerZ);
        System.out.println("x to the power y to the power z is " + xPowerYPowerZ);

        // calculate |x|, print result
        absValX = Math.abs(x);
        System.out.println("The absolute value of x is " + absValX);

        // make final calculation, print result
        finalCalculation = Math.sqrt(Math.pow((x * y), z));
        System.out.println("The square root of x*y to the power z is " + finalCalculation);
    }
}
