package holdem.models;

import holdem.generators.PokerGenerator;
import holdem.generators.Shuffled52Poker;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class PokerTest {
    @Test
    public void should_have_52_cards_for_a_whole_card_group() {
        PokerGenerator generator = new Shuffled52Poker();
        Poker poker = new Poker(generator);
        assertEquals(52, poker.getRemainCards().size());
    }

    @Test
    public void should_remain_51_cards_if_hand_out_one_card() {
        PokerGenerator generator = new Shuffled52Poker();
        Poker poker = new Poker(generator);
        Card card = poker.handOut();
        assertEquals(51, poker.getRemainCards().size());
        assertNotEquals(null, card);
    }

    @Test
    public void should_remain_0_cards_if_hand_out_more_than_52_cards() {
        PokerGenerator generator = new Shuffled52Poker();
        Poker poker = new Poker(generator);
        IntStream.range(0, 52).forEach(i -> {
            poker.handOut();
        });
        assertEquals(0, poker.getRemainCards().size());
        Card card = poker.handOut();
        assertEquals(null, card);
    }
}
