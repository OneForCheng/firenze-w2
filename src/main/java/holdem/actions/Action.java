package holdem.actions;

import holdem.Game;
import holdem.models.Player;

public interface Action {
    void execute(Game game, Player activePlayer);
}
