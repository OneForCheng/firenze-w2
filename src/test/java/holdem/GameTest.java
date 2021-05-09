package holdem;

import holdem.action.*;
import holdem.constant.Round;
import holdem.model.Player;
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
}
