package holdem;

public class Player {
    private boolean isActive;
    private boolean isTookAction;
    private int previousWager;
    private String name;

    public Player(String name) {
        this.name = name;
        this.isActive = true;
        this.isTookAction = false;
        this.previousWager = 0;
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

    public int getPreviousWager() {
        return this.previousWager;
    }

    public void setPreviousWager(int wager) {
        this.previousWager = wager;
    }
}
