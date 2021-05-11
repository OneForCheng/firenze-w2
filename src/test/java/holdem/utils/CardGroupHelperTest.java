package holdem.utils;

import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CardGroupHelperTest {

    @Test
    public void generate52ShuffledCards() {
        Queue<Card> cards = CardGroupHelper.generate52ShuffledCards();

        assertEquals(52, cards.size());
        List<Integer> allNumbers = IntStream.rangeClosed(1, 13).boxed().collect(Collectors.toList());
        List<CardSuit> allCardSuits = Arrays.stream(CardSuit.values()).collect(Collectors.toList());
        IntStream.range(0, 52).forEach(i -> {
            Card card = cards.poll();
            assertTrue(allNumbers.contains(card.getNumber()));
            assertTrue(allCardSuits.contains(card.getSuit()));
        });
    }
}
