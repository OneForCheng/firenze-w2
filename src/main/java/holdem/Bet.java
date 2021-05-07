package holdem;

public class Bet implements Action {
    @Override
    public void execute(Game game, Player activePlayer, int wager) {
        game.bet(activePlayer, wager);
    }
}
