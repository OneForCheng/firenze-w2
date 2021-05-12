package holdem.actions;

import holdem.Game;
import holdem.enums.Round;
import holdem.models.Player;

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

        Round currentRound = game.getCurrentRound();
        int currentRoundWager = activePlayer.getCurrentRoundWager(currentRound);
        activePlayer.setCurrentRoundWager(currentRound, currentRoundWager + currentBid);
    }
}
