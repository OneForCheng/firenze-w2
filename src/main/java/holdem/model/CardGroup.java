package holdem.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CardGroup {

    private final Queue<Card> remainCards;

    public CardGroup() {
        this.remainCards = new LinkedList<>();
        Arrays.stream(new int[52]).forEach(i -> {
            remainCards.add(new Card());
        });
    }

    public Card giveOut() {
        if (this.remainCards.size() == 0) return null;

        return this.remainCards.poll();
    }

    public Queue<Card> getRemainCards() {
        return this.remainCards;
    }
}
