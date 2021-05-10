package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class HighCardRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        Map<Integer, List<Card>> groupedCards = cards.stream().collect(groupingBy(Card::getNumber, toList()));

        if (groupedCards.size() == 5 && !isSameSuit(cards) && !isStraight(cards)) {
            return new RankingResult(CardRanking.HIGH_CARD);
        }
        return null;
    }
}
