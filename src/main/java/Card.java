public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;

    @Override
    public String toString() {
        return this.rank + " " + this.suit;
    }

    @Override
    public int compareTo(Card card) {
        return this.rank.ordinal() - card.rank.ordinal();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }


    public Card(String rankSuit) {
        this(rankSuit.substring(0, 1), rankSuit.substring(1, 2));

    }

    public Card(String rank, String suit) {
        this.rank = Rank.getRankByAlias(rank);
        this.suit = Suit.getSuitByAlias(suit);
    }
}
