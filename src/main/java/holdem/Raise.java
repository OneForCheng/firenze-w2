package holdem;

public class Raise implements Action {
    @Override
    public void execute(Game game, Player activePlayer, int wager) {
        game.raise(activePlayer, wager);
    }
}
