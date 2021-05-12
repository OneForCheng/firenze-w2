package holdem.comparators;

import holdem.enums.CardGroupRanking;
import holdem.models.Card;
import holdem.models.Player;

import java.util.List;

public class BestCardGroupRanking {
    private Player player;
    private CardGroupRanking cardGroupRanking;
    private List<Card> cards;

    public BestCardGroupRanking(Player player, CardGroupRanking cardGroupRanking, List<Card> cards) {
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
