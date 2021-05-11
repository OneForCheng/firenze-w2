package holdem.comparings;

import holdem.models.Card;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractComparing implements IComparing {
    public List<Card> getSortedCards(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparingInt(Card::getNumber)).collect(Collectors.toList());
    }
}
