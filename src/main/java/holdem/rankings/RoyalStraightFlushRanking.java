package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;
import java.util.stream.Collectors;

public class RoyalStraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        RankingResult result = null;
        if (this.isSameSuit(cards)) {
            List<Integer> numbers = cards.stream().map(Card::getNumber).collect(Collectors.toList());
            if (numbers.contains(1)
                    && numbers.contains(13)
                    && numbers.contains(12)
                    && numbers.contains(11)
                    && numbers.contains(10)) {
                result = new RankingResult(CardRanking.ROYAL_Straight_FLUSH);
            }
        }
        return result;
    }
}
