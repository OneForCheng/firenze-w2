package holdem;

import holdem.action.Action;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private Round currentRound;
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
        this.currentRound = Round.PREFLOP;
        this.roundWagers = Arrays.stream(players).collect(Collectors.toMap(Function.identity(), player -> 0));
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
        List<Player> activePlayers = Arrays.stream(this.players).filter(Player::isActive).collect(Collectors.toList());

        if (activePlayers.stream().allMatch(Player::isTookAction) && activePlayers.stream().allMatch(player -> roundWagers.get(player) == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
            activePlayers.stream().forEach(player -> player.setTookAction(false));
        }
    }

    public void execute(Action action) {
        Player activePlayer = awaitingPlayers.poll();
        action.execute(this, activePlayer);
        activePlayer.setTookAction(true);
        nextRound();
    }

    public void bet(Player activePlayer) {
        int previousWager = roundWagers.get(activePlayer);
        int currentBid = setCurrentBid(getMinWager());
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
        activePlayer.inActive();
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
