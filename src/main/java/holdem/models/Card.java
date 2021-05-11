package holdem.models;

import holdem.enums.CardSuit;

public class Card {
    private int number;
    private CardSuit cardSuit;

    public Card(int number, CardSuit cardSuit) {
        this.number = number;
        this.cardSuit = cardSuit;
    }

    public int getNumber() {
        return number;
    }

    public CardSuit getSuit() {
        return cardSuit;
    }
}
