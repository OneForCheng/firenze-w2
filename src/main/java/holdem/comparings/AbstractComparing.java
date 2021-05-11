package holdem.comparings;

import holdem.enums.CardRank;
import holdem.models.Card;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractComparing implements IComparing {
    public List<Card> getSortedCards(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparingInt(Card::getNumber)).collect(Collectors.toList());
    }

    public boolean isMinStraight(List<Card> cards) {
        List<CardRank> cardRanks = cards.stream().map(Card::getRank).collect(Collectors.toList());
        return cardRanks.contains(CardRank.ACE)
                && cardRanks.contains(CardRank.TWO)
                && cardRanks.contains(CardRank.THREE)
                && cardRanks.contains(CardRank.FOUR)
                && cardRanks.contains(CardRank.FIVE);
    }
}
