public enum  Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private String alias;

    Rank(String alias) {
        this.alias = alias;
    }

    public static Rank getRankByAlias(String alias) {
        for (Rank rank : Rank.values()) {
            if (rank.alias.equals(alias)) {
                return rank;
            }
        }
        return null;
    }
}
