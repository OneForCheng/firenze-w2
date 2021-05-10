package holdem.enums;

public enum CardRanking {
    ROYAL_STRAIGHT_FLUSH("皇家同花顺", 10),
    STRAIGHT_FLUSH("同花顺", 9);

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
