package holdem;

import holdem.action.Action;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private Round currentRound;
    private int currentBid;
    private int pot;
    private Queue<Player> awaitingPlayers;
    private List<Player> players;

    public Game(Player... players) {
        this.players = Arrays.asList(players);
        this.awaitingPlayers = new LinkedList<>(this.players);
        this.pot = 0;
        this.currentBid = 0;
        this.currentRound = Round.PRE_FLOP;
    }

    public Player getActivePlayer() {
        return this.awaitingPlayers.peek();
    }

    public int getPot() {
        return this.pot;
    }

    public int getCurrentBid() {
        return this.currentBid;
    }

    public int getMinWager() {
        return 1;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }

    public void execute(Action action) {
        Player activePlayer = awaitingPlayers.poll();
        action.execute(this, activePlayer);
        activePlayer.setTookAction(true);
        nextRound();
    }

    public void raise(Player activePlayer, int wager) {
        int previousWager = activePlayer.getPreviousWager();
        int currentBid = setCurrentBid(wager);
        putInPot(currentBid);
        wage(activePlayer, previousWager + currentBid);
        awaiting(activePlayer);
    }

    public void awaiting(Player activePlayer) {
        awaitingPlayers.offer(activePlayer);
    }

    private void nextRound() {
        List<Player> activePlayers = this.players.stream().filter(Player::isActive).collect(Collectors.toList());

        if (activePlayers.stream().allMatch(Player::isTookAction) && activePlayers.stream().allMatch(player -> player.getPreviousWager() == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
            activePlayers.stream().forEach(player -> player.setTookAction(false));
        }
    }

    public int setCurrentBid(int wager) {
        boolean isMinWager = wager == this.getMinWager();
        if (isMinWager) {
            if (this.currentBid < wager)
                this.currentBid = wager;
        } else {
            this.currentBid = wager;
        }

        return this.currentBid;
    }

    private void wage(Player activePlayer, int currentBid) {
        activePlayer.setPreviousWager(currentBid);
    }

    public void putInPot(int bid) {
        this.pot += bid;
    }

}
