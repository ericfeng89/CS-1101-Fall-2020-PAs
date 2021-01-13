// **********************************************************************************************
// Program Name: Parking.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 11/4/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will compare the area of two triangles given their bases and 
//                      heights.
//************************************************************************************************

import java.util.Scanner;

public class TriangleArea {

    /**
     * The main method will call several Triangle methods,
     * setting the base and height of the triangle and calculating
     * its area. It will do this for both triangles, and call
     * the getArea() function. The information of the larger-
     * area triangle will be printed.
     * @param args
     * @return void
     */
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        Triangle tri = new Triangle();

        double base1 = scnr.nextDouble();
        double height1 = scnr.nextDouble();

        tri.setBase(base1);
        tri.setHeight(height1);

        double area1 = tri.getArea();

        double base2 = scnr.nextDouble();
        double height2 = scnr.nextDouble();

        tri.setBase(base2);
        tri.setHeight(height2);

        double area2 = tri.getArea();

        System.out.println("Triangle with larger area:");

        if (area1 >= area2) {
            tri.setBase(base1);
            tri.setHeight(height1);
            tri.printInfo();
        }
        else {
            tri.printInfo();
        }
    }
}