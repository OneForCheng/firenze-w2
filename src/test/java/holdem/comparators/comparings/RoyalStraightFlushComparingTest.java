package holdem.comparators.comparings;

import holdem.enums.CardRank;
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
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.KING, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
            }
        };
        assertEquals(0, comparing.compare(cards, cards));
    }
}
