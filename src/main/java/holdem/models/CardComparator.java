package holdem.models;

import holdem.comparings.*;
import holdem.enums.CardGroupRanking;
import holdem.rankings.*;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class CardComparator {

    private List<IRanking> rankings;
    private Map<CardGroupRanking, IComparing> comparisons;

    public CardComparator() {
        this.rankings = new ArrayList<IRanking>() {
            {
                add(new RoyalStraightFlushRanking());
                add(new StraightFlushRanking());
                add(new FourOfAKindRanking());
                add(new FullHouseRanking());
                add(new FlushRanking());
                add(new StraightRanking());
                add(new ThreeOfAKindRanking());
                add(new TwoPairRanking());
                add(new OnePairRanking());
                add(new HighCardRanking());
            }
        };

        this.comparisons = new LinkedHashMap<CardGroupRanking, IComparing>() {
            {
                put(CardGroupRanking.ROYAL_STRAIGHT_FLUSH, new RoyalStraightFlushComparing());
                put(CardGroupRanking.STRAIGHT_FLUSH, new StraightFlushComparing());
                put(CardGroupRanking.FOUR_OF_A_KIND, new FourOfAKindComparing());
                put(CardGroupRanking.FULL_HOUSE, new FullHouseComparing());
                put(CardGroupRanking.FLUSH, new FlushComparing());
                put(CardGroupRanking.STRAIGHT, new StraightComparing());
                put(CardGroupRanking.THREE_OF_A_KIND, new ThreeOfAKindComparing());
                put(CardGroupRanking.TWO_PAIR, new TwoPairComparing());
                put(CardGroupRanking.ONE_PAIR, new OnePairComparing());
                put(CardGroupRanking.HIGH_CARD, new HighCardComparing());
            }
        };
    }

    public Pair<CardGroupRanking, List<Card>> getMaxCardGroup(List<Card> commonCards, Card[] holeCards) {
        List<Card> newCards = new ArrayList<>(commonCards);
        newCards.addAll(Arrays.asList(holeCards));

        return this.getMaxCardGroup(newCards);
    }

    private Pair<CardGroupRanking, List<Card>> getMaxCardGroup(List<Card> cards) {
        List<Pair<CardGroupRanking, List<Card>>> rankingCards = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            for(int j = i+1; j < 7; j++) {
                int indexI = i;
                int finalJ = j;
                List<Card> fiveCards = IntStream.rangeClosed(0, 6).filter(index -> index != indexI && index != finalJ).mapToObj(cards::get).collect(Collectors.toList());

                Pair<CardGroupRanking, List<Card>> cardGroupRanking = getCardGroupRanking(fiveCards);
                if (cardGroupRanking != null) {
                    rankingCards.add(cardGroupRanking);
                }
            }
        }

        Map<CardGroupRanking, List<Pair<CardGroupRanking, List<Card>>>> sortedGroupRankingCards = rankingCards.stream().collect(groupingBy(Pair::getKey, toList()));

        for (CardGroupRanking cardGroupRanking : CardGroupRanking.values()) {
            List<Pair<CardGroupRanking, List<Card>>> group = sortedGroupRankingCards.get(cardGroupRanking);
            if (group != null) {
                List<Pair<CardGroupRanking, List<Card>>> descSortedGroup = group.stream().sorted((a, b) -> this.comparisons.get(cardGroupRanking).compare(b.getValue(), a.getValue())).collect(toList());
                return descSortedGroup.get(0);
            }
        }

        return null;
    }

    private Pair<CardGroupRanking, List<Card>> getCardGroupRanking(List<Card> clone) {
        for (IRanking ranking : this.rankings) {
            RankingResult result = ranking.parse(clone);
            if (result != null) {
                return new Pair<>(result.getCardRanking(), clone);
            }
        }
        return null;
    }

}
