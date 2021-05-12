package holdem.models;

public class Player {
    private boolean isActive;
    private boolean isTookAction;
    private boolean isAllIn;
    private int currentRoundWager;
    private String name;
    private Card[] holeCards;

    public Player(String name) {
        this.name = name;
        this.isActive = true;
        this.isTookAction = false;
        this.isAllIn = false;
        this.currentRoundWager = 0;
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

    public int getCurrentRoundWager() {
        return this.currentRoundWager;
    }

    public void setCurrentRoundWager(int wager) {
        this.currentRoundWager = wager;
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
