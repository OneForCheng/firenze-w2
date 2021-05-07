package holdem;

public interface Action {
    void execute(Game game, Player activePlayer);
}
