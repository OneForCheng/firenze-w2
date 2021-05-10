package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StraightFlushRankingTest {
    @Test
    public void should_return_null_when_suits_of_five_cards_are_different() {
        StraightFlushRanking ranking = new StraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(5, Suit.Heart));
                add(new Card(6, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_suits_are_same_but_numbers_are_not_straight() {
        StraightFlushRanking ranking = new StraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(6, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(9, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_are_royal_straight_flush() {
        StraightFlushRanking ranking = new StraightFlushRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(10, Suit.Club));
                add(new Card(11, Suit.Club));
                add(new Card(12, Suit.Club));
                add(new Card(13, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_STRAIGHT_FLUSH_when_five_cards_are_straight_flush() {
        StraightFlushRanking ranking = new StraightFlushRanking();
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
        assertEquals(CardRanking.STRAIGHT_FLUSH, result.getCardRanking());
    }
}
