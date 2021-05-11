package holdem.models;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class PokerTest {
    @Test
    public void should_have_52_cards_for_a_whole_card_group() {
        Poker poker = new Poker();
        assertEquals(52, poker.getRemainCards().size());
    }

    @Test
    public void should_remain_51_cards_if_give_out_one_card() {
        Poker poker = new Poker();
        Card card = poker.giveOut();
        assertEquals(51, poker.getRemainCards().size());
        assertNotEquals(null, card);
    }

    @Test
    public void should_remain_0_cards_if_give_out_more_than_52_cards() {
        Poker poker = new Poker();
        IntStream.range(0, 52).forEach(i -> {
            poker.giveOut();
        });
        assertEquals(0, poker.getRemainCards().size());
        Card card = poker.giveOut();
        assertEquals(null, card);
    }
}
