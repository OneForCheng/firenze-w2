package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
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
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.THREE, CardSuit.Diamond));
                add(new Card(CardRank.EIGHT, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.FIVE, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.FOUR, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
    public void should_return_null_when_five_cards_are_flush() {
        HighCardRanking ranking = new HighCardRanking();
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
    public void should_return_null_when_five_cards_are_straight() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.EIGHT, CardSuit.Heart));
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
    public void should_return_HIGH_CARD_when_five_cards_are_different_number_and_are_not_suit() {
        HighCardRanking ranking = new HighCardRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.THREE, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.HIGH_CARD, result.getCardRanking());
    }
}
