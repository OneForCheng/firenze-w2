package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;

public class StraightRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isStraight(cards) && !isSameSuit(cards)) {
            return new RankingResult(CardGroupRanking.STRAIGHT);
        }
        return null;
    }

}
