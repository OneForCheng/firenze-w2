package holdem.action;

import holdem.Game;
import holdem.model.Player;

public interface Action {
    void execute(Game game, Player activePlayer);
}
