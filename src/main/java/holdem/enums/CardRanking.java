package holdem.enums;

public enum CardRanking {
    ROYAL_Straight_FLUSH("皇家同花顺", 10);

    private String type;
    private int priority;

    CardRanking(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }
}
