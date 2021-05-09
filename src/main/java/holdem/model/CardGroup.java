package holdem.model;

import holdem.util.CardHelper;

import java.util.Queue;

public class CardGroup {

    private final Queue<Card> remainCards;

    public CardGroup() {
        this.remainCards = CardHelper.generate52ShuffledCards();
    }

    public Card giveOut() {
        if (this.remainCards.size() == 0) return null;

        return this.remainCards.poll();
    }

    public Queue<Card> getRemainCards() {
        return this.remainCards;
    }
}
