package holdem.action;

import holdem.Game;
import holdem.Player;

public class Pass implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        game.awaiting(activePlayer);
    }
}
