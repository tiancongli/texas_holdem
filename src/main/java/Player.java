/**
 * The implementation of player.
 * Use an index to identify the player, and every player has a hand of cards.
 *
 * @author Tiancong Li
 */
public class Player {

    /**
     * The index of player, from 1 to N.
     */
    private int index;

    /**
     * The hand of cards.
     */
    private Hand hand;

    /**
     * Construct the Player by index and hand.
     * @param index the index of player
     * @param hand the hand of cards
     */
    public Player(int index, Hand hand) {
        this.index = index;
        this.hand = hand;
    }

    /**
     * use the combination of index and hand's class description to present
     * the player.
     */
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

}
