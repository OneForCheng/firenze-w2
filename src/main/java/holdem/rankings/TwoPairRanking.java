package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class TwoPairRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, List<Card>> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, toList()));

        Collection<List<Card>> values = groupedCards.values();
        if (groupedCards.size() == 3 && values.stream().anyMatch(value -> value.size() == 1) && values.stream().anyMatch(value -> value.size() == 2)) {
            return new RankingResult(CardRanking.TWO_PAIR);
        }
        return null;
    }
}
