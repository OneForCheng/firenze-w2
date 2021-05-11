package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StraightRankingTest {
    @Test
    public void should_return_STRAIGHT_when_numbers_are_not_straight() {
        StraightRanking ranking = new StraightRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(9, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_STRAIGHT_when_numbers_are_straight_and_suits_of_five_cards_are_different() {
        StraightRanking ranking = new StraightRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, CardSuit.Club));
                add(new Card(5, CardSuit.Heart));
                add(new Card(6, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.STRAIGHT, result.getCardRanking());
    }

    @Test
    public void should_return_null_when_five_cards_are_straight_flush() {
        StraightRanking ranking = new StraightRanking();
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
    public void should_return_null_when_five_cards_are_royal_straight_flush() {
        StraightRanking ranking = new StraightRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, CardSuit.Club));
                add(new Card(10, CardSuit.Club));
                add(new Card(11, CardSuit.Club));
                add(new Card(12, CardSuit.Club));
                add(new Card(13, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }
}
