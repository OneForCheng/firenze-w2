package holdem.comparators.comparings;

import holdem.models.Card;

import java.util.List;

public class FlushComparing extends AbstractComparing {
    public int compare(List<Card> first, List<Card> second) {
        List<Card> sortedFirstCards = this.getSortedCards(first);
        List<Card> sortedSecondCards = this.getSortedCards(second);

        for(int i = 4; i >= 0; i--) {
            int result = Integer.compare(sortedFirstCards.get(i).getNumber(), sortedSecondCards.get(i).getNumber());
            if (result != 0) {
                return result;
            }
        }

        return 0;
    }
}
