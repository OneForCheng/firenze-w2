package holdem.models;

import holdem.enums.CardGroupRanking;

import java.util.List;

public class PlayerCardGroupRanking {
    private Player player;
    private CardGroupRanking cardGroupRanking;
    private List<Card> cards;

    public PlayerCardGroupRanking(Player player, CardGroupRanking cardGroupRanking, List<Card> cards) {
        this.player = player;
        this.cardGroupRanking = cardGroupRanking;
        this.cards = cards;
    }

    public Player getPlayer() {
        return player;
    }

    public CardGroupRanking getCardGroupRanking() {
        return cardGroupRanking;
    }

    public List<Card> getCards() {
        return cards;
    }
}
