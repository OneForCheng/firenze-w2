package holdem;

public class Pass implements Action {
    @Override
    public void execute(Game game, Player activePlayer, int wager) {
        game.awaiting(activePlayer);
    }
}
