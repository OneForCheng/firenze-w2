package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardSuit;
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
                add(new Card(4, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(8, CardSuit.Club));
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
                add(new Card(1, CardSuit.Club));
                add(new Card(10, CardSuit.Club));
                add(new Card(13, CardSuit.Club));
                add(new Card(12, CardSuit.Club));
                add(new Card(11, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.ROYAL_STRAIGHT_FLUSH, result.getCardRanking());
    }
}
