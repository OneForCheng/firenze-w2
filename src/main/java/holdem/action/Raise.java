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
        game.setCurrentBidForRaise(this.wager);

        int currentBid = game.getCurrentBid();
        game.putInPot(currentBid);
        game.awaiting(activePlayer);

        activePlayer.setPreviousWager(activePlayer.getPreviousWager() + currentBid);
    }
}
