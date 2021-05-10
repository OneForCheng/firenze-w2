package holdem.rankings;

import holdem.enums.CardRanking;
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
    public void should_return_correct_ranking_result_when_three_cards_are_same_cards_and_other_two_cards_also_are_same() {
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
        assertEquals(CardRanking.FULL_HOUSE, result.getCardRanking());
    }
}
