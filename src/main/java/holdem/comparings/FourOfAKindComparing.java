package holdem.comparings;

import holdem.enums.CardRank;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class FourOfAKindComparing extends AbstractComparing {
    public int compare(List<Card> first, List<Card> second) {
        int firstWeight = this.getCardGroupWeight(first);
        int secondWeight = this.getCardGroupWeight(second);
        return Integer.compare(firstWeight, secondWeight);
    }

    private int getCardGroupWeight(List<Card> cards) {
        Map<Integer, Long> groupCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));
        int fourPairNumber = 0, singleNumber = 0;
        for (Integer key : groupCards.keySet()) {
            if (groupCards.get(key) == 4) {
                fourPairNumber = key;
            } else {
                singleNumber = key;
            }
        }

        return  fourPairNumber * CardRank.ACE.getNumber() + singleNumber;
    }
}
