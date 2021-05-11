package holdem.models;

import holdem.generators.PokerGenerator;

import java.util.Queue;

public class Poker {

    private final Queue<Card> remainCards;

    public Poker(PokerGenerator generator) {
        this.remainCards = generator.create();
    }

    public Card handOut() {
        if (this.remainCards.size() == 0) return null;

        return this.remainCards.poll();
    }

    public Queue<Card> getRemainCards() {
        return this.remainCards;
    }
}
