package holdem.models;

import holdem.utils.CardGroupHelper;

import java.util.Queue;

public class Poker {

    private final Queue<Card> remainCards;

    public Poker() {
        this.remainCards = CardGroupHelper.generate52ShuffledCards();
    }

    public Card giveOut() {
        if (this.remainCards.size() == 0) return null;

        return this.remainCards.poll();
    }

    public Queue<Card> getRemainCards() {
        return this.remainCards;
    }
}
