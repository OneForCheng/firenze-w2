package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;
import java.util.stream.Collectors;


public class StraightFlushRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        RankingResult result = null;
        if (this.isSameSuit(cards)) {
            List<Integer> sortedNumbers = cards.stream().map(Card::getNumber).sorted().collect(Collectors.toList());
            boolean isStraightFlush = true;
            for (int i = 0; i < sortedNumbers.size() - 1; i++) {
                if (sortedNumbers.get(i) - sortedNumbers.get(i+1) != -1) {
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
