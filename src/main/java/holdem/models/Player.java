package holdem.models;

import holdem.enums.Round;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Player {
    private boolean isActive;
    private boolean isTookAction;
    private boolean isAllIn;
    private Map<Round, Integer> roundWagers;
    private String name;
    private Card[] holeCards;

    public Player(String name) {
        this.name = name;
        this.isActive = true;
        this.isTookAction = false;
        this.isAllIn = false;
        roundWagers = Arrays.stream(Round.values()).collect(Collectors.toMap(Function.identity(), round -> 0));
    }

    public String getName() {
        return this.name;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void inActive() {
        this.isActive = false;
    }

    public boolean isTookAction() {
        return this.isTookAction;
    }

    public void setTookAction(boolean isTookAction) {
        this.isTookAction = isTookAction;
    }

    public int getCurrentRoundWager(Round round) {
        return this.roundWagers.get(round);
    }

    public void setCurrentRoundWager(Round round, int wager) {
        this.roundWagers.put(round, wager);
    }

    public Map<Round, Integer> getRoundWagers() {
        return this.roundWagers;
    }

    public void allIn() {
        this.isAllIn = true;
    }

    public boolean isAllIn() {
        return this.isAllIn;
    }

    public Card[] getHoleCards() {
        return holeCards;
    }

    public void setHoleCards(Card[] holeCards) {
        this.holeCards = holeCards;
    }
}
