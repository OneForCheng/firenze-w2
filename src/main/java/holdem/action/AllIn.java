package holdem.action;

import holdem.Game;
import holdem.model.Player;

public class AllIn implements Action {
    private int wager;

    public AllIn(int wager) {
        this.wager = wager;
    }

    @Override
    public void execute(Game game, Player activePlayer) {
        game.setCurrentBidForAllIn(this.wager);

        game.putInPot(this.wager);

        activePlayer.allIn();
        activePlayer.inActive();
    }
}
