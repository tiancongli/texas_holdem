public enum Suit {
    CLUB("C"),
    DIAMOND("D"),
    HEART("H"),
    SPADE("S");

    private String alias;

    Suit(String alias) {
        this.alias = alias;
    }

    public static Suit getSuitByAlias(String alias) {
        for (Suit suit : Suit.values()) {
            if (suit.alias.equals(alias)) {
                return suit;
            }
        }
        return null;
    }

}
