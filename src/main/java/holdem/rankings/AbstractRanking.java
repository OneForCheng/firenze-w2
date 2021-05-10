package holdem.rankings;

import holdem.enums.Suit;
import holdem.models.Card;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRanking {
    public boolean isSameSuit(List<Card> cards) {
        List<Suit> distinctSuits = cards.stream().map(Card::getSuit).distinct().collect(Collectors.toList());
        return distinctSuits.size() == 1;
    }

    public abstract RankingResult parse(List<Card> cards);
}
