package holdem.generators;

import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class Shuffled52PokerTest {
    @Test
    public void should_generate_52_shuffled_cards() {
        Shuffled52Poker generator = new Shuffled52Poker();
        Queue<Card> cards = generator.create();

        assertEquals(52, cards.size());
        List<Integer> allNumbers = IntStream.rangeClosed(CardRank.TWO.getNumber(), CardRank.ACE.getNumber()).boxed().collect(Collectors.toList());
        List<CardSuit> allCardSuits = Arrays.stream(CardSuit.values()).collect(Collectors.toList());
        IntStream.range(0, 52).forEach(i -> {
            Card card = cards.poll();
            assertTrue(allNumbers.contains(card.getNumber()));
            assertTrue(allCardSuits.contains(card.getSuit()));
        });
    }
}
