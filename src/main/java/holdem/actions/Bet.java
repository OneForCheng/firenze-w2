package holdem.actions;

import holdem.Game;
import holdem.enums.Round;
import holdem.models.Player;

public class Bet implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        game.setCurrentBidForBet();
        Round currentRound = game.getCurrentRound();

        int currentBid = game.getCurrentBid();
        game.putInPot(currentBid - activePlayer.getCurrentRoundWager(currentRound));
        game.awaiting(activePlayer);

        activePlayer.setCurrentRoundWager(currentRound, currentBid);
    }
}
