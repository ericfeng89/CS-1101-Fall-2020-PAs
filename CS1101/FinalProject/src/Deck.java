// **********************************************************************************************
// File Name: Deck.java
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
//                      This class will represent a deck of playing cards to be used in the game.
//************************************************************************************************

public class Deck {

    private Card[] deck;
    private int topCardIndex = -1;
    private int rndNb = GameData.rndNb;

    /**
     * Default constructor that will create the array of
     * Card objects and create each card. It will add wildcard
     * replacements, shuffle the deck, and set the index
     * of the top card to 0.
     */
    public Deck() {
        deck = new Card[GameData.NB_CARDS];

        // make deck
        for (int i = 0; i < GameData.arrSuit.length - 1; ++i) {
            for (int j = 0; j < GameData.arrRank.length - 1; ++j) {
                deck[i * (GameData.arrRank.length - 1) + j] = new Card(j + 1, i + 1);
            }
        }

        // add wildcard replacements
        addWild(deck);

        // shuffle deck
        shuffle(deck);

        topCardIndex = 0;
    }

    /**
     * Replaces two random cards in the deck with wild cards.
     * @param deck : the deck of cards
     */
    public void addWild(Card[] deck) {
        for (int i = 0; i < GameData.WILDCARD_CNT; ++i) {
            // get random number using the last one
            rndNb = GameData.getRandom(rndNb);

            // replace the card at the current index with a wild card
            deck[rndNb % 52] = new WildCard(deck[rndNb % 52].toString());
        }
    }

    /**
     * Shuffles the deck of cards.
     * @param deck : the deck of cards
     */
    public void shuffle(Card[] deck) {
        for (int i = 0; i < GameData.NB_CARDS; ++i) {
            // get random number using the last one
            rndNb = GameData.getRandom(rndNb);

            // replace the card at index i with the one at a random index
            Card temp = deck[i];
            deck[i] = deck[rndNb % 52];
            deck[rndNb % 52] = temp;
        }
    }

    /**
     * Returns how many cards are still remaining in the deck to be dealt.
     * @return int : number of cards remaining
     */
    public int cardsLeft() {
        return GameData.NB_CARDS - topCardIndex;
    }

    /**
     * This method returns the card at the topCardIndex and
     * updates the topCardIndex value. If the deck is empty,
     * re-shuffle and reset the topCardIndex.
     * @return card : the card at the top
     */
    public Card draw() {

        // if empty deck, reshuffle and set topCardIndex to 0
        if (topCardIndex >= 52) {
            shuffle(deck);
            topCardIndex = 0;
        }
        Card topCard = deck[topCardIndex];

        // update topCardIndex
        topCardIndex++;

        return topCard;
    }
}
