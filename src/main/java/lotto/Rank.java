package lotto;

public enum Rank {
    FIRST(6L, 2_000_000_000L),
    SECOND(5L, 30_000_000L),
    THIRD(5L, 1_500_000L),
    FOURTH(4L, 50_000L),
    FIFTH(3L, 5_000L),
    NONE(null, 0L),
    ;

    private final Long matchCount;
    private final Long prize;

    Rank(Long matchCount, Long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static Rank of(Long matchCount, boolean matchBonus) {
        for (Rank rank : Rank.values()) {
            if (rank.matchCount == null) {
                continue;
            }
            if (rank != Rank.SECOND && rank.matchCount.equals(matchCount)) {
                return rank;
            }
            if (rank == Rank.SECOND && rank.matchCount.equals(matchCount) && matchBonus) {
                return rank;
            }
        }
        return NONE;
    }

    public Long getPrize() {
        return prize;
    }
}
