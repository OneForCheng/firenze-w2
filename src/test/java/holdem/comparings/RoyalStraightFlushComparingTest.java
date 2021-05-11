package holdem.comparings;

import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoyalStraightFlushComparingTest {
    @Test
    public void should_return_0_for_royal_straight_flush_comparing() {
        IComparing comparing = new RoyalStraightFlushComparing();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(10, Suit.Club));
                add(new Card(13, Suit.Club));
                add(new Card(12, Suit.Club));
                add(new Card(11, Suit.Club));
            }
        };
        assertEquals(0, comparing.compare(cards, cards));
    }
}
