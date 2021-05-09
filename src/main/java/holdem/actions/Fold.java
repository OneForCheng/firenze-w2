package holdem.actions;

import holdem.Game;
import holdem.models.Player;

public class Fold implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        activePlayer.inActive();
    }
}
