package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class RoyalStraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isSameSuit(cards) && this.isRoyalStraight(cards)) {
            return new RankingResult(CardRanking.ROYAL_STRAIGHT_FLUSH);
        }
        return null;
    }
}
