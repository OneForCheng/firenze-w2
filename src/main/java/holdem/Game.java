package holdem;

import holdem.action.Action;
import holdem.constant.Round;
import holdem.model.Card;
import holdem.model.Player;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private Round currentRound;
    private int currentBid;
    private int pot;
    private Queue<Player> awaitingPlayers;
    private List<Player> players;
    private Map<Player, Card[]> playerHoleCards;
    private Queue<Card> commonCards;

    public Game(Player... players) {
        this.players = Arrays.asList(players);
        this.awaitingPlayers = new LinkedList<>(this.players);
        this.pot = 0;
        this.currentBid = 0;
        this.currentRound = Round.PRE_FLOP;
        this.playerHoleCards = Arrays.stream(players).collect(Collectors.toMap(Function.identity(), player -> new Card[]{
                new Card(),
                new Card(),
        }));
        commonCards = new LinkedList<>();
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

    public List<Player> getAllInPlayers() {
        return players.stream().filter(Player::isAllIn).collect(Collectors.toList());
    }

    public void execute(Action action) {
        if (!this.isOver()) {
            Player activePlayer = awaitingPlayers.poll();
            action.execute(this, activePlayer);
            activePlayer.setTookAction(true);
            nextRound();
        }
    }

    public void awaiting(Player activePlayer) {
        awaitingPlayers.offer(activePlayer);
    }

    private void nextRound() {
        List<Player> activePlayers = this.players.stream().filter(Player::isActive).collect(Collectors.toList());

        if (activePlayers.stream().allMatch(Player::isTookAction) && activePlayers.stream().allMatch(player -> player.getPreviousWager() == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
            activePlayers.stream().forEach(player -> player.setTookAction(false));
            addCommonCard();
        }
    }

    private void addCommonCard() {
        if (this.currentRound == Round.FLOP) {
            this.commonCards.add(new Card());
            this.commonCards.add(new Card());
            this.commonCards.add(new Card());
        } else if (this.currentRound == Round.TURN || this.currentRound == Round.RIVER) {
            this.commonCards.add(new Card());
        }
    }

    public void setCurrentBidForBet() {
        int minWager = this.getMinWager();
        if (this.currentBid < minWager) {
            this.currentBid = minWager;
        }
    }

    public void setCurrentBidForRaise(int wager) {
        this.currentBid = wager;
    }

    public void setCurrentBidForAllIn(int wager) {
        if (wager >= this.getMinWager() && wager >= this.currentBid) {
            this.currentBid = wager;
        }
    }

    public void putInPot(int bid) {
        this.pot += bid;
    }

    public boolean isOver() {
        List<Player> activePlayers = this.players.stream().filter(Player::isActive).collect(Collectors.toList());;
        return activePlayers.size() == 1 || this.getCurrentRound() == Round.SHOWDOWN;
    }

    public Card[] getPlayerHoleCards(Player player) {
        return this.playerHoleCards.get(player);
    }

    public Queue<Card> getCommonCards() {
        return this.commonCards;
    }
}
