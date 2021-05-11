package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FourOfAKindRankingTest {
    @Test
    public void should_return_null_when_five_cards_not_contain_four_same_cards() {
        FourOfAKindRanking ranking = new FourOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(7, Suit.Club));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_FOUR_OF_A_KIND_when_five_cards_contain_four_4_and_one_8() {
        FourOfAKindRanking ranking = new FourOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(8, Suit.Club));
                add(new Card(4, Suit.Diamond));
                add(new Card(4, Suit.Spade));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.FOUR_OF_A_KIND, result.getCardRanking());
    }

    @Test
    public void should_return_FOUR_OF_A_KIND_when_five_cards_contain_four_4_and_one_1() {
        FourOfAKindRanking ranking = new FourOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(1, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(4, Suit.Spade));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.FOUR_OF_A_KIND, result.getCardRanking());
    }
}
