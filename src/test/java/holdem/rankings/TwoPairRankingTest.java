package holdem.rankings;

import holdem.enums.CardRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwoPairRankingTest {
    @Test
    public void should_return_null_when_five_cards_contain_three_same_number() {
        TwoPairRanking ranking = new TwoPairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(8, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_only_contain_one_pair_same_number() {
        TwoPairRanking ranking = new TwoPairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(2, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(8, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_TWO_PAIR_when_five_cards_contain_two_1_and_two_8_and_one_4() {
        TwoPairRanking ranking = new TwoPairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(1, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(8, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardRanking.TWO_PAIR, result.getCardRanking());
    }

    @Test
    public void should_return_TWO_PAIR_when_five_cards_contain_two_4_and_two_8_and_one_1() {
        TwoPairRanking ranking = new TwoPairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(8, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardRanking.TWO_PAIR, result.getCardRanking());
    }

    @Test
    public void should_return_TWO_PAIR_when_five_cards_contain_two_1_and_two_4_and_one_8() {
        TwoPairRanking ranking = new TwoPairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(1, Suit.Club));
                add(new Card(1, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(4, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardRanking.TWO_PAIR, result.getCardRanking());
    }
}
