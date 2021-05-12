package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OnePairRankingTest {
    @Test
    public void should_return_null_when_five_cards_contain_three_same_number() {
        OnePairRanking ranking = new OnePairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.EIGHT, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertNull(result);
    }

    @Test
    public void should_return_ONE_PAIR_when_five_cards_only_contain_one_pair_same_number() {
        OnePairRanking ranking = new OnePairRanking();
        List<Card> cards = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.FOUR, CardSuit.Diamond));
                add(new Card(CardRank.EIGHT, CardSuit.Spade));
                add(new Card(CardRank.EIGHT, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.ONE_PAIR, result.getCardRanking());
    }
}
