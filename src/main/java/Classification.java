import java.util.List;

public enum Classification {
    STRAIGHT_FLUSH("r0-high_straight_flush"),
    FOUR_OF_A_KIND("Four_r0s"),
    FULL_HOUSE("r0s_full_of_r1s"),
    FLUSH("r0-high_flush"),
    STRAIGHT("r0-high_straight"),
    THREE_OF_A_KIND("Three_r0s"),
    TWO_PAIR("r0s_over_r1s"),
    ONE_PAIR("Pair_of_r0s"),
    HIGH_CARD("r0-high");

    private String desc;

    Classification (String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(List<Rank> ranks) {
        for (int i = 0; i < ranks.size(); i++) {
            this.desc = this.desc.replace(String.format("r%d", i), ranks.get(i).toString());
        }
    }
}
