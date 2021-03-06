package holdem.enums;

public enum CardGroupRanking {
    ROYAL_STRAIGHT_FLUSH("皇家同花顺", 10),
    STRAIGHT_FLUSH("同花顺", 9),
    FOUR_OF_A_KIND("四条", 8),
    FULL_HOUSE("葫芦", 7),
    FLUSH("同花", 6),
    STRAIGHT("顺子", 5),
    THREE_OF_A_KIND("三条", 4),
    TWO_PAIR("两对", 3),
    ONE_PAIR("一对", 2),
    HIGH_CARD("散牌", 1);

    private String type;
    private int priority;

    CardGroupRanking(String type, int priority) {
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
