package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.KING, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.ROYAL_STRAIGHT_FLUSH, result.getCardRanking());
    }
}
