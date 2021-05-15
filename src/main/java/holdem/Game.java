package holdem;

import holdem.actions.Action;
import holdem.comparators.BestCardGroupRanking;
import holdem.comparators.CardComparator;
import holdem.enums.CardGroupRanking;
import holdem.enums.Round;
import holdem.models.*;
import holdem.generators.Shuffled52Poker;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private Round currentRound;
    private int currentBid;
    private int pot;
    private Queue<Player> awaitingPlayers;
    private List<Player> players;
    private List<Card> commonCards;
    private Poker poker;
    private CardComparator cardComparator;

    public Game(Player... players) {
        this(Arrays.asList(players), new Poker(new Shuffled52Poker()), new CardComparator());
    }

    public Game(List<Player> players, Poker poker, CardComparator cardComparator) {
        this.players = players;
        this.poker = poker;
        this.cardComparator = cardComparator;

        this.awaitingPlayers = new LinkedList<>(this.players);
        this.pot = 0;
        this.currentBid = 0;
        this.currentRound = Round.PRE_FLOP;
        this.commonCards = new ArrayList<>();
        this.handOutHoleCards(this.players, this.poker);
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

    public List<Card> getCommonCards() {
        return this.commonCards;
    }

    public boolean isOver() {
        return this.getActivePlayers().size() == 1 || this.getCurrentRound() == Round.SHOWDOWN;
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

    public Map<Player, Double> getDistributedResult() {
        if (!this.isOver()) return null;

        List<Player> activePlayers = this.getActivePlayers();
        List<Player> allInPlayers = this.getAllInPlayers();

        List<Player> mayWinMoneyPlayers = new ArrayList<>(activePlayers);
        mayWinMoneyPlayers.addAll(allInPlayers);

        List<BestCardGroupRanking> bestCardGroupRankings = getBestCardGroupRankings(mayWinMoneyPlayers);
        List<List<BestCardGroupRanking>> group = this.cardComparator.getDescSortedBestCardGroupRankingGroup(bestCardGroupRankings);

        LinkedHashMap<Player, Double> result = new LinkedHashMap<>();

        Map<Player, Integer> playerTotalWagers = this.players.stream().collect(Collectors.toMap(Function.identity(), Player::getTotalWager));

        for (List<BestCardGroupRanking> winners : group) {
            if (winners.stream().noneMatch(winner -> winner.getPlayer().isAllIn())) {
                Integer remainWager = playerTotalWagers.values().stream().reduce(Integer::sum).orElse(0);
                winners.forEach(winner -> result.put(winner.getPlayer(), (double)remainWager / winners.size()));
                break;
            } else {
                List<BestCardGroupRanking> sortedWinners = winners.stream().sorted(Comparator.comparingInt(item -> item.getPlayer().getTotalWager())).collect(Collectors.toList());
                int size = sortedWinners.size();
                double winTotalWager, previousWinTotalWager = 0;
                for(int i = 0; i < size; i++) {
                    winTotalWager = 0;

                    Player winner = sortedWinners.get(i).getPlayer();
                    int totalWager = winner.getTotalWager();

                    for (Player player : playerTotalWagers.keySet()) {
                        int wager = playerTotalWagers.get(player);
                        if (totalWager > wager) {
                            winTotalWager += wager;
                            playerTotalWagers.put(player, 0);
                        } else {
                            winTotalWager += totalWager;
                            playerTotalWagers.put(player, wager - totalWager);
                        }
                    }
                    previousWinTotalWager += winTotalWager / (size - i);
                    result.put(winner, previousWinTotalWager);
                }
            }
        }

        return result;
    }

    private List<BestCardGroupRanking> getBestCardGroupRankings(List<Player> mayWinMoneyPlayers) {
        return mayWinMoneyPlayers.stream().map(player -> {
            Pair<CardGroupRanking, List<Card>> maxCardGroup = this.cardComparator.getMaxCardGroup(this.commonCards, player.getHoleCards());
            return new BestCardGroupRanking(player, maxCardGroup.getKey(), maxCardGroup.getValue());
        }).collect(Collectors.toList());
    }

    private void nextRound() {
        List<Player> activePlayers = getActivePlayers();

        if (activePlayers.stream().allMatch(Player::isTookAction) && activePlayers.stream().allMatch(player -> player.getCurrentRoundWager(this.currentRound) == this.currentBid)) {
            this.currentRound = Round.values()[this.currentRound.ordinal() + 1];
            activePlayers.forEach(player -> player.setTookAction(false));
            handOutCommonCard();
        }
    }

    private void handOutHoleCards(List<Player> players, Poker poker) {
        players.forEach(player -> player.setHoleCards(new Card[]{
                poker.handOut(),
                poker.handOut(),
        }));
    }

    private void handOutCommonCard() {
        if (this.currentRound == Round.FLOP) {
            this.commonCards.add(this.poker.handOut());
            this.commonCards.add(this.poker.handOut());
            this.commonCards.add(this.poker.handOut());
        } else if (this.currentRound == Round.TURN || this.currentRound == Round.RIVER) {
            this.commonCards.add(this.poker.handOut());
        }
    }

    private List<Player> getActivePlayers() {
        return this.players.stream().filter(Player::isActive).collect(Collectors.toList());
    }
}
