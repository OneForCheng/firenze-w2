package holdem.generators;

import holdem.models.Card;

import java.util.Queue;

public interface PokerGenerator {
    Queue<Card> create();
}
