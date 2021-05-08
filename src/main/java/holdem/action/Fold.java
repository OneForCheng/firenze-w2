package holdem.action;

import holdem.Game;
import holdem.Player;

public class Fold implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        activePlayer.inActive();
    }
}
