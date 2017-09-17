/**
 * The enum type of pokers'ranks.
 * The ranks, in order of increasing value, are 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack,
 * Queen, King, and Ace.
 *
 * @author Tiancong Li
 */
public enum  Rank {
    TWO("2", "2"),
    THREE("3", "3"),
    FOUR("4", "4"),
    FIVE("5", "5"),
    SIX("6", "6"),
    SEVEN("7", "7"),
    EIGHT("8", "8"),
    NINE("9", "9"),
    TEN("T", "10"),
    JACK("J", "Jack"),
    QUEEN("Q", "Queen"),
    KING("K", "King"),
    ACE("A", "Ace");

    /**
     * The short alias of rank.
     */
    private final String alias;

    /**
     * The real name of the rank.
     */
    private final String name;


    /**
     * Constructs a rank with its alias and name.
     * @param alias the alias of the rank
     * @param name the name of the rank
     */
    Rank(String alias, String name) {
        this.alias = alias;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Get the corresponding enum object by giving the alias.
     * @param alias the alias of rank
     * @return the rank
     */
    public static Rank getRankByAlias(String alias) {
        for (Rank rank : Rank.values()) {
            if (rank.alias.equals(alias)) {
                return rank;
            }
        }
        return null;
    }
}
