/**
 * The enum type of poker suits, including Clubs (♣), Diamonds (♦), Hearts (♥),
 * and Spades (♠).
 *
 * @author Tiancong Li
 */
public enum Suit {
    CLUB("C"),
    DIAMOND("D"),
    HEART("H"),
    SPADE("S");

    /**
     * The alias of each suit.
     */
    private String alias;

    /**
     * Constructs a suit with its alias.
     * @param alias the alias of the suit
     */
    Suit(String alias) {
        this.alias = alias;
    }

    /**
     * Get the corresponding enum object by giving the alias.
     * @param alias the alias of suit
     * @return the suit
     */
    public static Suit getSuitByAlias(String alias) {
        for (Suit suit : Suit.values()) {
            if (suit.alias.equals(alias)) {
                return suit;
            }
        }
        return null;
    }

}
