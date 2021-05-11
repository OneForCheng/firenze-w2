package holdem.comparings;

import holdem.enums.Suit;
import holdem.models.Card;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StraightFlushComparingTest {
    @Test
    public void should_return_0_when_first_argument_equal_the_second() {
        IComparing comparing = new StraightFlushComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(3, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(4, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(6, Suit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(7, Suit.Club));
                    add(new Card(5, Suit.Club));
                    add(new Card(4, Suit.Club));
                    add(new Card(3, Suit.Club));
                    add(new Card(6, Suit.Club));
                }
            }
        };
        assertEquals(0, comparing.compare(first, second));
    }

    @Test
    public void should_return_negative_1_when_first_argument_less_than_the_second() {
        IComparing comparing = new StraightFlushComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                add(new Card(3, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(4, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(6, Suit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(7, Suit.Club));
                    add(new Card(5, Suit.Club));
                    add(new Card(4, Suit.Club));
                    add(new Card(8, Suit.Club));
                    add(new Card(6, Suit.Club));
                }
            }
        };
        assertEquals(-1, comparing.compare(first, second));
    }

    @Test
    public void should_return_positive_1_when_first_argument_greater_than_the_second() {
        IComparing comparing = new StraightFlushComparing();
        List<Card> first = new ArrayList<Card>(5){
            {
                {
                    add(new Card(7, Suit.Club));
                    add(new Card(5, Suit.Club));
                    add(new Card(4, Suit.Club));
                    add(new Card(8, Suit.Club));
                    add(new Card(6, Suit.Club));
                }
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                add(new Card(3, Suit.Club));
                add(new Card(5, Suit.Club));
                add(new Card(4, Suit.Club));
                add(new Card(7, Suit.Club));
                add(new Card(6, Suit.Club));
            }
        };
        assertEquals(1, comparing.compare(first, second));
    }
}
