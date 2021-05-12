package holdem.comparators.rankings;

import holdem.models.Card;

import java.util.List;

public interface IRanking {
    RankingResult parse(List<Card> cards);
}
