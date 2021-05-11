package holdem.generators;

import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;

import java.util.*;

public class Shuffled52Poker implements PokerGenerator {
    @Override
    public Queue<Card> create() {
        List<Card> cards = new ArrayList<>(52);
        for(int i = 0; i <= 12; i++) {
            for(int j = 0; j <= 3; j++) {
                cards.add(new Card(CardRank.values()[i], CardSuit.values()[j]));
            }
        }
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }
}
