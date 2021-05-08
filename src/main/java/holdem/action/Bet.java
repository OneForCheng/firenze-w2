package holdem.action;

import holdem.Game;
import holdem.Player;

public class Bet implements Action {

    @Override
    public void execute(Game game, Player activePlayer) {
        game.bet(activePlayer);
    }
}
