package holdem;

public class Fold implements Action {
    @Override
    public void execute(Game game, Player activePlayer, int wager) {
        game.inActive(activePlayer);
    }
}
