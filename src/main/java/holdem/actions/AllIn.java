package holdem.actions;

import holdem.Game;
import holdem.enums.Round;
import holdem.models.Player;

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

        Round currentRound = game.getCurrentRound();
        int currentRoundWager = activePlayer.getCurrentRoundWager(currentRound);
        activePlayer.setCurrentRoundWager(currentRound, currentRoundWager + this.wager);
    }
}
