package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ThreeOfAKindRankingTest {
    @Test
    public void should_return_null_when_five_cards_contain_four_same_cards() {
        ThreeOfAKindRanking ranking = new ThreeOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(4, Suit.Spade));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_null_when_five_cards_are_full_house() {
        ThreeOfAKindRanking ranking = new ThreeOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(4, Suit.Diamond));
                add(new Card(8, Suit.Heart));
                add(new Card(8, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_THREE_OF_A_KIND_when_five_cards_contain_three_same_cards_and_them_are_different_other_two_cards() {
        ThreeOfAKindRanking ranking = new ThreeOfAKindRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(4, Suit.Club));
                add(new Card(4, Suit.Heart));
                add(new Card(8, Suit.Club));
                add(new Card(4, Suit.Diamond));
                add(new Card(7, Suit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.THREE_OF_A_KIND, result.getCardRanking());
    }
}
