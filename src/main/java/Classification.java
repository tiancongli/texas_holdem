import java.util.List;

/**
 * The enum type of classifications of a poker hand.
 *
 * @author Tiancong Li
 * @loginId tiancongl
 */
public enum Classification {
    HIGH_CARD("r0-high"),
    ONE_PAIR("Pair of r0s"),
    TWO_PAIR("r0s over r1s"),
    THREE_OF_A_KIND("Three r0s"),
    STRAIGHT("r0-high straight"),
    FLUSH("r0-high flush"),
    FULL_HOUSE("r0s full of r1s"),
    FOUR_OF_A_KIND("Four r0s"),
    STRAIGHT_FLUSH("r0-high straight flush");

    /**
     * The description template for each classification.
     */
    private final String template;

    /**
     * Constructs a classification with its desc template.
     * @param template the template of classification
     */
    Classification (String template) {
        this.template = template;
    }

    /**
     * Get the description of the classification by giving the cards'ranks.
     * Replacing the placeholder in template by the rank's name. For example,
     * r0 is replaced by Ace.
     * @param ranks the cards'ranks which present the classification
     * @return the description of the classification
     */
    public String getDescByRanks(List<Rank> ranks) {
        String desc = template;
        for (int i = 0; i < ranks.size(); i++) {
            desc = desc.replace(String.format("r%d", i), ranks.get(i).getName
                    ());
        }
        return desc;
    }
}
