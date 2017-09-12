public class Hand {
    private Card[] cards;

    @Override
    public String toString() {
        String result = "";
        for (Card card : cards) {
            result = result + card + "\n";
        }
        return result;
    }

    public void setCard(int number, Card card) {
        cards[number] = card;
    }

    public Card getCard(int number) {
        return cards[number];
    }

    public Hand() {
        this.cards = new Card[5];
    }
}
