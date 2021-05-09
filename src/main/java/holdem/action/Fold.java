package holdem.action;

import holdem.Game;
import holdem.model.Player;

public class Fold implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        activePlayer.inActive();
    }
}
