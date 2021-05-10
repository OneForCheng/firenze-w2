package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class FlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        if (this.isSameSuit(cards) && !this.isStraightFlush(cards)) {
            return new RankingResult(CardRanking.FLUSH);
        }
        return null;
    }

}
