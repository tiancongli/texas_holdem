import java.util.List;

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
    private final int index;

    /**
     * The hand of cards.
     */
    private final Hand hand;

    /**
     * Construct the Player by index and hand.
     * @param index the index of player
     * @param rankSuits a list of rankSuit, which represents the combination
     *                 of rank and suit, such as "AH"
     */
    public Player(int index, List<String> rankSuits) {
        this.index = index;
        this.hand = new Hand(rankSuits);
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
