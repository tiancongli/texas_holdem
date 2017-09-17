/**
 * Define an exception when the card name is invalid.
 *
 * @author Tiancong Li
 * @loginId tiancongl
 */
public class CardException extends NullPointerException {
    /**
     * Construct CardException with default description.
     */
    public CardException() {
        super("Invalid Card.");
    }

    /**
     * Construct CardException given the invalid card name.
     * @param cardName the invalid name
     */
    public CardException(String cardName) {
        super(String.format("Error: invalid card name '%s'", cardName));
    }
}
