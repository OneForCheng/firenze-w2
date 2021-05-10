package holdem.rankings;

import holdem.enums.CardRanking;

public class RankingResult {

    private CardRanking cardRanking;

    public RankingResult(CardRanking cardRanking) {
        this.cardRanking = cardRanking;
    }

    public CardRanking getCardRanking() {
        return this.cardRanking;
    }
}
