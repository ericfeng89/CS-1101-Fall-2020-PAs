// **********************************************************************************************
// Program Name: PaintCalculator.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/15/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will output the amount of gallons of paint the customer needs to purchase
//       given their room dimensions.
// **********************************************************************************************

import java.util.*;        //acccess to scanner
import java.lang.Math;     //access to math functions like ceiling

public class PaintCalculator {

    // declare constants/final variables
    final static int NUM_OF_WALLS = 4;
    final static int FEET_PER_GALLON = 350;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get wall height and width
        double wallHeight = getWallHeight(scnr);
        double wallWidth = getWallWidth(scnr);

        // calculate wall area and gallons of paint required, then display results
        double wallArea = calcWallArea(wallHeight, wallWidth);
        double gallons = calcGallons(wallArea);
        displayResults(wallArea, gallons);
    }

    // ** THIS METHOD SUPPLIED BY PROFESSOR DAN ARENA **
    // ************************************************************************
    // DESCRIPTION - getWallHeight() - prompts and returns wall height in  feet
    // PARAMETERS:
    //      Scanner scnr - access to input scanner
    // RETURNS: double wall height in feet
    // ************************************************************************
    public static double getWallHeight(Scanner scnr) {

        // get wall height
        System.out.println("Enter wall height (feet):");
        double wallHeight = scnr.nextDouble();
        return wallHeight;
    }

    // ** THIS METHOD SUPPLIED BY PROFESSOR DAN ARENA **
    // ************************************************************************
    // DESCRIPTION - getWallWidth() - prompts and returns wall width in  feet
    // PARAMETERS:
    //      Scanner scnr - access to input scanner
    // RETURNS: double wall width in feet
    // ************************************************************************

    // prompt user for width of wall, returns value
    public static double getWallWidth(Scanner scnr) {

        // get wall width
        System.out.println("Enter wall width (feet):");
        double wallWidth = scnr.nextDouble();
        return wallWidth;
    }

    // calculate area of one wall given wall height and width
    public static double calcWallArea(double wallHeight, double wallWidth) {
        double wallArea;
        wallArea = wallHeight * wallWidth;
        return wallArea;
    }

    // calculate # of gallons of paint given total area of one wall
    public static double calcGallons(double wallArea) {
        double gallons;
        double roomArea;

        // multiply wall area by 4 (because 4 walls)
        roomArea = wallArea * NUM_OF_WALLS;

        // roomArea / 350 gives gallons of paint
        gallons = roomArea / FEET_PER_GALLON;
        return gallons;
    }

    // display results and calculate cans needed
    public static void displayResults(double wallArea, double gallonsPaintNeeded) {
        int cans;

        // print results
        System.out.println("Wall area: " + wallArea + " square feet");
        System.out.println("Paint needed (assuming 4 walls): " + gallonsPaintNeeded + " gallons");

        // round up gallons using ceiling function, typecast to change double to int data type
        cans = (int) Math.ceil(gallonsPaintNeeded);
        System.out.println("How many cans to purchase: " + cans + (" can(s)"));

        return;
    }
}
