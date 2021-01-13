// **********************************************************************************************
// Program Name: WeShipIt.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 9/24/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will prompt the user for the item description and item weight followed by
//       the shipping method. Assuming the values are valid, that information should be passed to
//       a method that will calculate and return the shipping cost. An invoice will be displayed.
// **********************************************************************************************

import java.util.*;

public class WeShipIt {

    // initialize constants (shipping costs)
    public static final int OVERNIGHT_CHARGE = 5;
    public static final int TWO_DAY_CHARGE = 2;
    public static final int ECONOMY_CHARGE = 1;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // get item description
        String itemDescription = getItemDescription(keyboard);

        // notify user and terminate program if no description given
        if (itemDescription.equals("")) {
            System.out.println("Invalid description. Program cannot continue");
            System.exit(0);
        }

        // get item weight
        double shipWeight = getShipWeight(keyboard);

        // notify user and terminate program if no shipping weight given
        if (shipWeight <= 0.0) {
            System.out.println("Invalid shipping weight. Program cannot continue");
            System.exit(0);
        }

        // get shipping method
        char shipMethod = getShipClass(keyboard);
        shipMethod = Character.toUpperCase(shipMethod);

        // calculate shipping cost and display results
        double shipCost = calculateShipping(shipMethod, shipWeight);

        // display shipping charge invoice
        displayResults(itemDescription, shipWeight, shipMethod, shipCost);
    }

    // get item description and return it
    public static String getItemDescription(Scanner keyboard) {

        // get item description
        System.out.println("Enter item description:");
        String description = keyboard.nextLine();
        return description;
    }

    // get shipping weight and return it
    public static double getShipWeight(Scanner console) {

        // get shipping weight
        System.out.println("Enter item weight in lbs:");
        double weight = console.nextDouble();
        return weight;
    }

    // get user's choice for shipping method and return it
    public static char getShipClass(Scanner keyboard) {
        char shipMethod;

        // get shipping method
        System.out.println();
        System.out.println("How fast would you like to ship your package:");
        System.out.println("(O)vernight");
        System.out.println("(T)wo Days");
        System.out.println("(E)conomy (may take up to 7 days)");
        System.out.print("Choose an option: ");

        // look for shipping method character (first character)
        shipMethod = keyboard.next().charAt(0);
        return shipMethod;
    }

    // calculate and return shipping charge
    public static double calculateShipping(char shipMethod, double weight) {

        double shipCharge = 0.0;

        // multiply weight by cost per pound based on user char input (case insensitive)
        if (shipMethod == 'o' || shipMethod == 'O') {
            shipCharge = weight * 5;
        }
        else if (shipMethod == 't' || shipMethod == 'T') {
            shipCharge = weight * 2;
        }
        else if (shipMethod == 'e' || shipMethod == 'E') {
            shipCharge = weight;
        }

        // notify user and terminate program if invalid shipping method
        else {
            System.out.println("Invalid shipping method. Program cannot continue");
            System.exit(0);
        }
        return shipCharge;
    }

    // display shipping charge invoice
    public static void displayResults(String itemDescription, double shipWeight, char shipMethod, double shipCost) {
        System.out.println();
        System.out.println("*** WE SHIP INVOICE ***");
        System.out.println("Item Description: " + itemDescription);
        System.out.printf("Item Weight: %.2f\n", shipWeight);
        System.out.println("Ship Method: " + shipMethod);
        System.out.printf("Total Cost: $%.2f\n", shipCost);
    }
}
