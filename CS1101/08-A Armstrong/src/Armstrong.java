// **********************************************************************************************
// Program Name: Armstrong.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/20/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given 
//                  nor received any unauthorized aid on this assignment.
// 
// Program Description: This program will read an external .txt file that contains some numbers and 
//                      other tokens in the program and then compute whether the data contains 
//                      any three digits Armstrong number or not.
//************************************************************************************************

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Armstrong {
    public static final String FILENAME = "data.txt";

    public static void main(String[] args) throws FileNotFoundException {
        int armCount = 0;
        int nonArmCount = 0;
        int badTokenCount = 0;
        int number;

        Scanner scnr = new Scanner(System.in);

        // create file object
        File f = getInputFile(scnr);

        // second scanner for reading file
        Scanner input = new Scanner(f);

        System.out.println("\nScanning " + FILENAME + "...");

        // check if there are strings left in file
        while (input.hasNext()) {

            // if it's an integer, store its value
            if (input.hasNextInt()) {
                number = input.nextInt();

                // check if it's an Armstrong number
                // keep tally of what type of number
                if (isArmstrong(number)) {

                    System.out.println("Number : " + number + " is an Armstrong number.");
                    armCount++;
                }
                else {
                    nonArmCount++;
                }
            }

            // keep tally of bad tokens
            // flush out the bad token string
            else {
                badTokenCount++;
                input.next();
            }
        }
        displayReport(f, armCount, nonArmCount, badTokenCount);

    }

    /**
     * Gets the path of the file from the user and appends
     * it to the file name. Keep prompting the user until
     * the file under this path exists, and return the
     * file object.
     * @param scanner object (the first one)
     * @return the file object containing the file info
     */
    public static File getInputFile(Scanner console) {
        String path;
        String pathAndFile;

        // prompt user
        System.out.print("Enter path for file \"data.txt\"? ");
        path = console.nextLine();

        // combine path with file name
        pathAndFile = path + FILENAME;

        // create file object
        File f = new File(pathAndFile);

        // keep prompting until file exists under inputted path
        while (!f.exists()) {
            System.out.println("Cannot find file at that location. Please try again.");
            System.out.print("Enter path for file \"data.txt\"? ");

            path = console.nextLine();
            pathAndFile = path + FILENAME;

            f = new File(pathAndFile);
        }
        System.out.println(FILENAME + " successfully found.");
        return f;
    }

    /**
     * Takes in an integer and calculates whether
     * or not the number is an Armstrong number.
     * @param integer number (ideally 3-digit)
     * @return boolean, whether or not # is Armstrong
     */
    public static boolean isArmstrong(int number) {
        int onesDigit;
        int tensDigit;
        int hundredsDigit;

        // we are only looking for 3-digit Armstrong #'s
        if (number < 100 || number > 999) {
            return false;
        }

        hundredsDigit = number / 100;
        tensDigit = (number / 10) % 10;
        onesDigit = number % 10;

        // return true if cube of digits equals number, otherwise return false
        return hundredsDigit * hundredsDigit * hundredsDigit +
                tensDigit * tensDigit * tensDigit +
                onesDigit * onesDigit * onesDigit == number;

    }

    /**
     * Displays the final report, which states the
     * number of each type of token (Armstrong #,
     * non-Armstrong, or bad token).
     * @param file object
     * @param integer - amount of Armstrong #
     * @param integer - amount of non-Armstrong
     * @param integer - amount of bad tokens
     */
    public static void displayReport(File file, int armNum, int nArmNum,
                                     int badToken) {
        System.out.println("\nFinal Report for data.txt");
        System.out.println("\nFile Length (in bytes): " + file.length());
        System.out.println("The file has " + armNum + " Armstrong numbers.");
        System.out.println("The file has " + nArmNum + " non-Armstrong numbers.");
        System.out.println("The file has " + badToken + " bad tokens.");
    }
}
