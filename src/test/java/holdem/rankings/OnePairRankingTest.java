package holdem.rankings;

import holdem.enums.CardGroupRanking;
import holdem.enums.Suit;
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
    public void should_return_ONE_PAIR_when_five_cards_only_contain_one_pair_same_number() {
        OnePairRanking ranking = new OnePairRanking();
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
        assertEquals(CardGroupRanking.ONE_PAIR, result.getCardRanking());
    }
}
