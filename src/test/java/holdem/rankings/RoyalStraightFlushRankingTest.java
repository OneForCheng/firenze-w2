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
    public void should_return_null_when_cards_not_contain_royal_straight_flush() {
        RoyalStraightFlushRanking ranking = new RoyalStraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(7){
            {
                add(new Card(2, Suit.Club));
                add(new Card(3, Suit.Club));
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
    public void should_return_correct_ranking_result_when_cards_contain_royal_straight_flush() {
        RoyalStraightFlushRanking ranking = new RoyalStraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(7){
            {
                add(new Card(1, Suit.Club));
                add(new Card(3, Suit.Club));
                add(new Card(10, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(13, Suit.Club));
                add(new Card(12, Suit.Club));
                add(new Card(11, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardRanking.ROYAL_Straight_FLUSH, result.getCardRanking());
    }
}
