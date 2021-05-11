package holdem.comparings;

import holdem.enums.CardSuit;
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
                add(new Card(1, CardSuit.Club));
                add(new Card(10, CardSuit.Club));
                add(new Card(13, CardSuit.Club));
                add(new Card(12, CardSuit.Club));
                add(new Card(11, CardSuit.Club));
            }
        };
        assertEquals(0, comparing.compare(cards, cards));
    }
}
