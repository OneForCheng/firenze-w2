package holdem.models;

import holdem.enums.CardRank;
import holdem.enums.CardSuit;

public class Card {
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public int getNumber() {
        return this.cardRank.getNumber();
    }

    public CardSuit getSuit() {
        return cardSuit;
    }
}
