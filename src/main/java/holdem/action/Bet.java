package holdem.action;

import holdem.Game;
import holdem.Player;

public class Bet implements Action {

    @Override
    public void execute(Game game, Player activePlayer) {
        game.setCurrentBid(game.getMinWager());

        int currentBid = game.getCurrentBid();
        game.putInPot(currentBid - activePlayer.getPreviousWager());
        game.awaiting(activePlayer);

        activePlayer.setPreviousWager(currentBid);
    }
}
