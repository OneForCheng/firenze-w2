package holdem.enums;

public enum CardRank {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEUE(12),
    KING(13),
    ACE(14);

    private int number;

    CardRank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
