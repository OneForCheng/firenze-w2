package holdem;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest  {
    @Test
    public void player_a_should_be_the_first_active_player() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals("A", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(1, game.getMinWager());
        assertEquals(0, game.getCurrentBid());
    }

    @Test
    public void player_b_should_be_next_player_if_player_a_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        game.execute(new Pass(), 0);

        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(0, game.getPot());
        assertEquals(0, game.getCurrentBid());
    }

    @Test
    public void should_set_current_bid_to_min_wager_when_player_a_bet() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        game.execute(new Bet(), game.getMinWager());

        assertEquals("B", game.getActivePlayer().getName());
        assertEquals(1, game.getPot());
        assertEquals(1, game.getCurrentBid());
    }

    @Test
    public void should_enter_next_round_if_all_players_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Pass(), 0);

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Pass(), 0);

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Pass(), 0);

        assertEquals(Round.FLOP, game.getCurrentRound());
    }

    @Test
    public void should_enter_next_round_if_all_players_bet() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals(Round.FLOP, game.getCurrentRound());
    }

    @Test
    public void should_not_entry_next_round_if_anyone_raise() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Raise(), 2);

        assertEquals(Round.PREFLOP, game.getCurrentRound());
        assertEquals("A", game.getActivePlayer().getName());
        assertEquals(4, game.getPot());
        assertEquals(2, game.getCurrentBid());
    }

    @Test
    public void should_call_to_previous_bet_after_someone_raise_the_wager() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());
        int playWager = game.getCurrentBid();

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Raise(), 3);

        assertEquals(3, game.getCurrentBid());
        int pot = game.getPot();

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals(game.getCurrentBid() - playWager, game.getPot() - pot);

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Bet(), game.getMinWager());

        assertEquals(Round.FLOP, game.getCurrentRound());
    }


    @Test
    public void should_enter_next_round_if_all_players_fold_or_pass() {
        Game game = new Game(new Player("A"), new Player("B"), new Player("C"));

        assertEquals(Round.PREFLOP, game.getCurrentRound());

        assertEquals("A", game.getActivePlayer().getName());
        game.execute(new Fold(), 0);

        assertEquals("B", game.getActivePlayer().getName());
        game.execute(new Pass(), 0);

        assertEquals("C", game.getActivePlayer().getName());
        game.execute(new Pass(), 0);

        assertEquals(Round.FLOP, game.getCurrentRound());
        assertEquals("B", game.getActivePlayer().getName());
    }
}
