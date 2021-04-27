package holdem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HoldemGame {
    private List<Player> pendingPlayers;
    private Player progressPlayer;
    private List<Player> exitedPlayers;
    private double totalAmount;
    private List<Operation> operationRecords;

    HoldemGame(List<Player> players) {
        List<Player> clonePlayers = new ArrayList<>(players);
        this.progressPlayer = clonePlayers.remove(0);
        this.pendingPlayers = clonePlayers;
        this.exitedPlayers = new ArrayList<>();
        this.totalAmount = 0;
        this.operationRecords = new ArrayList<>();
    }

    public List<Player> getPendingPlayers() {
        return this.pendingPlayers;
    }

    public List<BaseOperation> getEnableOperations() {

        if (this.operationRecords.size() == 0) return Arrays.asList(BaseOperation.Pass, BaseOperation.Bet, BaseOperation.Fold);

        List<BaseOperation> allOperations = Arrays.asList(BaseOperation.Pass, BaseOperation.Fold, BaseOperation.Bet, BaseOperation.Raise);
        List<Operation> notFoldOperations = this.operationRecords.stream().filter(operation -> operation.getBaseOperation() != BaseOperation.Fold).collect(Collectors.toList());

        int size = notFoldOperations.size();
        if (size == 0) return allOperations;
        Operation last = notFoldOperations.get(size - 1);

        switch (last.getBaseOperation()) {
            case Pass:
                return allOperations;
            case Bet:
            case Raise:
                return Arrays.asList(BaseOperation.Fold, BaseOperation.Bet, BaseOperation.Raise);
            default:
                return new ArrayList<>();
        }
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
        this.operationRecords.add(operation);
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

    public boolean canPlay(Operation operation) {
        return this.getEnableOperations().contains(operation.getBaseOperation());
    }
}
