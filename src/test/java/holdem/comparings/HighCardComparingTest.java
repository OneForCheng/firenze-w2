package holdem.comparings;

import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HighCardComparingTest {
    @Test
    public void should_return_0_when_first_argument_equal_the_second() {
        IComparing comparing = new HighCardComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Heart));
                add(new Card(CardRank.ACE, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.ACE, CardSuit.Club));
                    add(new Card(CardRank.FIVE, CardSuit.Club));
                    add(new Card(CardRank.FOUR, CardSuit.Heart));
                    add(new Card(CardRank.THREE, CardSuit.Club));
                    add(new Card(CardRank.SIX, CardSuit.Club));
                }
            }
        };
        assertEquals(0, comparing.compare(first, second));
    }

    @Test
    public void should_return_negative_1_when_first_argument_less_than_the_second() {
        IComparing comparing = new HighCardComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.THREE, CardSuit.Heart));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.SEVEN, CardSuit.Club));
                    add(new Card(CardRank.FIVE, CardSuit.Club));
                    add(new Card(CardRank.FOUR, CardSuit.Club));
                    add(new Card(CardRank.EIGHT, CardSuit.Heart));
                    add(new Card(CardRank.TWO, CardSuit.Club));
                }
            }
        };
        assertEquals(-1, comparing.compare(first, second));
    }

    @Test
    public void should_return_positive_1_when_first_argument_greater_than_the_second() {
        IComparing comparing = new HighCardComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                {
                    add(new Card(CardRank.SEVEN, CardSuit.Club));
                    add(new Card(CardRank.FIVE, CardSuit.Club));
                    add(new Card(CardRank.FOUR, CardSuit.Club));
                    add(new Card(CardRank.KING, CardSuit.Club));
                    add(new Card(CardRank.SIX, CardSuit.Heart));
                }
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.SEVEN, CardSuit.Club));
                add(new Card(CardRank.SIX, CardSuit.Heart));
            }
        };
        assertEquals(1, comparing.compare(first, second));
    }
}
