package holdem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HoldemGame {
    private List<Player> pendingPlayers;
    private List<BaseOperation> enableOperations;
    private Player finishedPlayer;
    private Player progressPlayer;
    private List<Player> exitedPlayers;
    private double totalAmount;

    HoldemGame(List<Player> players) {
        List<Player> clonePlayers = players.stream().collect(Collectors.toList());
        this.progressPlayer = clonePlayers.remove(0);
        this.pendingPlayers = clonePlayers;
        this.enableOperations = Arrays.asList(BaseOperation.Pass, BaseOperation.Bet, BaseOperation.Fold);
        this.finishedPlayer = null;
        this.exitedPlayers = Collections.emptyList();
        this.totalAmount = 0;
    }

    public List<Player> getPendingPlayers() {
        return this.pendingPlayers;
    }

    public List<BaseOperation> getEnableOperations() {
        return enableOperations;
    }

    public Player getFinishedPlayer() {
        return this.finishedPlayer;
    }

    public Player getProgressPlayer() {
        return this.progressPlayer;
    }

    public List<Player> getExitedPlayers() {
        return this.exitedPlayers;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void play(Operation operation) {
        switch (operation.getBaseOperation()) {
            case Pass:
                break;
            case Bet:
                this.totalAmount += operation.getAmount();
                break;
            case Fold:
                break;
            case Raise:
                break;
        }
    }
}
