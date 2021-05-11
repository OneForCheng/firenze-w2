package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class FourOfAKindRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, List<Card>> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, toList()));

        if (groupedCards.size() == 2 && (groupedCards.values().stream().anyMatch(value -> value.size() == 4))) {
            return new RankingResult(CardGroupRanking.FOUR_OF_A_KIND);
        }
        return null;
    }
}
