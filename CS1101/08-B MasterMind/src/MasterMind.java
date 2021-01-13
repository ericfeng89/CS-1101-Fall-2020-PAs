// **********************************************************************************************
// Program Name: MasterMind.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/21/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program is our version of MasterMind, where the computer will select
//                      a random four-character mastercode. The user will have 6 chances to guess
//                      it. After each guess, they will be told how many colors they guessed
//                      correctly and how many of those were in the correct position.
//************************************************************************************************

import java.util.*;

public class MasterMind {
    public static final int NUM_OF_GUESSES = 6;

    public static void main(String args[]) {
        int numRounds = 0;
        int numWins = 0;
        char nextRound;
        String masterCode;
        boolean roundResult;

        Scanner scnr = new Scanner(System.in);

        // keep doing rounds until user says 'N'
        do {
            displayInstructions();

            // create random object
            Random randGen = new Random();

            masterCode = buildMasterCode(randGen);
            roundResult = playOneRound(scnr, masterCode);

            // increment win total if round is won
            if (roundResult) {
                numWins++;
            }

            // increment number of rounds played
            numRounds++;

            // see if user wants to play next round
            nextRound = getUserChoice(scnr);
        } while (nextRound == 'Y');

        // display statistics when all rounds end
        displayStats(numRounds, numWins);
    }

    /**
     * Displays the instructions at the start of each round.
     * @return void
     */
    public static void displayInstructions() {
        System.out.println("WELCOME TO MASTERMIND\n");
        System.out.println("How to Play:");
        System.out.print("1. I will pick a 4 character color code out of the ");
        System.out.println("following colors: Yellow, Blue, Red, Green.");
        System.out.print("2. You try to guess the code using only the first ");
        System.out.print("letter of any color. Example if you type YGBR that ");
        System.out.println("means you guess Yellow, Green, Blue, Red.");
        System.out.print("3. I will tell you if you guessed any colors correct ");
        System.out.println("and whether or not you guess them in the right order.\n");
        System.out.println("LET'S PLAY!\n");
        System.out.println("Ok, I've selected my secret code. Try and guess it.");
    }

    /**
     * Generates a random number between 1 and 4,
     * then assigns the generated number to a color
     * (R, G, B, and Y), returning the color (char).
     * @param object : random number generator
     * @return char : color (R, G, B, Y)
     */
    public static char getRandomColor(Random randGen) {
        char letter = 'R';

        // generate random number from 0 to 3
        int random = randGen.nextInt(4);

        if (random == 1) {
            letter = 'G';
        }
        if (random == 2) {
            letter = 'B';
        }
        if (random == 3) {
            letter = 'Y';
        }
        return letter;
    }

    /**
     * Makes master code by getting 4 random colors
     * and combining them into a string. Returns code
     * unless it's "YYYY", in which case a new code
     * is generated.
     * @param object : random number generated
     * @return string : the mastercode
     */
    public static String buildMasterCode(Random randGen) {
        String masterCode;

        do {

            // get four random colors
            String firstCh = Character.toString(getRandomColor(randGen));
            String secondCh = Character.toString(getRandomColor(randGen));
            String thirdCh = Character.toString(getRandomColor(randGen));
            String fourthCh = Character.toString(getRandomColor(randGen));

            // make string
            masterCode = firstCh + secondCh + thirdCh + fourthCh;
        } while (masterCode.equals("YYYY"));

        return masterCode;
    }

    /**
     * Displays the user's statistics at the end of all rounds,
     * including rounds played, rounds won/lost, and win pct
     * @param int : number of rounds
     * @param int : number of wins
     * @return void
     */
    public static void displayStats(int numRounds, int numWins) {
        System.out.println("\nYOUR FINAL STATS:");
        System.out.println("Rounds played: " + numRounds);
        System.out.println("Won: " + numWins + " Lost: " + (numRounds - numWins));

        double winPct = ((double) (numWins) / numRounds) * 100;
        System.out.printf("Winning Pct: %.2f", winPct);
        System.out.println("%");
    }

    /**
     * Determines whether or not the color entered is valid.
     * Can be uppercase or lowercase.
     * @param char : the color
     * @return boolean : true/false, depending on if char is valid
     */
    public static boolean isValidColor(char color) {
        return (color == 'R' || color == 'G' || color == 'B' || color == 'Y' ||
                color == 'r' || color == 'g' || color == 'b' || color == 'y');
    }

