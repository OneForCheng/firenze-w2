package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FullHouseRankingTest {
    @Test
    public void should_return_null_when_five_cards_not_contain_three_same_cards() {
        FullHouseRanking ranking = new FullHouseRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(5, Suit.Diamond));
                add(new Card(5, Suit.Club));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_three_cards_are_same_cards_but_other_two_cards_are_different() {
        FullHouseRanking ranking = new FullHouseRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(5, Suit.Club));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_FULL_HOUSE_when_three_cards_are_4_and_other_two_cards_are_5() {
        FullHouseRanking ranking = new FullHouseRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(5, Suit.Club));
                add(new Card(4, Suit.Diamond));
                add(new Card(5, Suit.Heart));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.FULL_HOUSE, result.getCardRanking());
    }


    @Test
    public void should_return_FULL_HOUSE_when_three_cards_are_4_and_other_two_cards_are_1() {
        FullHouseRanking ranking = new FullHouseRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(1, Suit.Club));
                add(new Card(4, Suit.Diamond));
                add(new Card(1, Suit.Heart));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.FULL_HOUSE, result.getCardRanking());
    }
}
