package holdem.comparators.comparings;

import holdem.enums.CardRank;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FullHouseComparing extends AbstractComparing {
    public int compare(List<Card> first, List<Card> second) {
        int firstWeight = this.getCardGroupWeight(first);
        int secondWeight = this.getCardGroupWeight(second);
        return Integer.compare(firstWeight, secondWeight);
    }

    private int getCardGroupWeight(List<Card> cards) {
        Map<Integer, Long> groupCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));
        int threeNumber = 0, pairNumber = 0;
        for (Integer key : groupCards.keySet()) {
            if (groupCards.get(key) == 3) {
                threeNumber = key;
            } else {
                pairNumber = key;
            }
        }

        return  threeNumber * CardRank.ACE.getNumber() + pairNumber;
    }
}
