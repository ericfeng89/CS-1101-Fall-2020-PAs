// **********************************************************************************************
// File Name: Card.java
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
//                      This class will represent a playing card with a suit and rank.
//************************************************************************************************

public class Card {
    /**
     * rank, suit, and text (ex. "2S" for 2 of spades) of a card
     */
    private int rankVal;
    private int suitVal;
    private String cardText;

    /**
     * Constructor that takes in the rank and suit of the
     * card, verifies if they are valid values, and initializes
     * the rank, suit, and cardText variables. Throws exception
     * if arguments are invalid.
     * @param rank : the rank of the card (1 to 13)
     * @param suit : the suit of the card (1 to 4)
     */
    public Card(int rank, int suit) {
        if (rank < 1 || rank > 13 || suit < 1 || suit > 4) {
            throw new IllegalArgumentException();
        }
        this.rankVal = rank;
        this.suitVal = suit;

        // cardText is the rank and suit (both char form) added together as strings
        this.cardText = Character.toString(getRank()) + Character.toString(getSuit());
    }

    /**
     * Constructor that takes in the card's text (rank + suit),
     * verifies that the text is valid, and initializes the rank,
     * suit, and cardText. Throws exception if argument is invalid.
     * @param cardText
     */
    public Card(String cardText) {
        if (cardText.length() != 2) {
            throw new IllegalArgumentException();
        }
        this.suitVal = -1;
        this.rankVal = -1;

        // start i and j at 1 because space (arrSuit[0]) is not acceptable
        // if character matches value in suit/rank array, set suitVal/rankVal to array index
        for (int i = 1; i < GameData.arrSuit.length; ++i) {
            if (cardText.charAt(1) == GameData.arrSuit[i]) {
                this.suitVal = i;
            }
        }
        for (int j = 1; j < GameData.arrRank.length; ++j) {
            if (cardText.charAt(0) == GameData.arrRank[j]) {
                this.rankVal = j;
            }
        }
        // throw exception if suitVal or rankVal are still at initial values (thus invalid)
        if (this.suitVal == -1 || this.rankVal == -1) {
            throw new IllegalArgumentException();
        }
        this.cardText = cardText;
    }

    /**
     * Gets the first letter of the card's suit
     * @return char : the first letter
     */
    public char getSuit() {
        return GameData.arrSuit[suitVal];
    }

    /**
     * Gets the rank of the card
     * @return char : the rank
     */
    public char getRank() {
        return GameData.arrRank[rankVal];
    }

    /**
     * Gets the card text (2 character string)
     * @return String : the card text
     */
    public String toString() {
        return cardText;
    }

    /**
     * Accepts any type of object and returns true if
     * the object is of type Card and if the rank and
     * suit values are equal in the two objects.
     * @param obj : an unspecified object
     * @return boolean : whether or not the objects are equal
     */
    public boolean equals(Object obj) {
        // only evaluate if the parameter is Card object, else return false
        if (obj instanceof Card) {
            Card card = (Card) obj;
            return card.rankVal == rankVal && card.suitVal == suitVal;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the value of the card as an integer
     * @return int : the rank times the suit
     */
    public int value() {
        return rankVal * suitVal;
    }

    /**
     * Added so that zyBooks can compile
     */
    @Override
    public int hashCode() {
        return rankVal * suitVal;
    }
}