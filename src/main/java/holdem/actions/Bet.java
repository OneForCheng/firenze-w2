package holdem.actions;

import holdem.Game;
import holdem.models.Player;

public class Bet implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        game.setCurrentBidForBet();

        int currentBid = game.getCurrentBid();
        game.putInPot(currentBid - activePlayer.getCurrentRoundWager());
        game.awaiting(activePlayer);

        activePlayer.setCurrentRoundWager(currentBid);
    }
}
