package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;

import java.util.List;

public class FlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isSameSuit(cards) && !isStraight(cards)) {
            return new RankingResult(CardGroupRanking.FLUSH);
        }
        return null;
    }

}
