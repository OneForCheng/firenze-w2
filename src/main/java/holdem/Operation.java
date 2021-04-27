package holdem;

public class Operation {
    private BaseOperation baseOperation;
    private double amount;

    Operation(BaseOperation baseOperation, double amount) {
        this.baseOperation = baseOperation;
        this.amount = amount;
    }

    public BaseOperation getBaseOperation() {
        return baseOperation;
    }

    public double getAmount() {
        return amount;
    }
}
