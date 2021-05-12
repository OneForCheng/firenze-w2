package holdem.comparators.rankings;

import holdem.enums.CardGroupRanking;

public class RankingResult {

    private CardGroupRanking cardGroupRanking;

    public RankingResult(CardGroupRanking cardGroupRanking) {
        this.cardGroupRanking = cardGroupRanking;
    }

    public CardGroupRanking getCardRanking() {
        return this.cardGroupRanking;
    }
}
