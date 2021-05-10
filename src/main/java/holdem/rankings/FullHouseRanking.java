package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class FullHouseRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        List<Card> sortedCards = this.getSortedCards(cards);

        boolean isBefore3After2 = sortedCards.get(0).getNumber() == sortedCards.get(1).getNumber() &&
                sortedCards.get(1).getNumber() == sortedCards.get(2).getNumber() &&
                sortedCards.get(3).getNumber() == sortedCards.get(4).getNumber();

        boolean isBefore2After3 = sortedCards.get(0).getNumber() == sortedCards.get(1).getNumber() &&
                sortedCards.get(2).getNumber() == sortedCards.get(3).getNumber() &&
                sortedCards.get(3).getNumber() == sortedCards.get(4).getNumber();

        if (isBefore3After2 || isBefore2After3) {
            return new RankingResult(CardRanking.FULL_HOUSE);
        }
        return null;
    }
}
