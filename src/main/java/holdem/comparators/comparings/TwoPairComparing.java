package holdem.comparators.comparings;

import holdem.enums.CardRank;
import holdem.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TwoPairComparing extends AbstractComparing {
    public int compare(List<Card> first, List<Card> second) {
        int firstWeight = this.getCardGroupWeight(first);
        int secondWeight = this.getCardGroupWeight(second);
        return Integer.compare(firstWeight, secondWeight);
    }

    private int getCardGroupWeight(List<Card> cards) {
        Map<Integer, Long> groupCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));
        int weight = 0;
        List<Integer> nums = new ArrayList<>(2);

        for (Integer key : groupCards.keySet()) {
            if (groupCards.get(key) == 2) {
                nums.add(key);
            } else {
                weight += key;
            }
        }

        if (nums.get(0) > nums.get(1)) {
            weight += nums.get(0) * CardRank.ACE.getNumber() * CardRank.ACE.getNumber() + nums.get(1)  * CardRank.ACE.getNumber();
        } else {
            weight += nums.get(0)  * CardRank.ACE.getNumber() + nums.get(1) * CardRank.ACE.getNumber() * CardRank.ACE.getNumber();
        }

        return  weight;
    }
}
