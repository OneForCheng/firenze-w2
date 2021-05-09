package holdem.action;

import holdem.Game;
import holdem.Player;

public class Raise implements Action {

    private final int wager;

    public Raise(int wager) {
        this.wager = wager;
    }

    @Override
    public void execute(Game game, Player activePlayer) {
        int previousWager = activePlayer.getPreviousWager();
        int currentBid = game.setCurrentBid(this.wager);

        game.putInPot(currentBid);
        game.awaiting(activePlayer);

        activePlayer.setPreviousWager(previousWager + currentBid);
    }
}
