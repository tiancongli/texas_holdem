import java.util.List;

public enum Classification {

    HIGH_CARD("r0-high"),
    ONE_PAIR("Pair_of_r0s"),
    TWO_PAIR("r0s_over_r1s"),
    THREE_OF_A_KIND("Three_r0s"),
    STRAIGHT("r0-high_straight"),
    FLUSH("r0-high_flush"),
    FULL_HOUSE("r0s_full_of_r1s"),
    FOUR_OF_A_KIND("Four_r0s"),
    STRAIGHT_FLUSH("r0-high_straight_flush");

    private String template;

    Classification (String template) {
        this.template = template;
    }

    public String getDescByRanks(List<Rank> ranks) {
        String desc = this.template;
        for (int i = 0; i < ranks.size(); i++) {
            desc = desc.replace(String.format("r%d", i), ranks.get(i).getName());
        }
        return desc;
    }
}
