package holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoldemGame {
    private List<Player> pendingPlayers;
    private List<BaseOperation> enableOperations;
    private Player progressPlayer;
    private List<Player> exitedPlayers;
    private double totalAmount;

    HoldemGame(List<Player> players) {
        List<Player> clonePlayers = new ArrayList<>(players);
        this.progressPlayer = clonePlayers.remove(0);
        this.pendingPlayers = clonePlayers;
        this.enableOperations = Arrays.asList(BaseOperation.Pass, BaseOperation.Bet, BaseOperation.Fold);
        this.exitedPlayers = new ArrayList<>();
        this.totalAmount = 0;
    }

    public List<Player> getPendingPlayers() {
        return this.pendingPlayers;
    }

    public List<BaseOperation> getEnableOperations() {
        return enableOperations;
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
        BaseOperation baseOperation = operation.getBaseOperation();
        switch (baseOperation) {
            case Pass:
                if (this.pendingPlayers.size() > 0) {
                    Player finishedPlayer = this.progressPlayer;
                    this.progressPlayer = this.pendingPlayers.remove(0);
                    this.pendingPlayers.add(finishedPlayer);
                }
                break;
            case Fold:
                this.exitedPlayers.add(this.progressPlayer);
                if (this.pendingPlayers.size() > 0) {
                    this.progressPlayer = this.pendingPlayers.remove(0);
                }
                break;
            case Bet:
            case Raise:
                this.totalAmount += operation.getAmount();
                this.progressPlayer.setAmount(this.progressPlayer.getAmount() - operation.getAmount());
                if (this.pendingPlayers.size() > 0) {
                    Player finishedPlayer = this.progressPlayer;
                    this.progressPlayer = this.pendingPlayers.remove(0);
                    this.pendingPlayers.add(finishedPlayer);
                }
                break;
        }
    }

    public void distributeAmount(List<Player> winners) {
        double winAmount = this.totalAmount / winners.size();
        for (Player winner : winners) {
            winner.setAmount(winner.getAmount() + winAmount);
        }
        this.totalAmount = 0;
    }
}
