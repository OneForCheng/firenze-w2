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

    private void nextRound() {
        if (playersTookAction.size() == players.length && activePlayers.stream().allMatch(player -> roundWagers.get(player) == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
        }
    }

    public void execute(Action action, int wager) {
        Player activePlayer = awaitingPlayers.poll();
        action.execute(this, activePlayer, wager);
        playersTookAction.add(activePlayer);
        nextRound();
    }

    public void bet(Player activePlayer, int wager) {
        int previousWager = roundWagers.get(activePlayer);
        int currentBid = setCurrentBid(wager);
        putInPot(currentBid - previousWager);
        wage(activePlayer, currentBid);
        awaiting(activePlayer);
    }

    public void raise(Player activePlayer, int wager) {
        int previousWager = roundWagers.get(activePlayer);
        int currentBid = setCurrentBid(wager);
        putInPot(currentBid);
        wage(activePlayer, previousWager + currentBid);
        awaiting(activePlayer);
    }

    public void inActive(Player activePlayer) {
        activePlayers.remove(activePlayer);
    }

    private int setCurrentBid(int wager) {
        if (wager == this.getMinWager()) {
            if (this.currentBid < wager)
                this.currentBid = wager;
        } else {
            this.currentBid = wager;
        }

        return this.currentBid;
    }

    private void putInPot(int bid) {
        this.pot += bid;
    }

    private void wage(Player activePlayer, int newWager) {
        roundWagers.put(activePlayer, newWager);
    }

    public void awaiting(Player activePlayer) {
        awaitingPlayers.offer(activePlayer);
    }

    public int getMinWager() {
        return 1;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

}
