package holdem.models;

import holdem.utils.CardGroupHelper;

import java.util.Queue;

public class CardGroup {

    private final Queue<Card> remainCards;

    public CardGroup() {
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
