// **********************************************************************************************
// File Name: GameData.java
// Name: CS1101 Instructor
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
//                      This class will contain constants and random number function used in the
//                      game.
//************************************************************************************************
public final class GameData {

    public static int MAX_DRAW = 3;
    public static int POINTS_TO_WIN = 100;
    public static int WILDCARD_VALUE = 55;
    public static int WILDCARD_CNT = 2;
    public static final int NB_CARDS = 52;

    public static final char[] arrSuit = new char [] {' ', 'C', 'D', 'H', 'S'};
    public static final char[] arrRank = new char [] {' ', 'A', '2', '3', '4', '5',
            '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

    /*
        Data for players
     */
    static String [] PLAYER_NAME = {"John", "Carolyn"};
    static int [] CUT_OFF = {12, 11};

    // Data for randomizer
    private static final long MODULUS = 2147483647;
    private static final long SEED = 314159;
    public static int rndNb = (int) SEED;

    // Pseudo Random number generator
    public static int getRandom(int rnd) {
        long multiplier = 16807;
        return (int) ((multiplier * rnd) % MODULUS);
    }
}
