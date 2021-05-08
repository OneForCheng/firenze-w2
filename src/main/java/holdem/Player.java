package holdem;

public class Player {
    private boolean isActive;
    private String name;

    public Player(String name) {
        this.name = name;
        this.isActive = true;
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
}
