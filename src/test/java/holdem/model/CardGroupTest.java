package holdem.model;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CardGroupTest {
    @Test
    public void should_have_52_cards_for_a_whole_card_group() {
        CardGroup cardGroup = new CardGroup();
        assertEquals(52, cardGroup.getRemainCards().size());
    }

    @Test
    public void should_remain_51_cards_if_give_out_one_card() {
        CardGroup cardGroup = new CardGroup();
        Card card = cardGroup.giveOut();
        assertEquals(51, cardGroup.getRemainCards().size());
        assertNotEquals(null, card);
    }

    @Test
    public void should_remain_0_cards_if_give_out_more_than_52_cards() {
        CardGroup cardGroup = new CardGroup();
        IntStream.range(0, 52).forEach(i -> {
            cardGroup.giveOut();
        });
        assertEquals(0, cardGroup.getRemainCards().size());
        Card card = cardGroup.giveOut();
        assertEquals(null, card);
    }
}
