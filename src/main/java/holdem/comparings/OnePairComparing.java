package holdem.comparings;

import holdem.enums.CardRank;
import holdem.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OnePairComparing extends AbstractComparing {
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
                weight += key * Math.pow(CardRank.ACE.getNumber(), 3);
            } else {
                nums.add(key);
            }
        }

        List<Integer> sortedNums = nums.stream().sorted().collect(Collectors.toList());

        weight += sortedNums.get(0) + sortedNums.get(1) * CardRank.ACE.getNumber() +  sortedNums.get(2) * CardRank.ACE.getNumber() * CardRank.ACE.getNumber();

        return  weight;
    }
}
