package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class FourOfAKindRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        List<Card> sortedCards = this.getSortedCards(cards);
        if (sortedCards.get(0).getNumber() == sortedCards.get(1).getNumber() &&
                sortedCards.get(1).getNumber() == sortedCards.get(2).getNumber() &&
                sortedCards.get(2).getNumber() == sortedCards.get(3).getNumber()) {
            return new RankingResult(CardRanking.FOUR_OF_A_KIND);
        }
        return null;
    }
}
