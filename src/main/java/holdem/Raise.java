package holdem;

public class Raise implements Action {

    private final int wager;

    Raise(int wager) {
        this.wager = wager;
    }

    @Override
    public void execute(Game game, Player activePlayer) {
        game.raise(activePlayer, this.wager);
    }
}
