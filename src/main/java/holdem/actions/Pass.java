package holdem.actions;

import holdem.Game;
import holdem.models.Player;

public class Pass implements Action {
    @Override
    public void execute(Game game, Player activePlayer) {
        game.awaiting(activePlayer);
    }
}
