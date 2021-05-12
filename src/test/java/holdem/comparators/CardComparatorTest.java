package holdem.comparators;

import holdem.enums.CardGroupRanking;
import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CardComparatorTest {
    @Test
    public void should_return_royal_straight_flush_as_max_card_group() {
        CardComparator comparator = new CardComparator();
        List<Card> commonCards = new ArrayList<Card>() {
            {
                add(new Card(CardRank.FIVE, CardSuit.Diamond));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Spade));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
            }
        };
        Card[] holeCards = new Card[] {
                new Card(CardRank.ACE, CardSuit.Club),
                new Card(CardRank.KING, CardSuit.Club),
        };
        Pair<CardGroupRanking, List<Card>> result = comparator.getMaxCardGroup(commonCards, holeCards);
        assertEquals(CardGroupRanking.ROYAL_STRAIGHT_FLUSH, result.getKey());
        List<CardRank> ranks = result.getValue().stream().map(Card::getRank).collect(Collectors.toList());
        assertTrue(ranks.contains(CardRank.TEN));
        assertTrue(ranks.contains(CardRank.JACK));
        assertTrue(ranks.contains(CardRank.QUEUE));
        assertTrue(ranks.contains(CardRank.KING));
        assertTrue(ranks.contains(CardRank.ACE));
    }

    @Test
    public void should_return_straight_flush_as_max_card_group() {
        CardComparator comparator = new CardComparator();
        List<Card> commonCards = new ArrayList<Card>() {
            {
                add(new Card(CardRank.FIVE, CardSuit.Diamond));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Spade));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
            }
        };
        Card[] holeCards = new Card[] {
                new Card(CardRank.NINE, CardSuit.Club),
                new Card(CardRank.KING, CardSuit.Club),
        };
        Pair<CardGroupRanking, List<Card>> result = comparator.getMaxCardGroup(commonCards, holeCards);
        assertEquals(CardGroupRanking.STRAIGHT_FLUSH, result.getKey());
        List<CardRank> ranks = result.getValue().stream().map(Card::getRank).collect(Collectors.toList());
        assertTrue(ranks.contains(CardRank.TEN));
        assertTrue(ranks.contains(CardRank.JACK));
        assertTrue(ranks.contains(CardRank.QUEUE));
        assertTrue(ranks.contains(CardRank.KING));
        assertTrue(ranks.contains(CardRank.NINE));
    }

    @Test
    public void should_return_four_of_a_kind_as_max_card_group() {
        CardComparator comparator = new CardComparator();
        List<Card> commonCards = new ArrayList<Card>() {
            {
                add(new Card(CardRank.ACE, CardSuit.Diamond));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Spade));
                add(new Card(CardRank.NINE, CardSuit.Heart));
            }
        };
        Card[] holeCards = new Card[] {
                new Card(CardRank.NINE, CardSuit.Club),
                new Card(CardRank.NINE, CardSuit.Diamond),
        };
        Pair<CardGroupRanking, List<Card>> result = comparator.getMaxCardGroup(commonCards, holeCards);
        assertEquals(CardGroupRanking.FOUR_OF_A_KIND, result.getKey());
        List<CardRank> ranks = result.getValue().stream().map(Card::getRank).collect(Collectors.toList());
        assertTrue(ranks.contains(CardRank.NINE));
        assertTrue(ranks.contains(CardRank.ACE));
    }
}
