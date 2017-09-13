import java.util.*;

public class Hand {
    private enum Status {INIT, FULL, SORTED};
    private Status status;
    private Classification classification;
    private Card[] cards;
    private static final int CARDS_NUM = 5;

    public String getClassification() {
        return classification.getDesc();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : cards) {
            result.append(card).append("\n");
        }
        return result.toString();
    }

    public Map<Integer, TreeSet<Rank>> getAllRepeats() {
        Map<Integer, TreeSet<Rank>> repeats = new TreeMap<>();
        for (int i = 2; i < 5; i++) {
            repeats.put(i, getRepeat(i));
        }
        return repeats;
    }

    public TreeSet<Rank> getRepeat(int repeatNumber) {
        TreeSet<Rank> repeat = new TreeSet<>();

        Map<Rank, Integer> partitions = getPartition();
        for (Map.Entry<Rank, Integer> entry : partitions.entrySet()) {
            if (entry.getValue() == repeatNumber) {
                repeat.add(entry.getKey());
            }
        }
        return repeat;
    }

    public Map<Rank, Integer> getPartition() {
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

    public boolean isFlush() {
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards[i].getSuit() != cards[i-1].getSuit()) {
                return false;
            }
        }
        return true;
    }

    public boolean isStraight() {
        // add codes to make sure status is sorted
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards[i].compareTo(cards[i-1]) != 1) {
                return false;
            }
        }
        return true;
    }

    public void classify() {
        List<Rank> ranks = new ArrayList<>();
        Map<Integer, TreeSet<Rank>> repeats = getAllRepeats();

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
        classification.setDesc(ranks);

    }

    public void sortHand() {
        Arrays.sort(this.cards);
        this.status = Status.SORTED;
    }

    public void setCard(int number, Card card) {
        cards[number] = card;
        if (cards[CARDS_NUM-1] != null) {
            this.status = Status.FULL;
            this.sortHand();
            this.classify();
        }
    }

    public Card getCard(int number) {
        return cards[number];
    }

    public Hand() {
        this.cards = new Card[CARDS_NUM];
        this.status = Status.INIT;
    }
}
