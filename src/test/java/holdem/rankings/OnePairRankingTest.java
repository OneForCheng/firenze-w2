package holdem.rankings;

import holdem.enums.CardGroupRanking;
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
                add(new Card(4, CardSuit.Club));
                add(new Card(4, CardSuit.Heart));
                add(new Card(4, CardSuit.Diamond));
                add(new Card(8, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
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
                add(new Card(1, CardSuit.Club));
                add(new Card(2, CardSuit.Heart));
                add(new Card(4, CardSuit.Diamond));
                add(new Card(8, CardSuit.Spade));
                add(new Card(8, CardSuit.Club));
            }
        };
        RankingResult result = ranking.parse(cards);
        assertEquals(CardGroupRanking.ONE_PAIR, result.getCardRanking());
    }
}
