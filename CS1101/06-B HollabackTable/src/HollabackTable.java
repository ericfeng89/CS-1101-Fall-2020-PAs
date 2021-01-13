// **********************************************************************************************
// Program Name: HollabackTable.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/7/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description:
//       This program will print a hollaback table with the number of rows depending on user input.
// **********************************************************************************************

import java.util.Scanner;

public class HollabackTable {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // get number of rows
        int numRows = getHowManyRows(scnr);

        // display entire table
        drawHollabackTable(numRows);

        // ask user if they want to do again, get response
        char yesOrNo = getDoAgain(scnr);

        // keep repeating process until user chooses not to
        while (yesOrNo == 'Y' || yesOrNo == 'y') {
            numRows = getHowManyRows(scnr);
            drawHollabackTable(numRows);
            yesOrNo = getDoAgain(scnr);
        }
    }

    // get number of rows, reprompting if necessary
    public static int getHowManyRows(Scanner console) {
        System.out.println("How many rows of the table would you like?");
        int numRows = console.nextInt();

        // if number entered not within range 1-30, reprompt
        while (numRows < 1 || numRows > 30) {
            System.out.print("Please enter a value 1-30 inclusive: ");
            numRows = console.nextInt();
        }
        return numRows;
    }

    // get character indicating whether user would like to do again
    public static char getDoAgain(Scanner console) {
        System.out.print("Would you like to do it again (Y/N)? ");
        char yesOrNo = console.next().charAt(0);

        // continue reprompting if response is invalid
        while (yesOrNo != 'Y' && yesOrNo != 'y' && yesOrNo != 'N' && yesOrNo != 'n') {
            System.out.print("Please enter a valid response (Y/N): ");
            yesOrNo = console.next().charAt(0);
        }
        return yesOrNo;
    }

    // draw the entire table
    public static void drawHollabackTable(int howManyRows) {

        // display header message
        System.out.println("\nHere is your hollaback table:");

        // print table line by line, calling computeLine() method
        int i;
        for (i = 1; i <= howManyRows; i++) {
            System.out.println(computeLine(i));
        }
        System.out.println("");
    }

    /*
     * This method prints each individual line of the table.
     * Since the row's string depends entirely on the previous line,
     * simulate printing the entire table up to the final row (whichRow)
     * and only return that row.
     */
    public static String computeLine(int whichRow) {

        // data validation: if input outside range 1-30, return empty string
        if (whichRow < 1 || whichRow > 30) {
            return "";
        }

        // initial row: used to help calculate next rows
        if (whichRow == 1) {
            return "1";
        }

        int rowLength;
        int newNumberIndex;
        char currentChar;
        int counter = 1;
        String currentString = "1";
        String nextString = "";

        // from second row to final row (whichRow),
        // find next string using previous, returning the final
        // row string at the end.
        int i;
        for (i = 2; i <= whichRow; i++) {

            // find length of current row
            rowLength = currentString.length();

            // this variable stores the index of the next number that is different
            // if the number is 11112222, newNumberIndex starts at 0 and changes to 5
            // reset to 0 after each line is calculated
            newNumberIndex = 0;

            // keep finding new numbers in current row until there are none left
            while (newNumberIndex < rowLength) {

                // store current character that we are looking for
                currentChar = currentString.charAt(newNumberIndex);

                // increment counter if next character matches the current one (currentChar)
                while (newNumberIndex + counter < currentString.length() &&
                        currentString.charAt(newNumberIndex + counter) == currentChar) {
                    counter++;
                }

                // create next row's string
                // convert counter ("number of...") to string, append
                nextString += Integer.toString(counter);

                // convert number being counted to string, append
                nextString += String.valueOf(currentChar);

                // set newNumberIndex to index of next unique number in the row
                newNumberIndex += counter;

                // reset counter
                counter = 1;
            }

            // the new string we created is now the current string for next iteration
            currentString = nextString;
            nextString = "";
        }
        return currentString;
    }
}

