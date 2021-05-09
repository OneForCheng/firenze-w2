package holdem.util;

import holdem.constant.Suit;
import holdem.model.Card;

import java.util.*;

public class CardGroupHelper {
    public static Queue<Card> generate52ShuffledCards() {
        List<Card> cards = new ArrayList<>(52);
        for(int i = 1; i <= 13; i++) {
            for(int j = 0; j <= 3; j++) {
                cards.add(new Card(i, Suit.values()[j]));
            }
        }
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }
}
