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
// Program Description: This program is a parking simulation. The parking lot will have sizes varying
//                      from 50 to 250 spots. Statistics including total $ paid and total number of
//                      hours parked will be displayed.
//************************************************************************************************

import java.util.Arrays;

public class Parking {
    static final int[] PKG_SIZE = {50, 100, 150, 200, 250};
    static final int HOURLY_COST = 3;
    static final int DAILY_COST = 20;
    static final int HOUR_MAX = 72;
    static final int HOURS_SIMULATION = 31 * 24;
    static final int HOURS_IN_DAY = 24;
    static final long MODULUS = 2147483647;
    static final long SEED = 314159;
    static int rndNb = (int) SEED;

    public static void main(String args[]) {
        int [] totPaid = new int [PKG_SIZE.length];    // Total paid by cars for the simulation.
        int [] totIn = new int [PKG_SIZE.length];      // Total number of cars accepted to enter the parking.
        int [] totRefused = new int [PKG_SIZE.length]; // Total number of cars not accepted to enter the parking.
        int [] totHours = new int [PKG_SIZE.length];   // Total number of hours that all cars parked.

        // initialize array
        for (int i = 0; i < PKG_SIZE.length; ++i) {
            totPaid[i] = 0;
            totIn[i] = 0;
            totRefused[i] = 0;
            totHours[i] = 0;
        }

        printHeader();

        int ipk;
        int iHour = 0;

        for (ipk = 0; ipk < PKG_SIZE.length; ++ipk) {
            int [] parking = new int[PKG_SIZE[ipk]];
            int [] time = new int[PKG_SIZE[ipk]];

            // initialize arrays
            for (int i = 0; i < parking.length; ++i) {
                parking[i] = 0;
                time[i] = 0;
            }

            for (int i = 0; i < HOURS_SIMULATION; ++i) {
                simulateHour(ipk, i, parking, time, totPaid, totIn, totRefused, totHours);
            }

            if (ipk == PKG_SIZE.length - 1) {
                for (int i = 0; i < PKG_SIZE.length; ++i) {
                    System.out.printf("%,15d", PKG_SIZE[i]);
                    System.out.printf("%,15d", totPaid[i]);
                    System.out.printf("%,15d", totIn[i]);
                    System.out.printf("%,15d", totRefused[i]);
                    System.out.printf("%,15d", totHours[i]);
                    System.out.printf("%15.2f\n", (double) totHours[i]/totIn[i]);
                }
            }
        }
        printFooter();
    }

    /**
     * Pseudo random number generator
     * @param int : previous random number or seed
     * @return int : next random number
     */
    public static int getRandom(int rnd) {
        long multiplier = 16807;
        return (int) ((multiplier * rnd) % MODULUS);
    }

    /**
     * Simulates and hour of the parking lot. Updates
     * several arrays without returning anything. This
     * function will be called 744 times (because the
     * duration of this simulation is 744 hours).
     * @param int : index of the parking lot size array
     * @param iHour : the current hour
     * @param array : contains info about how long each car will park
     * @param array : time left for each car in parking spot
     * @param array : total paid by cars for the simulation
     * @param array : total number of cars accepted to enter the parking.
     * @param array : total number of cars not accepted to enter the parking.
     * @param array : total number of hours that all cars parked.
     * @return void
     */
    public static void simulateHour (int ipk, int iHour, int[] parking, int[] time,
                                     int[] totPaid, int[] totIn, int[] totRefused, int[] totHours) {
        for (int i = 0; i < parking.length; ++i) {
            if (time[i] == 0 && parking[i] != 0) {
                totPaid[ipk] += calculatePayment(parking[i]);
                parking[i] = 0;
            }
        }

        int numCars;
        int numHours;
        boolean openSpace = false;

        numCars = getNumCars(iHour);

        while (numCars > 0) {
            for (int i = 0; i < parking.length; ++i) {
                if (parking[i] == 0) {
                    rndNb = getRandom(rndNb);

                    // random number 1 to 72
                    numHours = (rndNb % 72) + 1;

                    parking[i] = numHours;
                    time[i] = numHours;

                    totHours[ipk] += numHours;

                    totIn[ipk]++;
                    numCars--;
                    openSpace = true;

                    break;
                }
            }
            if (!openSpace) {
                totRefused[ipk]++;
                numCars--;
            }
            openSpace = false;
        }

        for (int i = 0; i < parking.length; ++i) {
            if (time[i] != 0) {
                time[i]--;
            }
        }
    }

    /**
     * Calculates the cost of parking for each car,
     * given the amount of hours they are staying.
     * @param int : amount of hours to park
     * @return int : the cost of parking the car
     */
    public static int calculatePayment(int hours) {
        int total;
        int secondDayCost;
        int thirdDayCost;

        total = HOURLY_COST * hours;

        if (total < DAILY_COST) {
            return total;
        }
        else if (total > DAILY_COST && hours <= HOURS_IN_DAY) {
            return DAILY_COST;
        }

        secondDayCost = (hours - HOURS_IN_DAY) * HOURLY_COST;

        if (secondDayCost < DAILY_COST) {
            total = DAILY_COST + secondDayCost;
            return total;
        }
        else if (secondDayCost > DAILY_COST && hours <= 2 * HOURS_IN_DAY) {
            return (2 * DAILY_COST);
        }

        thirdDayCost = (hours - 2 * HOURS_IN_DAY) * HOURLY_COST;

        if (thirdDayCost < DAILY_COST) {
            total = 2 * DAILY_COST + thirdDayCost;
            return total;
        }
        return (3 * DAILY_COST);
    }

    /**
     * Prints the header of the table.
     * @return void
     */
    public static void printHeader() {
        System.out.println("Result of simulation.");
        System.out.print("Duration for each parking size: " + HOURS_SIMULATION);
        System.out.print(" hours (" + HOURS_SIMULATION / HOURS_IN_DAY + " Days and ");
        System.out.println(HOURS_SIMULATION % HOURS_IN_DAY + " hours)");
        System.out.print("            (1)            (2)            (3)");
        System.out.println("            (4)            (5)            (6)");
    }

    /**
     * Prints the footer of the table.
     * @return void
     */
    public static void printFooter() {
        System.out.println("\n\t(1) : parking size (number of slots)");
        System.out.println("\t(2) : total ($) paid during simulation.");
        System.out.println("\t(3) : number of cars accepted to park during simulation.");
        System.out.println("\t(4) : number of cars refused to park during simulation.");
        System.out.println("\t(5) : total number of hours for all cars which parked.");
        System.out.println("\t(6) : average number of hours that a car parked.\n");
    }

    /**
     * Gets the number of cars that will try to park
     * in a given hour.
     * @param int : the current hour
     * @return int : the number of cars that will try to park
     */
    public static int getNumCars(int iHour) {
        int numCars = 0;

        if (iHour % HOURS_IN_DAY >= 6 && iHour % HOURS_IN_DAY < 18) {
            rndNb = getRandom(rndNb);

            // random number between 5 and 9
            numCars = (rndNb % 5) + 5;
        }
        else if (iHour % HOURS_IN_DAY >= 18 && iHour % HOURS_IN_DAY < 21) {
            rndNb = getRandom(rndNb);

            // random number between 1 and 2
            numCars = (rndNb % 2) + 1;
        }
        else {
            numCars = 0;
        }
        return numCars;
    }
}
