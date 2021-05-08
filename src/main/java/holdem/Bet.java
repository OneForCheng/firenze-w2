package holdem;

public class Bet implements Action {

    private final int wager;

    Bet(int wager) {
        this.wager = wager;
    }

    @Override
    public void execute(Game game, Player activePlayer) {
        game.bet(activePlayer, this.wager);
    }
}
