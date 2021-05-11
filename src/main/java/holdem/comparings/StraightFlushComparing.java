package holdem.comparings;

import holdem.models.Card;

import java.util.List;

public class StraightFlushComparing extends AbstractComparing {
    public int compare(List<Card> first, List<Card> second) {
        List<Card> sortedFirstCards = this.getSortedCards(first);
        List<Card> sortedSecondCards = this.getSortedCards(second);

        if (sortedFirstCards.get(0).getNumber() < sortedSecondCards.get(0).getNumber()) {
            return -1;
        }

        if (sortedFirstCards.get(0).getNumber() > sortedSecondCards.get(0).getNumber()) {
            return 1;
        }

        return 0;
    }
}
