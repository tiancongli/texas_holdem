/**
 * The implementation of poker cards.
 * Use rank and suit as the components.
 *
 * @author Tiancong Li
 */
public class Card implements Comparable<Card> {

    /**
     * The rank of the card.
     */
    private Rank rank;

    /**
     * The suit of the card.
     */
    private Suit suit;

    /**
     * Construct a card given the rankSuit string by calling another
     * constructor.
     * @param rankSuit a string consisted by rank and suit, such as '3H', which
     *                 means rank Three and suit Heart.
     */
    public Card(String rankSuit) {
        this(rankSuit.substring(0, 1), rankSuit.substring(1, 2));

    }

    /**
     * Construct a card by the alias of rank and suit.
     * @param rank the alias of rank
     * @param suit the alias of suit
     */
    public Card(String rank, String suit) {
        this.rank = Rank.getRankByAlias(rank);
        this.suit = Suit.getSuitByAlias(suit);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    /**
     * Use rank and suit to present the card.
     */
    @Override
    public String toString() {
        return rank + " " + suit;
    }

    /**
     * Override compareTo method, depending on the ranks'order.
     * This will be used when Collections.sort.
     */
    @Override
    public int compareTo(Card card) {
        return this.rank.ordinal() - card.rank.ordinal();
    }

}
