// **********************************************************************************************
// Program Name: MonteCarlo.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/15/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Description: Two students are each right X% of the time. When they answer a quiz of N questions
//              what is the probability their answer is correct when they both agree.
//              This program simulates the above situation to find the answer empirically.
//************************************************************************************************

import java.util.Scanner;

public class MonteCarlo {
    static int rnd;
    static final int N_CNT = 6;     // Each question has that many outcomes
    static final int Q_CNT = 10000; // Number of questions for the test

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // first random number is the user's input
        rnd = getSeed(sc);

        // print table header, data, and key
        printTableHeader();
        printTableData();
        printTableKey();
    }

    /**
     * Create a pseudo random number between MIN (included) and MAX (included)
     * Note: modifies the global number rnd each time.
     * @return: random number
     */
    public static int getRandom(int rnd) {
        long multiplier = 16807;
        long modulus = 2147483647;
        return (int) ((multiplier * rnd) % modulus);
    }

    /**
     * Gets integer from 0 to 100, reprompting if necessary.
     * @param scanner object (to read in input)
     * @return integer, to serve as random number later
     */
    public static int getSeed(Scanner sc) {
        int seed;

        String userString;
        Scanner strScanner;

        // infinite loop until valid integer entered
        while (true) {

            // prompt user
            System.out.print("Enter an integer in ]0, 100] ? ");

            // read in input as a String to be safe
            userString = sc.nextLine();

            // set a second scanner to point to the string
            strScanner = new Scanner(userString);

            // did the user enter anything at all?
            if (userString.length() != 0) {

                // check the String and see if was an integer
                // if there's an integer, get its value
                if (strScanner.hasNextInt()) {
                    seed = strScanner.nextInt();

                    // if integer entered is within range, return it
                    if (seed >= 0 && seed <= 100) {
                        return seed;
                    }
                }
            }
            System.out.println("Incorrect value.");
        }
    }

    /**
     * Determines if answer to given question is right or wrong.
     * If right, then integer 1 will be returned. If wrong, a
     * random integer between 2 and the # of question options
     * will be returned.
     * @param an int, the % chance the student gets correct answer
     * @return an int, the answer to the question
     */
    public static int getAnswer(int pCorrect) {

        // generate new random number and get last two digits
        rnd = getRandom(rnd);
        int rndTwoDigit = rnd % 100;

        // return 1 if answer is correct...
        // correct answers are when the random number
        // is less than the percent chance
        if (rndTwoDigit < pCorrect) {
            return 1;
        }

        // else, return random number between 2 and # of question options
        else {

            // generate new random number
            rnd = getRandom(rnd);

            return (rnd % (N_CNT - 1)) + 2;
        }
    }

    /**
     * Prints the header of the table
     */
    public static void printTableHeader() {
        System.out.print("*******************************");
        System.out.println("**********************************");
        System.out.print("*  (1)  *  (2)  *  (3)  *  (4)  *  ");
        System.out.println("(5)  *  (6)  *  (7)  *  (8)  *");
        System.out.print("**********************************");
        System.out.println("*******************************");
    }

    /**
     * Prints all of the data in the table
     * Prints data row by row
     * Rows differ by the percent chance of a correct answer
     */
    public static void printTableData() {
        int numCorrect1 = 0;
        int numCorrect2 = 0;
        int bothCorrect = 0;
        int sameWrong = 0;
        int numDisagree = 0;
        int firstStudentAnswer;
        int secondStudentAnswer;
        double ratio;

        // for each % chance, print entire row of data
        for (int i = 10; i <= 100; i = i + 10) {

            // iterate through 10000 questions for each student
            for (int j = 0; j < Q_CNT; ++j) {

                // get answer from first student
                firstStudentAnswer = getAnswer(i);

                // if answer is correct, increment counter
                if (firstStudentAnswer == 1) {
                    numCorrect1++;
                }

                // get answer from second student
                secondStudentAnswer = getAnswer(i);

                // if answer is correct, increment counter
                if (secondStudentAnswer == 1) {
                    numCorrect2++;
                }

                // if both students got the correct answer, increment counter
                if (firstStudentAnswer == secondStudentAnswer && firstStudentAnswer == 1) {
                    bothCorrect++;
                }

                // if both students got different answers, increment counter
                else if (firstStudentAnswer != secondStudentAnswer) {
                    numDisagree++;
                }

                // if both students got same wrong answer, increment counter
                else {
                    sameWrong++;
                }
            }

            // given that both students got the same answer...
            // calculate the proportion of times when they were both correct
            ratio = (double) (bothCorrect) / (bothCorrect + sameWrong);

            // print formatted data
            // space between asterisks is constant
            // last column rounded to 2 decimal places
            System.out.printf("*%5d", i);
            System.out.print("%");
            System.out.printf(" * %5d", Q_CNT);
            System.out.printf(" * %5d", numCorrect1);
            System.out.printf(" * %5d", numCorrect2);
            System.out.printf(" * %5d", bothCorrect);
            System.out.printf(" * %5d", sameWrong);
            System.out.printf(" * %5d", numDisagree);
            System.out.printf(" * %5.2f", ratio);
            System.out.println(" *");

            // reset counters after each row is printed
            numCorrect1 = 0;
            numCorrect2 = 0;
            bothCorrect = 0;
            sameWrong = 0;
            numDisagree = 0;
        }
    }

    /**
     * Print key/bottom of the table.
     */
    public static void printTableKey() {
        System.out.println("\n (1) : Probability for each student to be right.");
        System.out.println(" (2) : Number of questions simulated.");
        System.out.println(" (3) : Number of times student 1 was right.");
        System.out.println(" (4) : Number of times student 2 was right.");
        System.out.println(" (5) : Number of times student 1&2 were both right.");
        System.out.println(" (6) : Number of times student 1&2 agreed but were wrong.");
        System.out.println(" (7) : Number of times student 1&2 disagreed.");
        System.out.println(" (8) : Ratio (5) / [(5) + (6)].");
    }
}
