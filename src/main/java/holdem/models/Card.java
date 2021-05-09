package holdem.models;

import holdem.enums.Suit;

public class Card {
    private int number;
    private Suit suit;

    public Card(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }
}
