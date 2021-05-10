package holdem.rankings;

import holdem.enums.Suit;
import holdem.models.Card;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRanking {
    public boolean isSameSuit(List<Card> cards) {
        List<Suit> distinctSuits = cards.stream().map(Card::getSuit).distinct().collect(Collectors.toList());
        return distinctSuits.size() == 1;
    }

    public List<Card> getSortedCards(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparingInt(Card::getNumber)).collect(Collectors.toList());
    }

    public abstract RankingResult parse(List<Card> cards);
}
