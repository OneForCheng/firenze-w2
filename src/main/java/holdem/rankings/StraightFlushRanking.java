package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;

public class StraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isSameSuit(cards) && this.isStraight(cards) && !this.isRoyalStraight(cards)) {
            return new RankingResult(CardGroupRanking.STRAIGHT_FLUSH);
        }
        return null;
    }
}
