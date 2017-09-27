import java.util.*;

/**
 * The implementation of poker hand.
 * It contains methods of how to sort the cards in the hand, how to classify
 * the hand, and how to compare different
 * hands.
 *
 * @author Tiancong Li
 * @loginId tiancongl
 */
public class Hand implements Comparable<Hand> {

    /**
     * The total cards number in a hand.
     */
    public static final int CARDS_NUM = 5;

    /**
     * The cards of the hand.
     */
    private final List<Card> cards;

    /**
     * The classification of the hand.
     */
    private Classification classification;

    /**
     * The repeat components in the hand.
     * The data structure is a map. The key is the times of cards'occurrence,
     * and the value is the set of cards(ranks). For example,
     * {1: {TWO, THREE, QUEEN},
     *  2: {ACE}}
     *  means TWO, THREE, QUEEN occur only once, while ACE occurs twice.
     */
    private final Map<Integer, TreeSet<Rank>> repeats;

    /**
     * The list of ranks which accounts for the description of the hand.
     */
    private final List<Rank> descRanks;

    /**
     * Init the hand, and init the cards in it.
     * @param rankSuits a list of rankSuit, which represents the combination
     *                 of rank and suit, such as "AH"
     */
    public Hand(List<String> rankSuits) {
        cards = new ArrayList<>();
        repeats = new TreeMap<>();
        descRanks = new ArrayList<>();

        for (String rankSuit : rankSuits) {
            addCard(new Card(rankSuit));
        }
    }

    /**
     * get the description of the hand's classification, using the
     * description ranks of the classification.
     */
    public String getClassification() {
        return classification.getDescByRanks(descRanks);
    }

    /**
     * Add a card to the hand.
     * @param card a poker card
     */
    public void addCard(Card card) {
        // if the hand is full, just ignore this add.
        if (cards.size() == CARDS_NUM) return;

        cards.add(card);

        // if the hand is full after adding the card, set up the repeats
        // variable, sort and classify the hand.
        if (cards.size() == CARDS_NUM) {
            setRepeats();
            sortHand();
            classify();
        }
    }

    /**
     * Divide the hand into parts.
     * Using a Map to store the partitions, the key is the card rank and the
     * value is the number of occurrences. For example, {TWO: 1, THREE: 1,
     * QUEEN: 1, ACE: 2}
     * @return the map of partitions
     */
    private Map<Rank, Integer> getPartition() {
        Map<Rank, Integer> result = new TreeMap<>();

        for (Card card : cards) {
            Rank rank = card.getRank();
            if (!result.containsKey(rank)) {
                result.put(rank, 0);
            }
            result.put(rank, result.get(rank) + 1);
        }

        return result;
    }

    /**
     * Set up the repeats variable
     */
    private void setRepeats() {
        // First init the repeats with the key from 1 to CARDS_NUM-1 since a
        // card can occur no more than CARD_NUM-1 times. Init the value with
        // empty set.
        for (int i = 1; i < CARDS_NUM; i++) {
            repeats.put(i, new TreeSet<>());
        }

        // Get the partitions of the hand.
        Map<Rank, Integer> partitions = getPartition();

        // Traverse the partitions, and make up repeats variable
        for (Map.Entry<Rank, Integer> entry : partitions.entrySet()) {
            repeats.get(entry.getValue()).add(entry.getKey());
        }

    }

    /**
     * Sort the hand by the cards order.
     */
    public void sortHand() {
        Collections.sort(cards);
    }


