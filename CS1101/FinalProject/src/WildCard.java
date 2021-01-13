// **********************************************************************************************
// File Name: WildCard.java
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
//                      This class will be derived from the Card class and will represent a
//                      wild card, a playing card that has a special value.
//************************************************************************************************

public class WildCard extends Card {
    /**
     * Constructor that takes in the card's text (rank + suit),
     * verifies that the text is valid, and initializes the rank,
     * suit, and cardText. Throws exception if argument is invalid.
     * @param cardText
     */
    public WildCard(String cardText) {

        // call parent class constructor, which is identical to this one
        // super keyword to avoid repeating code
        super(cardText);
    }

    /**
     * Overrides Card.toString() method, returns a 2 character
     * string that represents both the rank and suit of the
     * card followed by the string (wild).
     * @return String : rank, suit, and "(wild)"
     */
    @Override
    public String toString() {
        return super.toString() + "(wild)";
    }

    /**
     * Overrides Card.value() method, returns the value of
     * the wild card as an integer from GameData.
     * @return int : value of the wild card
     */
    @Override
    public int value() {
        return GameData.WILDCARD_VALUE;
    }
}
