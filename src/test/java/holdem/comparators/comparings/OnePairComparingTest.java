package holdem.comparators.comparings;

import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OnePairComparingTest {
    @Test
    public void should_return_0_when_first_argument_equal_the_second() {
        IComparing comparing = new OnePairComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.THREE, CardSuit.Heart));
                add(new Card(CardRank.FIVE, CardSuit.Diamond));
                add(new Card(CardRank.SEVEN, CardSuit.Spade));
                add(new Card(CardRank.SIX, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.THREE, CardSuit.Club));
                    add(new Card(CardRank.THREE, CardSuit.Heart));
                    add(new Card(CardRank.SIX, CardSuit.Club));
                    add(new Card(CardRank.FIVE, CardSuit.Diamond));
                    add(new Card(CardRank.SEVEN, CardSuit.Spade));
                }
            }
        };
        assertEquals(0, comparing.compare(first, second));
    }

    @Test
    public void should_return_negative_1_when_first_argument_less_than_the_second() {
        IComparing comparing = new OnePairComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.THREE, CardSuit.Heart));
                add(new Card(CardRank.TWO, CardSuit.Diamond));
                add(new Card(CardRank.FIVE, CardSuit.Spade));
                add(new Card(CardRank.SIX, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.THREE, CardSuit.Club));
                    add(new Card(CardRank.THREE, CardSuit.Heart));
                    add(new Card(CardRank.TWO, CardSuit.Diamond));
                    add(new Card(CardRank.FIVE, CardSuit.Spade));
                    add(new Card(CardRank.SEVEN, CardSuit.Club));
                }
            }
        };
        assertEquals(-1, comparing.compare(first, second));
    }

    @Test
    public void should_return_positive_1_when_first_argument_greater_than_the_second() {
        IComparing comparing = new OnePairComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.NINE, CardSuit.Club));
                    add(new Card(CardRank.NINE, CardSuit.Heart));
                    add(new Card(CardRank.THREE, CardSuit.Diamond));
                    add(new Card(CardRank.EIGHT, CardSuit.Spade));
                    add(new Card(CardRank.TWO, CardSuit.Club));
                }
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Heart));
                add(new Card(CardRank.ACE, CardSuit.Diamond));
                add(new Card(CardRank.KING, CardSuit.Spade));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
            }
        };
        assertEquals(1, comparing.compare(first, second));
    }
}