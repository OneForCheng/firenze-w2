package holdem.action;

import holdem.Game;
import holdem.Player;

public interface Action {
    void execute(Game game, Player activePlayer);
}
