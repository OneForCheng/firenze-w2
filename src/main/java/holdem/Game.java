package holdem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private final Queue<Player> activePlayers;
    private Round currentRound;
    private HashSet<Player> playersTookAction;
    private int currentBid;
    private int pot;
    private Queue<Player> awaitingPlayers;
    private Player[] players;
    private Map<Player, Integer> roundWagers;

    public Game(Player... players) {
        this.players = players;
        this.awaitingPlayers = new LinkedList<>(Arrays.asList(players));
        this.pot = 0;
        this.currentBid = 0;
        this.playersTookAction = new HashSet<>();
        this.currentRound = Round.PREFLOP;
        this.roundWagers = Arrays.stream(players).collect(Collectors.toMap(Function.identity(), player -> 0));
        this.activePlayers = new LinkedList<>(Arrays.asList(players));
    }

    public Player getActivePlayer() {
        return this.awaitingPlayers.peek();
    }

    public int getPot() {
        return pot;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void pass() {
        Player activePlayer = awaitingPlayers.poll();
        playersTookAction.add(activePlayer);
        awaitingPlayers.offer(activePlayer);

        nextRound();
    }

    private void nextRound() {
        if (playersTookAction.size() == players.length && activePlayers.stream().allMatch(player -> roundWagers.get(player) == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
        }
    }

    public void bet() {
        if (this.currentBid < this.getMinWager()) {
            this.currentBid = this.getMinWager();
        }

        Player activePlayer = awaitingPlayers.poll();
        this.pot += this.currentBid - roundWagers.get(activePlayer);

        roundWagers.put(activePlayer, this.currentBid);

        playersTookAction.add(activePlayer);
        awaitingPlayers.offer(activePlayer);

        nextRound();
    }

    public int getMinWager() {
        return 1;
    }

    public void raise(int wager) {
        this.currentBid = wager;
        this.pot += this.currentBid;

        Player activePlayer = awaitingPlayers.poll();
        roundWagers.put(activePlayer, roundWagers.get(activePlayer) + this.currentBid);

        playersTookAction.add(activePlayer);
        awaitingPlayers.offer(activePlayer);

        nextRound();
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

    public void fold() {
        Player activePlayer = awaitingPlayers.poll();
        playersTookAction.add(activePlayer);
        activePlayers.remove(activePlayer);

        nextRound();
    }
}
