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

    private String alias;
    private String name;

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    Rank(String alias, String name) {
        this.alias = alias;
        this.name = name;
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
