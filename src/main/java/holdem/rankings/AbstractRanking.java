package holdem.rankings;

import holdem.enums.CardSuit;
import holdem.models.Card;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRanking implements IRanking {
    public boolean isSameSuit(List<Card> cards) {
        List<CardSuit> distinctCardSuits = cards.stream().map(Card::getSuit).distinct().collect(Collectors.toList());
        return distinctCardSuits.size() == 1;
    }

    public List<Card> getSortedCards(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparingInt(Card::getNumber)).collect(Collectors.toList());
    }

    public boolean isRoyalStraight(List<Card> cards) {
        List<Integer> numbers = cards.stream().map(Card::getNumber).collect(Collectors.toList());
        return numbers.contains(1)
                && numbers.contains(13)
                && numbers.contains(12)
                && numbers.contains(11)
                && numbers.contains(10);
    }

    public boolean isStraight(List<Card> cards) {
        List<Card> sortedCards = this.getSortedCards(cards);

        if (this.isRoyalStraight(cards)) return true;

        boolean isStraight = true;
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i).getNumber() - sortedCards.get(i+1).getNumber() != -1) {
                isStraight = false;
                break;
            }
        }
        return isStraight;
    }
}
