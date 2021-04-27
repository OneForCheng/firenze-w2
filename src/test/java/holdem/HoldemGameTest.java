package holdem;

import org.junit.Test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class HoldemGameTest {

    @Test public void should_correct_init_holdem_game() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        assertEquals(0.0, game.getTotalAmount(), 0.0001);
        assertTrue(game.getExitedPlayers().isEmpty());
        assertEquals(players.get(0), game.getProgressPlayer());
        assertEquals(3, game.getPendingPlayers().size());
        assertEquals(BaseOperation.Pass, game.getEnableOperations().get(0));
        assertEquals(BaseOperation.Bet, game.getEnableOperations().get(1));
        assertEquals(BaseOperation.Fold, game.getEnableOperations().get(2));
    }

    @Test public void should_return_160_when_all_players_only_bet_10_every_round() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        Operation bet10 = new Operation(BaseOperation.Bet, 10);
        IntStream.rangeClosed(1, 16).forEach(i -> {
            game.play(bet10);
        });
        assertEquals(160.0, game.getTotalAmount(), 0.0001);
        assertTrue(game.getExitedPlayers().isEmpty());
        assertEquals(players.get(0), game.getProgressPlayer());
        assertEquals(3, game.getPendingPlayers().size());
    }

    @Test public void should_return_90_when_C_and_D_fold_in_first_round_and_second_round() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        Operation bet10 = new Operation(BaseOperation.Bet, 10);
        Operation fold = new Operation(BaseOperation.Fold, 0);
        game.play(bet10);
        game.play(bet10);
        game.play(bet10);
        game.play(fold);
        game.play(bet10);
        game.play(bet10);
        game.play(fold);
        game.play(bet10);
        game.play(bet10);
        game.play(bet10);
        game.play(bet10);

        assertEquals(90.0, game.getTotalAmount(), 0.0001);
        assertEquals(2, game.getExitedPlayers().size());
        assertEquals(players.get(0), game.getProgressPlayer());
        assertEquals(1, game.getPendingPlayers().size());
    }

    @Test public void should_return_60_when_B_C_and_D_fold_in_first_round_and_second_round_and_third_round() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        Operation bet10 = new Operation(BaseOperation.Bet, 10);
        Operation fold = new Operation(BaseOperation.Fold, 0);
        game.play(bet10);
        game.play(bet10);
        game.play(bet10);
        game.play(fold);
        game.play(bet10);
        game.play(bet10);
        game.play(fold);
        game.play(bet10);
        game.play(fold);

        assertEquals(60.0, game.getTotalAmount(), 0.0001);
        assertEquals(3, game.getExitedPlayers().size());
        assertEquals(players.get(0), game.getProgressPlayer());
        assertEquals(0, game.getPendingPlayers().size());
    }

    @Test public void should_distribute_160_to_B_when_all_players_only_bet_10_every_round_and_B_is_winner() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        Operation bet10 = new Operation(BaseOperation.Bet, 10);
        IntStream.rangeClosed(1, 16).forEach(i -> {
            game.play(bet10);
        });
        Player b = players.get(1);
        game.distributeAmount(Collections.singletonList(b));
        assertEquals(220, b.getAmount(), 0.0001);
        assertEquals(0, game.getTotalAmount(), 0.0001);
    }

    @Test public void should_averagely_distribute_160_to_B_when_all_players_are_winner() {
        List<Player> players = Arrays.asList(
                new Player("A", 100),
                new Player("B", 100),
                new Player("C", 100),
                new Player("D", 100)
        );
        HoldemGame game = new HoldemGame(players);
        Operation bet10 = new Operation(BaseOperation.Bet, 10);
        IntStream.rangeClosed(1, 16).forEach(i -> {
            game.play(bet10);
        });
        game.distributeAmount(players);
        assertEquals(100, players.get(0).getAmount(), 0.0001);
        assertEquals(100, players.get(1).getAmount(), 0.0001);
        assertEquals(100, players.get(2).getAmount(), 0.0001);
        assertEquals(100, players.get(3).getAmount(), 0.0001);
        assertEquals(0, game.getTotalAmount(), 0.0001);
    }
}
