package holdem.comparators.rankings;

import holdem.enums.CardRank;
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
        List<CardRank> cardRanks = cards.stream().map(Card::getRank).collect(Collectors.toList());
        return cardRanks.contains(CardRank.ACE)
                && cardRanks.contains(CardRank.KING)
                && cardRanks.contains(CardRank.QUEUE)
                && cardRanks.contains(CardRank.JACK)
                && cardRanks.contains(CardRank.TEN);
    }

    public boolean isMinStraight(List<Card> cards) {
        List<CardRank> cardRanks = cards.stream().map(Card::getRank).collect(Collectors.toList());
        return cardRanks.contains(CardRank.ACE)
                && cardRanks.contains(CardRank.TWO)
                && cardRanks.contains(CardRank.THREE)
                && cardRanks.contains(CardRank.FOUR)
                && cardRanks.contains(CardRank.FIVE);
    }

    public boolean isStraight(List<Card> cards) {
        List<Card> sortedCards = this.getSortedCards(cards);

        if (this.isRoyalStraight(cards) || this.isMinStraight(cards)) return true;

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
