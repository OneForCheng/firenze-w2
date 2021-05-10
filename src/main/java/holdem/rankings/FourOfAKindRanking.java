package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.models.Card;

import java.util.List;

public class FourOfAKindRanking extends AbstractRanking {
    public RankingResult parse(List<Card> cards) {
        List<Card> sortedCards = this.getSortedCards(cards);
        int firstNumber = sortedCards.get(0).getNumber();
        int secondNumber = sortedCards.get(1).getNumber();
        int thirdNumber = sortedCards.get(2).getNumber();
        int forthNumber = sortedCards.get(3).getNumber();
        int fifthNumber = sortedCards.get(4).getNumber();

        boolean isPreviousFourEqual = firstNumber == secondNumber &&
                secondNumber == thirdNumber &&
                thirdNumber == forthNumber;
        boolean isBehindFourEqual = secondNumber == thirdNumber &&
                thirdNumber == forthNumber &&
                forthNumber == fifthNumber;
        if (isPreviousFourEqual || isBehindFourEqual) {
            return new RankingResult(CardRanking.FOUR_OF_A_KIND);
        }
        return null;
    }
}
