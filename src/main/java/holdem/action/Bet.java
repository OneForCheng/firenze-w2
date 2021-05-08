package holdem.action;

import holdem.Game;
import holdem.Player;

public class Bet implements Action {

    @Override
    public void execute(Game game, Player activePlayer) {
        int minWager = game.getMinWager();
        int currentBid = game.setCurrentBid(minWager);

        game.putInPot(currentBid - activePlayer.getPreviousWager());
        game.awaiting(activePlayer);

        activePlayer.setPreviousWager(currentBid);
    }
}
