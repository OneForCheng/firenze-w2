package holdem;

public class Player {
    private String name;
    private double amount;

    Player(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
