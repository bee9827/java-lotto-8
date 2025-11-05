package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum WinningRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0),
    ;
    private final int matchCount;
    private final long prize;

    WinningRank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static WinningRank of(int matchCount, boolean matchBonusBall) {
        for (WinningRank result : WinningRank.values()) {
            if (checkCondition(result, matchCount, matchBonusBall)) {
                return result;
            }
        }
        return NONE;
    }

    private static boolean checkCondition(WinningRank result, int matchCount, boolean matchBonusBall) {
        if (result == SECOND
                && SECOND.matchCount == matchCount
                && matchBonusBall) {
            return true;
        }
        return result != SECOND
                && matchCount == result.matchCount;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
