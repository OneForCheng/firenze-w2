package holdem.comparings;

import holdem.enums.CardSuit;
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
                add(new Card(3, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(4, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(7, CardSuit.Club));
                    add(new Card(5, CardSuit.Club));
                    add(new Card(4, CardSuit.Club));
                    add(new Card(3, CardSuit.Club));
                    add(new Card(6, CardSuit.Club));
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
                add(new Card(3, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(4, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                {
                    add(new Card(7, CardSuit.Club));
                    add(new Card(5, CardSuit.Club));
                    add(new Card(4, CardSuit.Club));
                    add(new Card(8, CardSuit.Club));
                    add(new Card(6, CardSuit.Club));
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
                    add(new Card(7, CardSuit.Club));
                    add(new Card(5, CardSuit.Club));
                    add(new Card(4, CardSuit.Club));
                    add(new Card(8, CardSuit.Club));
                    add(new Card(6, CardSuit.Club));
                }
            }
        };
        List<Card> second = new ArrayList<Card>(5){
            {
                add(new Card(3, CardSuit.Club));
                add(new Card(5, CardSuit.Club));
                add(new Card(4, CardSuit.Club));
                add(new Card(7, CardSuit.Club));
                add(new Card(6, CardSuit.Club));
            }
        };
        assertEquals(1, comparing.compare(first, second));
    }
}
