package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class HighCardRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, Long> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, counting()));

        if (groupedCards.size() == 5 && !isSameSuit(cards) && !isStraight(cards)) {
            return new RankingResult(CardGroupRanking.HIGH_CARD);
        }
        return null;
    }
}