    /**
     * Determines whether or not the user's guess is valid.
     * Checks if the length of the guess is 4 and calls
     * isValidColor() for each letter of the guess to see if
     * all colors are valid (but not necessarily correct).
     * @param string : user's guess
     * @return boolean : true/false, depending on if guess is valid
     */
    public static boolean isValidGuess(String userGuess) {
        char currLetter;

        // checks if length is 4
        if (userGuess.length() != 4) {
            return false;
        }

        // check if all colors are valid
        for (int i = 0; i < 4; ++i) {
            currLetter = userGuess.charAt(i);

            if (!isValidColor(currLetter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prompts user for their guess. Removes spaces from the
     * user's input. With the help of isValidGuess, keeps
     * prompting user until a valid 4-letter guess is entered.
     * @param object : scanner object
     * @param int : the round number (1-6)
     * @return string : the validated/formatted user guess
     */
    public static String getValidGuess(Scanner scnr, int roundNum) {
        System.out.print("\nEnter guess #" + roundNum + " (e.g., YBRG): ");

        String guess = scnr.nextLine().trim();

        // remove intermediate spaces
        guess = guess.replaceAll("\\s", "");

        // reprompt if invalid guess
        while (!isValidGuess(guess)) {
            System.out.println("Please enter a valid guess of correct length and colors");
            System.out.print("\nEnter guess #" + roundNum + " (e.g., YBRG): ");
            guess = scnr.nextLine().trim();
            guess = guess.replaceAll("\\s", "");
        }
        return guess;
    }

    /**
     * Counts the number of colors that the user's guess has
     * in common with the mastercode. User's guess is case
     * insensitive.
     * @param string : the mastercode
     * @param string : the user's guess
     * @return int : the number of correct colors the user has
     */
    public static int countCorrectColors(String masterCode, String userGuess) {
        int correctColorsCnt = 0;
        char currLetter;
        int redCnt = 0;
        int greenCnt = 0;
        int blueCnt = 0;
        int yellowCnt = 0;
        int redGuessCnt = 0;
        int greenGuessCnt = 0;
        int blueGuessCnt = 0;
        int yellowGuessCnt = 0;

        // tally up amount of each color in mastercode
        for (int i = 0; i < 4; ++i) {
            currLetter = masterCode.charAt(i);

            if (currLetter == 'R') {
                redCnt++;
            }
            else if (currLetter == 'G') {
                greenCnt++;
            }
            else if (currLetter == 'B') {
                blueCnt++;
            }
            else if (currLetter == 'Y') {
                yellowCnt++;
            }
        }

        // tally up amount of each color in user's guess
        for (int j = 0; j < 4; ++j) {
            currLetter = userGuess.charAt(j);
            currLetter = Character.toUpperCase(currLetter);

            if (currLetter == 'R') {
                redGuessCnt++;
            }
            else if (currLetter == 'G') {
                greenGuessCnt++;
            }
            else if (currLetter == 'B') {
                blueGuessCnt++;
            }
            else if (currLetter == 'Y') {
                yellowGuessCnt++;
            }
        }

        // tally up total correct colors
        // if the user's guess has more of a color than the mastercode,
        // increment correctColorsCnt by the mastercode's count for that color,
        // otherwise add the userguess's count for that color
        if (redGuessCnt >= redCnt) {
            correctColorsCnt += redCnt;
        }
        else {
            correctColorsCnt += redGuessCnt;
        }

        if (greenGuessCnt >= greenCnt) {
            correctColorsCnt += greenCnt;
        }
        else {
            correctColorsCnt += greenGuessCnt;
        }

        if (blueGuessCnt >= blueCnt) {
            correctColorsCnt += blueCnt;
        }
        else {
            correctColorsCnt += blueGuessCnt;
        }

        if (yellowGuessCnt >= yellowCnt) {
            correctColorsCnt += yellowCnt;
        }
        else {
            correctColorsCnt += yellowGuessCnt;
        }

        return correctColorsCnt;
    }

    /**
     * Counts the amount of colors in the correct position
     * that the user guess has. User's guess is case insensitive.
     * @param string : the mastercode
     * @param string : the user's guess
     * @return integer : number of colors in the correct position
     */
    public static int countCorrectPositions(String masterCode, String userGuess) {
        int correctPosCnt = 0;

        // iterate through each index for both mastercode and user's guess,
        // tallying up the matches
        for (int i = 0; i < 4; ++i) {
            if (masterCode.charAt(i) == Character.toUpperCase(userGuess.charAt(i))) {
                correctPosCnt++;
            }
        }
        return correctPosCnt;
    }

    /**
     * Determines whether or not the guess was correct
     * for the particular round. Prints corresponding
     * message depending on whether guess was right
     * or wrong.
     * @param string : the mastercode
     * @param string : the user's guess
     * @return boolean : true/false, depending on
     * whether or not guess was correct
     */
    public static boolean checkGuess(String masterCode, String userGuess) {
        int numCorrectColors = countCorrectColors(masterCode, userGuess);
        int numCorrectPos = countCorrectPositions(masterCode, userGuess);

        // display corresponding message depending on whether guess was right
        if (countCorrectPositions(masterCode, userGuess) == 4) {
            System.out.print("That's correct! You win this round. Bet you ");
            System.out.println("can't do it again!");
            return true;
        }
        else {
            System.out.println("You have " + numCorrectColors + " colors correct");
            System.out.println("" + numCorrectPos + " are in the correct position");
            return false;
        }
    }

    /**
     * Determines whether or not the user won the round.
     * Incorporates getValidGuess() and checkGuess() methods.
     * Prints losing message if last round was an incorrect
     * guess.
     * @param object : scanner object
     * @param string : the mastercode
     * @return boolean : true/false, depending on whether the
     * user won the round
     */
    public static boolean playOneRound(Scanner scnr, String masterCode) {
        int roundCounter = 1;
        String userGuess;
        boolean guessCheck;

        do {
            userGuess = getValidGuess(scnr, roundCounter);

            // keep track of round number
            roundCounter++;

            guessCheck = checkGuess(masterCode, userGuess);

        } while (!guessCheck && roundCounter <= NUM_OF_GUESSES);

        // if user is wrong on last round, display losing message
        if (roundCounter == NUM_OF_GUESSES + 1 && !guessCheck) {
            System.out.print("No more guesses. Sorry you lose. My sequence was ");
            System.out.println(masterCode);
            return false;
        }
        return true;
    }

    /**
     * Prompts user when the round ends, asking if they
     * would like to play again. Accepts char input (Y/N)
     * representing yes/no and returns the user's choice.
     * @param object : scanner object
     * @return character : user's choice (Y or N)
     */
    public static char getUserChoice(Scanner scnr) {
        System.out.print("Play again (Y/N)? ");
        char choice;

        choice = scnr.next().charAt(0);
        choice = Character.toUpperCase(choice);

        while (choice != 'Y' && choice != 'N') {
            System.out.println("Please enter a valid response (Y/N): ");
            choice = scnr.next().charAt(0);
            choice = Character.toUpperCase(choice);
        }
        return choice;
    }
}