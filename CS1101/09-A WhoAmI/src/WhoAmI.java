// **********************************************************************************************
// Program Name: WhoAmI.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 10/27/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will store questions that are asked by the user and uses the
//                      information learned to try and guess the person that they are thinking of.
//************************************************************************************************

import java.util.Scanner;

public class WhoAmI {

    static final int T_MAX = 100;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        String[] qt = new String[T_MAX];

        // initialize first few array values and make the others empty strings
        for (int i = 0; i < T_MAX; i++){
            qt[i] = "";
        }
        qt[0] = "\n*** WhoAmI guessing game. ***\n";
        qt[0] += "Think of a person and answer questions by Y or N.";
        qt[1] = "Is the person alive ? ";
        qt[2] = "Queen Elizabeth of England.";
        qt[3] = "Adam";

        askQuestion(scnr, qt);
    }

    /**
     * Asks user the right question depending on whether the user
     * replies Y or N. If there are no more questions to ask and program
     * makes an incorrect guess, calls the function updateQuestion().
     * @param object: scanner object
     * @param array : array of stored questions/people
     * @return void
     */
    public static void askQuestion (Scanner scnr, String [] qt) {
        int currIndex = 1;
        char questionMark;
        String guess;
        char yesOrNo;
        char playAgain;
        String newName;

        // print welcome message
        System.out.println(qt[0]);

        // while the current index is not an empty string, continue
        while (!qt[currIndex].equals("")) {
            questionMark = qt[currIndex].charAt(qt[currIndex].length() - 2);

            // case 1: the current index has a question
            if (questionMark == '?') {
                yesOrNo = readYN(scnr, qt[currIndex]);

                if (yesOrNo == 'Y' && !qt[2 * currIndex].equals("")) {
                    currIndex = 2 * currIndex;
                }
                else if (yesOrNo == 'N' && !qt[2 * currIndex + 1].equals("")){
                    currIndex = 2 * currIndex + 1;
                }
            }

            // case 2: the current index has a person
            else {
                guess = "I suggest: " + qt[currIndex] + "\nDid I guess correctly ? ";
                yesOrNo = readYN(scnr, guess);

                if (yesOrNo == 'Y') {
                    System.out.println("Person found !");

                    currIndex = 1;

                    playAgain = readYN(scnr, "Do you want to play again ? ");

                    // exit program if user chooses to quit
                    if (playAgain == 'N') {
                        System.exit(0);
                    }

                    System.out.println(qt[0]);
                }
                else if (yesOrNo == 'N' && !qt[2 * currIndex + 1].equals("")) {
                    currIndex = 2 * currIndex + 1;
                }
                else {
                    System.out.print("Who are you thinking of ? ");
                    newName = scnr.nextLine();

                    // update the database with newly gained info
                    updateQuestion(scnr, qt, currIndex, newName);
                    currIndex = 1;

                    playAgain = readYN(scnr, "Do you want to play again ? ");

                    if (playAgain == 'N') {
                        System.exit(0);
                    }

                    System.out.println(qt[0]);
                }
            }

            // if there is insufficient space left in array, prompt user and terminate
            if (currIndex >= T_MAX) {
                System.out.print("there is insufficient space to store the");
                System.out.println("new question and associated answers.");
                System.exit(0);
            }
        }
    }

    /**
     * Called when the program can't guess the right person.
     * Prompts user to enter a question that will guide them
     * to guess the right answer next round. Stores the data
     * in a formatted manner. Depending on whether the user's
     * reply to the discriminating question will be Y or N,
     * switch correct and wrong data storage locations.
     * @param object : scanner object
     * @param array : array of stored questions/people
     * @param integer : index of where disc. question will be stored
     * @param String : name of the new person to be stored
     * @return void
     */
    public static void updateQuestion (Scanner scnr, String [] qt,
                                       int ind, String newName) {
        char yesOrNo;
        String temp;
        int qTextLength;

        System.out.print("What would be a discriminating question to ");
        System.out.println("ask such that the answer is: ");
        System.out.print("\"" + newName + "\" ? ");

        // if there is insufficient space left in array, prompt user and terminate
        if (2 * ind + 1 >= T_MAX) {
            System.out.print("there is insufficient space to store the");
            System.out.println("new question and associated answers.");
            System.exit(0);
        }

        // if answer is Y, 2*ind will contain next question
        // if N, 2*ind + 1 will contain next question
        qt[2 * ind] = newName;
        qt[2 * ind + 1] = qt[ind];

        qt[ind] = scnr.nextLine();

        System.out.println("Ok. At the question: \" " + qt[ind] + " ? \"");

        yesOrNo = readYN(scnr, "Would you reply Y or N to get the answer " +
                "\"" + newName + "\" ? ");

        // switch storage locations if user would reply "N" to get answer
        if (yesOrNo == 'N') {
            temp = qt[2 * ind + 1];
            qt[2 * ind + 1] = qt[2 * ind];
            qt[2 * ind] = temp;
        }

        qTextLength = qt[ind].length();

        // add question mark and spaces if needed
        if (qt[ind].charAt(qTextLength - 3) != ' ') {
            qt[ind] += " ";
        }
        if (qt[ind].charAt(qTextLength - 1) == ' ') {
            if (qt[ind].charAt(qTextLength - 2) != '?') {
                qt[ind] += "? ";
            }
        }
        else if (qt[ind].charAt(qTextLength - 1) == '?') {
            qt[ind] += " ";
        }
        else {
            qt[ind] += "? ";
        }
    }

    /**
     * Prompts the user to answer a yes/no question,
     * validates response to see if Y or N (case insensitive)
     * entered, returns character
     * @param object : scanner object
     * @param string : yes/no question to be asked
     * @return char : Y or N
     */
    public static char readYN (Scanner scnr, String question) {
        String inputString;
        char yesOrNo;

        System.out.print(question);

        inputString = scnr.nextLine();

        // reprompt if necessary
        while (inputString.isBlank() || inputString.isEmpty() ||
                (inputString.charAt(0) != 'y' && inputString.charAt(0) != 'Y' &&
                        inputString.charAt(0) != 'n' && inputString.charAt(0) != 'N')) {
            System.out.println("Please enter Y or N");
            System.out.print(question);
            inputString = scnr.nextLine();
        }

        yesOrNo = inputString.charAt(0);
        yesOrNo = Character.toUpperCase(yesOrNo);

        return yesOrNo;
    }

    /**
     * Premade function to be used for unit testing.
     * @param array : array of questions/people
     */
    public static void populateQuestion (String [] qt) {
        for (int i = 0; i < T_MAX; i++){
            qt[i] = "";
        }
        qt[0] = "\n*** WhoAmI guessing game. ***\n";
        qt[0] += "Think of a person and answer questions by Y or N.";
        qt[1] = "Is the person alive ? ";
        qt[2] = "Queen Elizabeth of England.";
        qt[3] = "Adam";
    }
}