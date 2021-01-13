// **********************************************************************************************
// Program Name: TennesseeTwister.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 11/2/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will provide safety checks for groups of riders planning to go
//                      on the Tennessee Twister ride. If any of the requirements are not met, the
//                      ride will be deemed unsafe.
//************************************************************************************************

import java.util.Scanner;

public class TennesseeTwister {
    public static final int MIN_NUM_RIDERS = 2;
    public static final int MAX_NUM_RIDERS = 5;
    public static final int MIN_WEIGHT = 200;
    public static final int MAX_WEIGHT = 900;
    public static final int MAX_OVER_AVG = 100;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int numRiders = 0;
        int totalWeight;
        double avgWeight;
        char userChoice;

        do {
            int [] weightList = new int[MAX_NUM_RIDERS];

            numRiders = loadWeights(weightList, scnr);

            totalWeight = totalWeights(weightList, numRiders);
            avgWeight = calcAvgWeight(totalWeight, numRiders);

            System.out.println("The number of riders is " + numRiders);

            if (numRiders != 0) {
                System.out.println("Total ride weight: " + totalWeight);
                System.out.println("Avg rider weight:    " + avgWeight);
                System.out.println("\nPerforming Safety Tests...");
                if (!isSafeToRide(weightList, totalWeight, avgWeight, numRiders)) {
                    System.out.println("Tennessee Twister is UNSAFE. DO NOT PROCEED!");
                }
                else {
                    System.out.println("Tennessee Twister is good to go. Have fun!");
                }
            }
            userChoice = getUserChoice(scnr);
        }
        while (userChoice == 'Y');
    }

    /**
     * Accepts a console scanner and an empty array of weights.
     * Prompts the user for a list of weights with -1 signaling
     * the end of the list. Automatically stop allowing data entry
     * after the fifth rider. Returns the total number of riders on
     * the raft through the return statement (explicit return) and
     * the array of weights (returned implicitly).
     * @param int array : empty array of weights
     * @param object : scanner object
     * @return int : the number of riders on the raft
     */
    public static int loadWeights(int userWeight[], Scanner scnr) {
        int riderCount = 0;
        int indivWeight;

        for (int i = 0; i < userWeight.length; ++i) {
            userWeight[i] = 0;
        }

        while (riderCount < userWeight.length) {
            indivWeight = getWeight(scnr, riderCount + 1);

            if (indivWeight == -1) {
                return riderCount;
            }
            else {
                userWeight[riderCount] = indivWeight;
                riderCount++;
            }
        }
        return riderCount;
    }

    /**
     * Accepts a scanner and returns the weight of one of
     * the riders so it can be placed into the weight array.
     * Ensures that the weight entered is valid.
     * @param object : scanner object
     * @param int : the rider number (for prompt message)
     * @return int : the rider's weight
     */
    public static int getWeight(Scanner scnr, int riderNum) {
        System.out.println("Enter weight of rider #" + riderNum + " (or -1 when done): ");

        int weight;
        String userString;
        Scanner strScanner;

        // infinite loop until valid digit is returned
        while (true) {

            // read in input as a String to be safe
            userString = scnr.nextLine();

            // Now set a second Scanner to point to the string.
            strScanner = new Scanner(userString);

            // check to see if the user enter anything at all
            if (userString.length() != 0) {
                if (strScanner.hasNextInt()) {
                    weight = strScanner.nextInt();

                    if (weight > 0 || weight == -1) {
                        return weight;
                    }
                }
            }
            System.out.print("Rider weight must be a value > 0 or -1 to stop. ");
            System.out.println("Enter the rider weight:");
        }
    }

    /**
     * Accepts the array of weights and the number of riders
     * and computes the total weight of all the riders.
     * @param int array : array of weights
     * @param int : number of riders
     * @return int : total weight of all riders
     */
    public static int totalWeights(int userWeight[], int numRiders) {
        int sum = 0;
        for (int i = 0; i < numRiders; ++i) {
            sum += userWeight[i];
        }
        return sum;
    }

    /**
     * Accepts the total weight of riders along with the number
     * of riders and calculates and returns the average rider weight.
     * @param int : the total weight of riders
     * @param int : the number of riders
     * @return double : the average rider weight
     */
    public static double calcAvgWeight(int totalWeight, int numRiders) {
        return ((double) totalWeight / numRiders);
    }

    /**
     * Performs all of the necessary safety checks and displays
     * an appropriate message when any safety check fails with
     * the reason the check failed.
     * @param int array : array of rider weights
     * @param int : the total weight
     * @param int : the average rider weight
     * @param int : the number of riders
     * @return boolean : whether or not the ride is safe
     */
    public static boolean isSafeToRide(int[] userWeight, int totalWeight,
                                       double avgWeight, int numRiders) {
        if (numRiders < MIN_NUM_RIDERS) {
            System.out.println("SAFETY CHECK FAILED: Ride must have at least two riders.");
            return false;
        }

        else if (totalWeight < MIN_WEIGHT) {
            System.out.println("SAFETY CHECK FAILED: Ride weight UNDER minimum capacity.");
            return false;
        }

        else if (totalWeight > MAX_WEIGHT) {
            System.out.println("SAFETY CHECK FAILED: Ride weight OVER maximum capacity.");
            return false;
        }
        for (int i = 0; i < numRiders; ++i) {
            if (userWeight[i] - MAX_OVER_AVG > avgWeight) {
                System.out.println("SAFETY CHECK FAILED: Ride weight UNBALANCED.");
                return false;
            }
        }
        System.out.println("All safety checks passed.");
        return true;
    }

    /**
     * Prompts user when the round ends, asking if there
     * is another group of riders. Accepts char input (Y/N)
     * representing yes/no and returns the user's choice.
     * @param object : scanner object
     * @return character : user's choice (Y or N)
     */
    public static char getUserChoice(Scanner scnr) {
        System.out.println("\nDo you have another group of riders (y/n)?");
        char choice;

        choice = scnr.next().charAt(0);
        choice = Character.toUpperCase(choice);

        while (choice != 'Y' && choice != 'N') {
            System.out.println("Do you have another group of riders (y/n)?");
            choice = scnr.next().charAt(0);
            choice = Character.toUpperCase(choice);
        }
        return choice;
    }
}
