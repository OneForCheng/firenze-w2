package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class StraightRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isStraight(cards)) {
            return new RankingResult(CardRanking.STRAIGHT);
        }
        return null;
    }

}
