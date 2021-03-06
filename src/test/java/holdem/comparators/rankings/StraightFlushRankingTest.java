package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
import holdem.enums.CardSuit;
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Heart));
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Club));
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
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
                add(new Card(CardRank.KING, CardSuit.Club));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.STRAIGHT_FLUSH, result.getCardRanking());
    }
}
