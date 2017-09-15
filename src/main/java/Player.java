public class Player {
    private int index;
    private Hand hand;

    @Override
    public String toString() {
        return String.format("Player %d: %s", index, hand.getClassification());
    }

    public int getIndex() {
        return index;
    }

    public Hand getHand() {
        return hand;
    }

    public Player(int index, Hand hand) {
        this.index = index;
        this.hand = hand;
    }

}
