package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class StraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        RankingResult result = null;
        if (this.isSameSuit(cards)) {
            List<Card> sortedCards = this.getSortedCards(cards);
            boolean isStraightFlush = true;
            for (int i = 0; i < sortedCards.size() - 1; i++) {
                if (sortedCards.get(i).getNumber() - sortedCards.get(i+1).getNumber() != -1) {
                    isStraightFlush = false;
                    break;
                }
            }
            if (isStraightFlush) {
                result = new RankingResult(CardRanking.STRAIGHT_FLUSH);
            }

        }
        return result;
    }
}
