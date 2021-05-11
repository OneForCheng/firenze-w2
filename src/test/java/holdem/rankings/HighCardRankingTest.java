package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HighCardRankingTest {
    @Test
    public void should_return_null_when_five_cards_contain_two_same_numbers() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, CardSuit.Club));
                add(new Card(2, CardSuit.Heart));
                add(new Card(3, CardSuit.Diamond));
                add(new Card(8, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_contain_three_same_numbers() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, CardSuit.Club));
                add(new Card(4, CardSuit.Heart));
                add(new Card(4, CardSuit.Diamond));
                add(new Card(5, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_contain_four_same_numbers() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, CardSuit.Club));
                add(new Card(4, CardSuit.Heart));
                add(new Card(4, CardSuit.Diamond));
                add(new Card(4, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_are_straight_flush() {
        HighCardRanking ranking = new HighCardRanking();
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
    public void should_return_null_when_five_cards_are_flush() {
        HighCardRanking ranking = new HighCardRanking();
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
    public void should_return_null_when_five_cards_are_straight() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(8, CardSuit.Heart));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }


    @Test
    public void should_return_null_when_five_cards_are_royal_straight_flush() {
        HighCardRanking ranking = new HighCardRanking();
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

    @Test
    public void should_return_HIGH_CARD_when_five_cards_are_different_number_and_are_not_suit() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, CardSuit.Club));
                add(new Card(2, CardSuit.Heart));
                add(new Card(4, CardSuit.Diamond));
                add(new Card(3, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.HIGH_CARD, result.getCardRanking());
    }
}
