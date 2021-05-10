package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoyalStraightFlushRankingTest {
    @Test
    public void should_return_null_when_five_cards_is_not_royal_straight_flush() {
        RoyalStraightFlushRanking ranking = new RoyalStraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(6, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_ROYAL_STRAIGHT_FLUSH_when_five_cards_is_royal_straight_flush() {
        RoyalStraightFlushRanking ranking = new RoyalStraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(10, Suit.Club));
                add(new Card(13, Suit.Club));
                add(new Card(12, Suit.Club));
                add(new Card(11, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardRanking.ROYAL_STRAIGHT_FLUSH, result.getCardRanking());
    }
}
