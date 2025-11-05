package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum WinningRank {
    FIRST("1등", "6개 일치", 6, 2_000_000_000),
    SECOND("2등", "5개 일치, 보너스 볼 일치", 5, 30_000_000),
    THIRD("3등", "5개 일치", 5, 1_500_000),
    FOURTH("4등", "4개 일치", 4, 50_000),
    FIFTH("5등", "3개 일치", 3, 5_000),
    NONE("", "", 0, 0),
    ;
    private final String label;
    private final String condition;
    private final int matchCount;
    private final long prize;

    WinningRank(String label, String condition, int matchCount, long prize) {
        this.label = label;
        this.condition = condition;
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

    public static List<WinningRank> getValues() {
        return Arrays.stream(WinningRank.values())
                .toList()
                .reversed();
    }

    public String getLabel() {
        return label;
    }

    public String getCondition() {
        return condition;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
