import java.util.*;

public class Hand implements Comparable<Hand> {
    private Classification classification;
    private Card[] cards;
    private Map<Integer, TreeSet<Rank>> repeats;
    private List<Rank> descRanks;
    private static final int CARDS_NUM = 5;

    public Hand() {
        this.cards = new Card[CARDS_NUM];
        this.repeats = new TreeMap<>();
        this.descRanks = new ArrayList<>();
    }

    public String getClassification() {
        return classification.getDescByRanks(descRanks);
    }

    public void setCard(int number, Card card) {
        cards[number] = card;
        if (cards[CARDS_NUM-1] != null) {
            this.sortHand();
            this.setAllRepeats();
            this.classify();
        }
    }

    public Map<Integer, TreeSet<Rank>> getRepeats() {
        return repeats;
    }

    public Card getCard(int number) {
        return cards[number];
    }


    private static int compareRanks(List<Rank> ranks, List<Rank> anotherRanks) {
        for (int i = ranks.size() - 1; i >= 0 ; i--) {
            if (ranks.get(i) != anotherRanks.get(i)) {
                return ranks.get(i).compareTo(anotherRanks.get(i));
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Hand hand) {
        ArrayList<Rank> ranks = new ArrayList<>();
        ArrayList<Rank> anotherRanks = new ArrayList<>();

        if (this.classification != hand.classification) {
            return this.classification.ordinal() - hand.classification.ordinal();
        } else {
            switch (this.classification) {
                case STRAIGHT_FLUSH:
                case STRAIGHT:
                case FLUSH:
                case HIGH_CARD:
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    break;
                case FOUR_OF_A_KIND:
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(4));
                    anotherRanks.addAll(hand.repeats.get(4));
                    break;
                case FULL_HOUSE:
                    ranks.addAll(this.repeats.get(2));
                    anotherRanks.addAll(hand.repeats.get(2));
                    ranks.addAll(this.repeats.get(3));
                    anotherRanks.addAll(hand.repeats.get(3));
                    break;
                case THREE_OF_A_KIND:
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(3));
                    anotherRanks.addAll(hand.repeats.get(3));
                    break;
                case TWO_PAIR:
                case ONE_PAIR:
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(2));
                    anotherRanks.addAll(hand.repeats.get(2));
                    break;
            }
            return compareRanks(ranks, anotherRanks);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : cards) {
            result.append(card).append("\n");
        }
        return result.toString();
    }

    public void setAllRepeats() {
        for (int i = 1; i < 5; i++) {
            repeats.put(i, getRepeat(i));
        }
    }

    private TreeSet<Rank> getRepeat(int repeatNumber) {
        TreeSet<Rank> repeat = new TreeSet<>();

        Map<Rank, Integer> partitions = getPartition();
        for (Map.Entry<Rank, Integer> entry : partitions.entrySet()) {
            if (entry.getValue() == repeatNumber) {
                repeat.add(entry.getKey());
            }
        }
        return repeat;
    }

    private Map<Rank, Integer> getPartition() {
        Map<Rank, Integer> result = new TreeMap<>();
        for (int i = 0; i < CARDS_NUM; i++) {
            Rank rank = cards[i].getRank();
            if (!result.containsKey(rank)) {
               result.put(rank, 0);
            }
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private boolean isFlush() {
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards[i].getSuit() != cards[i-1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards[i].compareTo(cards[i-1]) != 1) {
                return false;
            }
        }
        return true;
    }

    public void classify() {
        List<Rank> ranks = new ArrayList<>();

        if (isStraight() & isFlush()) {
            ranks.add(cards[CARDS_NUM - 1].getRank());
            classification = Classification.STRAIGHT_FLUSH;
        } else if (repeats.get(4).size() == 1) {
            ranks.add(repeats.get(4).first());
            classification = Classification.FOUR_OF_A_KIND;
        } else if (repeats.get(3).size() == 1 & repeats.get(2).size() == 1) {
            ranks.add(repeats.get(3).first());
            ranks.add(repeats.get(2).first());
            classification = Classification.FULL_HOUSE;
        } else if (isFlush()) {
            ranks.add(cards[CARDS_NUM - 1].getRank());
            classification = Classification.FLUSH;
        } else if (isStraight()) {
            ranks.add(cards[CARDS_NUM - 1].getRank());
            classification = Classification.STRAIGHT;
        } else if (repeats.get(3).size() == 1) {
            ranks.add(repeats.get(3).first());
            classification = Classification.THREE_OF_A_KIND;
        } else if (repeats.get(2).size() == 2) {
            ranks.add(repeats.get(2).last());
            ranks.add(repeats.get(2).first());
            classification = Classification.TWO_PAIR;
        } else if (repeats.get(2).size() == 1) {
            ranks.add(repeats.get(2).first());
            classification = Classification.ONE_PAIR;
        } else {
            ranks.add(cards[CARDS_NUM - 1].getRank());
            classification = Classification.HIGH_CARD;
        }
        this.descRanks = ranks;
    }

    public void sortHand() {
        Arrays.sort(this.cards);
    }

}
