package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ThreeOfAKindRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, List<Card>> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, toList()));

        if (groupedCards.size() == 3 && (groupedCards.values().stream().anyMatch(value -> value.size() == 3))) {
            return new RankingResult(CardRanking.THREE_OF_A_KIND);
        }
        return null;
    }
}
