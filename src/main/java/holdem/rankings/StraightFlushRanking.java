package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class StraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isSameSuit(cards) && this.isStraightFlush(cards)) {
            return new RankingResult(CardRanking.STRAIGHT_FLUSH);
        }
        return null;
    }
}
