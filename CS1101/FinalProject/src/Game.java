// **********************************************************************************************
// File Name: Game.java
// Name: Eric Feng
// VUnetID: fengep
// Email: eric.p.feng@vanderbilt.edu
// Class: CS 1101 - Vanderbilt University
// Section: 002 MWF 10:20am
// Date: 12/5/2020
// Honor statement: I attest that I understand the honor code for this class and have neither given
//                  nor received any unauthorized aid on this assignment.
//
// Program Description: This program will simulate a simple card game between two players. The
//                      game will use a standard deck of 52 cards. These cards will be drawn and
//                      points will be given based on which card has the higher point value.
//
//                      This class contains the main method and will simulate a game between the
//                      two players.
//************************************************************************************************

public class Game {

    public static void main(String args[]) {
        Deck deck = new Deck();
        Player player1 = new Player();
        Player player2 = new Player();

        // simulate game
        printHeader(player1, player2);
        playGame(player1, player2, deck);
        printFinalScore(player1, player2);
    }

    /**
     * Prints the header of the game, including the names of
     * the players and the number of points needed to win.
     * @param player1
     * @param player2
     */
    public static void printHeader(Player player1, Player player2) {
        System.out.println("Match between " + player1.toString() + " and "
                + player2.toString());

        System.out.println("The winner must get more than " + GameData.POINTS_TO_WIN
                + " points.");
    }

    /**
     * Simulates a game, printing everything except
     * the game introduction and the final score.
     * @param player1
     * @param player2
     * @param deck : the deck of cards
     */
    public static void playGame(Player player1, Player player2, Deck deck) {
        int p1TotalPts = 0;
        int p2TotalPts = 0;
        int turnCnt = 0;

        // keep playing until a player passes 100 points
        while (player1.getScore() <= 100 && player2.getScore() <= 100) {
            // increment turn number
            turnCnt++;

            System.out.println("Turn #" + turnCnt);

            Card p1Card = drawAndKeep(player1, deck);
            Card p2Card = drawAndKeep(player2, deck);

            int difference = p1Card.value() - p2Card.value();

            calculateRoundScore(difference, p1TotalPts, p2TotalPts, player1, player2, p1Card);
            printCurrentScores(player1, player2);
        }
    }

    /**
     * Returns the card kept by the player and prints
     * information about the card they drew.
     * @param player
     * @param deck
     * @return the kept card
     */
    public static Card drawAndKeep(Player player, Deck deck) {
        Card cardKept = player.takeTurn(deck);

        System.out.println("\t" + player.toString() + "[" + player.getCut_off() +
                "] drew " + player.getCntCard() + " card(s) and kept " + cardKept.toString()
                + " which is " + cardKept.value() + " points");

        return cardKept;
    }

    /**
     * Prints information about the outcome of the round, including
     * which player who scored the points for that particular draw,
     * and how many points they scored.
     * @param difference : player1's score minus player2's score
     * @param p1TotalPts : cumulative points for player 1
     * @param p2TotalPts : cumulative points for player 2
     * @param player1
     * @param player2
     * @param p1Card : most recent card drawn by player 1
     */
    public static void calculateRoundScore(int difference, int p1TotalPts, int p2TotalPts,
                                           Player player1, Player player2, Card p1Card) {
        if (difference > 0) {
            player1.increaseScore(difference);

            // increment total points
            p1TotalPts += difference;
            System.out.println("\t\tThus " + player1.toString() + " scores " +
                    difference + " points");
        } else if (difference < 0) {
            player2.increaseScore(-difference);

            // subtract negative difference to increment total points
            p2TotalPts -= difference;
            System.out.println("\t\tThus " + player2.toString() + " scores " +
                    (-difference) + " points");
        } else {
            // print special message the round is a tie
            System.out.println("Pure insanity! Both " + player1.toString() + " and "
                    + player2.toString() + "tied this round with " + p1Card.value()
                    + "points.");
        }
    }

    /**
     * Prints the current scores of both players.
     * @param player1
     * @param player2
     */
    public static void printCurrentScores(Player player1, Player player2) {
        System.out.println("Scores:: " + player1.toString() + " has "
                + player1.getScore() + " points and " + player2.toString()
                + " has " + player2.getScore() + " points");
    }

    /**
     * Prints the winner of the game and their final score.
     * @param player1
     * @param player2
     */
    public static void printFinalScore(Player player1, Player player2) {
        System.out.print("The winner is ");

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.toString() + " with " + player1.getScore() + " points");
        } else {
            System.out.println(player2.toString() + " with " + player2.getScore() + " points");
        }
    }
}
