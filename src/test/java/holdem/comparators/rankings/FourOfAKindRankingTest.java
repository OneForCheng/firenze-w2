package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
import holdem.enums.CardSuit;
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.FOUR, CardSuit.Spade));
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
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.FOUR, CardSuit.Spade));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.FOUR_OF_A_KIND, result.getCardRanking());
    }
}
