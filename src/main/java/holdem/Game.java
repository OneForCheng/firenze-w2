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

    public void pass() {
        Player activePlayer = awaitingPlayers.poll();

        passExecute(activePlayer);

        playersTookAction.add(activePlayer);
        nextRound();
    }



    public void bet() {
        Player activePlayer = awaitingPlayers.poll();

        int currentBid = setCurrentBid(this.getMinWager());
        betExecute(currentBid, activePlayer, roundWagers.get(activePlayer));

        playersTookAction.add(activePlayer);
        nextRound();
    }

    public void raise(int wager) {
        Player activePlayer = awaitingPlayers.poll();

        int currentBid = setCurrentBid(wager);
        raiseExecute(currentBid, activePlayer, roundWagers.get(activePlayer));

        playersTookAction.add(activePlayer);
        nextRound();
    }

    public void fold() {
        Player activePlayer = awaitingPlayers.poll();

        foldExecute(activePlayer);

        playersTookAction.add(activePlayer);
        nextRound();
    }

    private void passExecute(Player activePlayer) {
        awaiting(activePlayer);
    }

    private void betExecute(int currentBid, Player activePlayer, int previousWager) {
        putInPot(currentBid - previousWager);
        wage(activePlayer, currentBid);
        awaiting(activePlayer);
    }

    private void raiseExecute(int currentBid, Player activePlayer, int previousWager) {
        putInPot(currentBid);
        wage(activePlayer, previousWager + currentBid);
        awaiting(activePlayer);
    }

    private void foldExecute(Player activePlayer) {
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

    private void awaiting(Player activePlayer) {
        awaitingPlayers.offer(activePlayer);
    }

    public int getMinWager() {
        return 1;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }
}
