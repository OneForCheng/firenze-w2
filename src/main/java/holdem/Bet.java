package holdem;

public class Bet implements Action {

    @Override
    public void execute(Game game, Player activePlayer) {
        game.bet(activePlayer);
    }
}
