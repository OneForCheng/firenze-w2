package holdem;

import holdem.actions.*;
import holdem.enums.CardRank;
import holdem.enums.CardSuit;
import holdem.enums.Round;
import holdem.models.Card;
import holdem.comparators.CardComparator;
import holdem.models.Player;
import holdem.models.Poker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GameTest  {
    double DELTA = 0.01;

    @Test
    public void player_a_should_be_the_first_active_player() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals("A", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(1, game.getMinWager());
        assertEquals(0, game.getCurrentBid());
        assertEquals(false, game.isOver());
    }

    @Test
    public void player_b_should_be_next_player_if_player_a_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        game.execute(new Pass());

        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(0, game.getCurrentBid());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_set_current_bid_to_min_wager_when_player_a_bet() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        game.execute(new Bet());

        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(1, game.getPot());
        assertEquals(1, game.getCurrentBid());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_enter_next_round_if_all_players_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_enter_next_round_if_all_players_bet() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_not_entry_next_round_if_anyone_raise() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Raise(2));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());
        assertEquals("A", game.getActivePlayer().getName());
        assertEquals(4, game.getPot());
        assertEquals(2, game.getCurrentBid());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_call_to_previous_bet_after_someone_raise_the_wager() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet());
        int playWager = game.getCurrentBid();

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Raise(3));

        assertEquals(3, game.getCurrentBid());
        int pot = game.getPot();

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals(game.getCurrentBid() - playWager, game.getPot() - pot);

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals(false, game.isOver());
    }


    @Test
    public void should_enter_next_round_if_all_players_fold_or_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Fold());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_enter_third_round_if_all_players_pass_in_previous_two_round() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_enter_forth_round_if_all_players_pass_in_previous_three_round() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.RIVER, game.getCurrentRound());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_enter_next_round_if_player_a_all_in_and_other_players_bet() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new AllIn(6));
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(18, game.getPot());
        assertEquals(6, game.getCurrentBid());
        assertEquals("A", game.getAllInPlayers().get(0).getName());
        assertEquals(false, game.isOver());
    }

    @Test
    public void should_game_over_if_there_is_only_one_active_player_in_first_round() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        game.execute(new Fold());
        game.execute(new Fold());

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());
        assertEquals("C", game.getActivePlayer().getName());
        assertEquals(true, game.isOver());
    }

    @Test
    public void should_game_over_if_game_enter_showdown_round() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.RIVER, game.getCurrentRound());
        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.SHOWDOWN, game.getCurrentRound());
        assertEquals(true, game.isOver());
    }

    @Test
    public void should_give_two_hole_card_for_every_player_when_the_game_start() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");

        assertNull(playerA.getHoleCards());
        assertNull(playerB.getHoleCards());
        assertNull(playerC.getHoleCards());

        Game game = new Game(playerA, playerB, playerC);

        assertEquals(2, playerA.getHoleCards().length);
        assertEquals(2, playerB.getHoleCards().length);
        assertEquals(2, playerC.getHoleCards().length);
    }

    @Test
    public void should_be_different_count_for_common_card_in_different_round() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());
        assertEquals(0, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals(3, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());
        assertEquals(4, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.RIVER, game.getCurrentRound());
        assertEquals(5, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.SHOWDOWN, game.getCurrentRound());
        assertEquals(5, game.getCommonCards().size());
    }

    @Test
    public void should_play_a_win_all_wager_if_other_players_fold() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");

        List<Player> players = new ArrayList<Player>() {
            {

                add(playerA);
                add(playerB);
                add(playerC);
            }
        };
        CardComparator comparator = new CardComparator();
        Game game = new Game(players, new Poker(() -> new LinkedList<Card>() {
            {
                add(new Card(CardRank.TWO, CardSuit.Club));
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.TWO, CardSuit.Spade));
                add(new Card(CardRank.TWO, CardSuit.Diamond));
                // common cards
                add(new Card(CardRank.EIGHT, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Club));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
            }
        }), comparator);


        assertNull(game.getDistributedResult());
        assertEquals(Round.PRE_FLOP, game.getCurrentRound());
        assertEquals(0, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals(3, game.getCommonCards().size());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());
        assertEquals(4, game.getCommonCards().size());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Fold());

        assertEquals(Round.RIVER, game.getCurrentRound());
        assertEquals(5, game.getCommonCards().size());

        game.execute(new Bet());
        game.execute(new Fold());

        Map<Player, Double> result = game.getDistributedResult();
        assertEquals(1, result.size());
        assertEquals(3, result.get(playerA), DELTA);
    }

    @Test
    public void should_play_a_win_all_wager_if_card_group_of_player_a_is_max() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");

        List<Player> players = new ArrayList<Player>() {
            {

                add(playerA);
                add(playerB);
                add(playerC);
            }
        };
        CardComparator comparator = new CardComparator();
        Game game = new Game(players, new Poker(() -> new LinkedList<Card>() {
            {
                add(new Card(CardRank.TWO, CardSuit.Club));
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.TWO, CardSuit.Spade));
                add(new Card(CardRank.TWO, CardSuit.Diamond));
                // common cards
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.FOUR, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Heart));
                add(new Card(CardRank.QUEUE, CardSuit.Spade));
            }
        }), comparator);

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.RIVER, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.SHOWDOWN, game.getCurrentRound());

        Map<Player, Double> result = game.getDistributedResult();
        assertEquals(1, result.size());
        assertEquals(6, result.get(playerA), DELTA);
    }

    @Test
    public void all_wager_should_be_averagely_distributed_for_player_a_and_player_b_if_both_player_a_and_player_b_are_max_card_group() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");

        List<Player> players = new ArrayList<Player>() {
            {

                add(playerA);
                add(playerB);
                add(playerC);
            }
        };
        CardComparator comparator = new CardComparator();
        Game game = new Game(players, new Poker(() -> new LinkedList<Card>() {
            {
                add(new Card(CardRank.TWO, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.TWO, CardSuit.Spade));
                add(new Card(CardRank.TWO, CardSuit.Diamond));
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                // common cards
                add(new Card(CardRank.SIX, CardSuit.Club));
                add(new Card(CardRank.ACE, CardSuit.Heart));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Heart));
                add(new Card(CardRank.QUEUE, CardSuit.Spade));
            }
        }), comparator);

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.TURN, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.RIVER, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.SHOWDOWN, game.getCurrentRound());

        Map<Player, Double> result = game.getDistributedResult();
        assertEquals(2, result.size());
        assertEquals(6, result.get(playerA), DELTA);
        assertEquals(6, result.get(playerA), DELTA);
    }

    @Test
    public void all_wager_should_be_averagely_distributed_for_all_player_if_max_card_group_is_in_common_cards() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Player playerC = new Player("C");

        List<Player> players = new ArrayList<Player>() {
            {

                add(playerA);
                add(playerB);
                add(playerC);
            }
        };
        CardComparator comparator = new CardComparator();
        Game game = new Game(players, new Poker(() -> new LinkedList<Card>() {
            {
                add(new Card(CardRank.TWO, CardSuit.Club));
                add(new Card(CardRank.THREE, CardSuit.Club));
                add(new Card(CardRank.FIVE, CardSuit.Club));
                add(new Card(CardRank.TWO, CardSuit.Heart));
                add(new Card(CardRank.TWO, CardSuit.Spade));
                add(new Card(CardRank.TWO, CardSuit.Diamond));
                // common cards
                add(new Card(CardRank.EIGHT, CardSuit.Club));
                add(new Card(CardRank.NINE, CardSuit.Club));
                add(new Card(CardRank.TEN, CardSuit.Club));
                add(new Card(CardRank.JACK, CardSuit.Club));
                add(new Card(CardRank.QUEUE, CardSuit.Club));
            }
        }), comparator);

        assertEquals(Round.PRE_FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.FLOP, game.getCurrentRound());

        game.execute(new Pass());
        game.execute(new Pass());
        game.execute(new Pass());

        assertEquals(Round.TURN, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.RIVER, game.getCurrentRound());

        game.execute(new Bet());
        game.execute(new Bet());
        game.execute(new Bet());

        assertEquals(Round.SHOWDOWN, game.getCurrentRound());

        Map<Player, Double> result = game.getDistributedResult();
        assertEquals(2, result.get(playerA), DELTA);
        assertEquals(2, result.get(playerB), DELTA);
        assertEquals(2, result.get(playerC), DELTA);
    }
}
