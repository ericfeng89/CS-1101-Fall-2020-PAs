// **********************************************************************************************
// File Name: Player.java
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
//                      This class will represent a player in the game (there are two total).
//************************************************************************************************

public class Player {

    private Card cardHold;
    private int score;
    private String name;
    private int cut_off;
    private int cntCard;
    private int pNb;

    private static int pNbStatic = 0;

    /**
     * Default constructor that initializes the properties
     * values. Score and # of cards drawn initialized to 0.
     * The rest are initialized to static variables.
     */
    public Player() {
        /* The first instance of the class should have a pNb of 0
         and the second should have a pNb of 1. To achieve this,
         we set the pNb to a static variable that has an initial
         value of 0, which we change to 1 after the first player
         is created.
         */
        this.pNb = pNbStatic;
        pNbStatic = 1;
        this.score = 0;
        this.name = GameData.PLAYER_NAME[pNb];
        this.cut_off = GameData.CUT_OFF[pNb];
        this.cntCard = 0;
    }

    /**
     * Returns the last card drawn by the player.
     * @return Card : last card drawn
     */
    public Card getCardHold() {
        return cardHold;
    }

    /**
     * Returns the player's current score.
     * @return int : their score
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets the player's score to 0.
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Increments the score by a given value.
     * @param numToAdd : amount to increase score by
     */
    public void increaseScore(int numToAdd) {
        score += numToAdd;
    }

    /**
     * Returns the player's cut-off value.
     * @return int : cut-off value
     */
    public int getCut_off() {
        return cut_off;
    }

    /**
     * Returns the player's number (0 or 1).
     * @return int : player number
     */
    public int getPNb() {
        return pNb;
    }

    /**
     * Returns the number of cards the player drew.
     * @return int : number of cards
     */
    public int getCntCard() {
        return cntCard;
    }

    /**
     * Returns the player's name.
     * @return String : name
     */
    public String toString() {
        return name;
    }

    /**
     * This method will take a turn for the player in the game.
     * The player can only draw a certain number of cards (3 in
     * this case). If the card's value exceeds the cut-off value
     * the card will be kept and returned, else the player will
     * re-draw (given that they still can).
     * @param deckObj : deck object (deck of cards)
     * @return Card : the card that was kept
     */
    public Card takeTurn(Deck deckObj) {
        cntCard = 0;

        // keep drawing cards until cut_off or MAX_DRAW reached
        do {
            cardHold = deckObj.draw();

            // increment # of cards drawn
            cntCard++;
        } while (cardHold.value() < cut_off && cntCard < GameData.MAX_DRAW);

        return cardHold;
    }
}