    /**
     * Judge whether the hand is flush
     * @return boolean
     */
    private boolean isFlush() {
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards.get(i).getSuit() != cards.get(i-1).getSuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Judge whether the hand is straight.
     * @return boolean
     */
    private boolean isStraight() {
        for (int i = 1; i < CARDS_NUM; i++) {
            if (cards.get(i).getRank().ordinal() - cards.get(i-1).getRank()
                    .ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * set up the classification and descRanks of the hand.
     */
    public void classify() {
        if (isStraight() && isFlush()) {
            // if straight and flush, make it STRAIGHT_FLUSH, and set the
            // highest rank to the descRanks.
            classification = Classification.STRAIGHT_FLUSH;
            descRanks.add(cards.get(CARDS_NUM - 1).getRank());

        } else if (repeats.get(4).size() == 1) {
            // if one card occurs 4 times in the hand, make it
            // FOUR_OF_A_KIND, and set rank of 4 cards to the descRanks.
            classification = Classification.FOUR_OF_A_KIND;
            descRanks.add(repeats.get(4).first());

        } else if (repeats.get(3).size() == 1 && repeats.get(2).size() == 1) {
            // if one card occurs 3 times, one occurs 2 times, make it
            // FULL_HOUSE, and set rank of 3 cards, and 2 cards to descRanks.
            classification = Classification.FULL_HOUSE;
            descRanks.add(repeats.get(3).first());
            descRanks.add(repeats.get(2).first());

        } else if (isFlush()) {
            // if still flush, make it FLUSH, and set highest rank to the
            // descRanks.
            classification = Classification.FLUSH;
            descRanks.add(cards.get(CARDS_NUM -1).getRank());

        } else if (isStraight()) {
            // if still straight, make it STRAIGHT, and set highest rank to
            // the descRanks.
            classification = Classification.STRAIGHT;
            descRanks.add(cards.get(CARDS_NUM -1).getRank());

        } else if (repeats.get(3).size() == 1) {
            // if still one occurs 3 times, make it THREE_OF_A_KIND, and set
            // rank of 3 cards to descRanks.
            classification = Classification.THREE_OF_A_KIND;
            descRanks.add(repeats.get(3).first());

        } else if (repeats.get(2).size() == 2) {
            // if two cards both occur 2 times, make it TWO_PAIR, and set
            // rank of pairs to descRanks, from the higher to lower.
            classification = Classification.TWO_PAIR;
            descRanks.add(repeats.get(2).last());
            descRanks.add(repeats.get(2).first());

        } else if (repeats.get(2).size() == 1) {
            // if still one occurs 2 times, make it ONE_PAIR, and set rank
            // of the pair to descRanks.
            classification = Classification.ONE_PAIR;
            descRanks.add(repeats.get(2).first());

        } else {
            // otherwise, make it HIGH_CARD, and set the highest to descRanks.
            classification = Classification.HIGH_CARD;
            descRanks.add(cards.get(CARDS_NUM -1).getRank());
        }
    }

    /**
     * The assistant method to help the compareTo method.
     * Compare the given two lists of ranks, the last one has highest priority.
     * @param ranks the first given list of ranks
     * @param anotherRanks the other list of ranks
     * @return 0 if two lists are same, greater than 0 if the first list
     * wins, less than 0 if the second list wins.
     */
    private static int compareRanks(List<Rank> ranks, List<Rank> anotherRanks) {
        for (int i = ranks.size() - 1; i >= 0 ; i--) {
            if (ranks.get(i) != anotherRanks.get(i)) {
                return ranks.get(i).compareTo(anotherRanks.get(i));
            }
        }
        return 0;
    }

    /**
     * Override compareTo based on classifications and ranks, will be used
     * when sorting.
     */
    @Override
    public int compareTo(Hand hand) {
        if (this.classification != hand.classification) {
            // first compare the classification
            return this.classification.ordinal() - hand.classification
                    .ordinal();

        } else {
            // using two ranks list to compare the hands when same class.
            // store higher priority rank from the last to the first element of
            // list.
            ArrayList<Rank> ranks = new ArrayList<>();
            ArrayList<Rank> anotherRanks = new ArrayList<>();

            switch (this.classification) {
                case STRAIGHT_FLUSH:
                case STRAIGHT:
                case FLUSH:
                case HIGH_CARD:
                    // use the ranks of single cards to compare.
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    break;
                case FOUR_OF_A_KIND:
                    // use the rank of 4 cards, and the rank of single card.
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(4));
                    anotherRanks.addAll(hand.repeats.get(4));
                    break;
                case FULL_HOUSE:
                    // use the rank of 3 cards and the rank of 2 cards.
                    ranks.addAll(this.repeats.get(2));
                    anotherRanks.addAll(hand.repeats.get(2));
                    ranks.addAll(this.repeats.get(3));
                    anotherRanks.addAll(hand.repeats.get(3));
                    break;
                case THREE_OF_A_KIND:
                    // use the rank of 3 cards, and the remaining single cards.
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(3));
                    anotherRanks.addAll(hand.repeats.get(3));
                    break;
                case TWO_PAIR:
                case ONE_PAIR:
                    // use the rank of pairs, and the rank of single cards.
                    ranks.addAll(this.repeats.get(1));
                    anotherRanks.addAll(hand.repeats.get(1));
                    ranks.addAll(this.repeats.get(2));
                    anotherRanks.addAll(hand.repeats.get(2));
                    break;
            }

            return compareRanks(ranks, anotherRanks);
        }
    }

}
