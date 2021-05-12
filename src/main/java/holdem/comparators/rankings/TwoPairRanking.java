package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class TwoPairRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, Long> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));

        Collection<Long> values = groupedCards.values();
        if (groupedCards.size() == 3 && values.stream().anyMatch(value -> value == 1) && values.stream().anyMatch(value -> value == 2)) {
            return new RankingResult(CardGroupRanking.TWO_PAIR);
        }
        return null;
    }
}
