package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ThreeOfAKindRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, Long> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));

        if (groupedCards.size() == 3 && (groupedCards.values().stream().anyMatch(value -> value == 3))) {
            return new RankingResult(CardGroupRanking.THREE_OF_A_KIND);
        }
        return null;
    }
}
