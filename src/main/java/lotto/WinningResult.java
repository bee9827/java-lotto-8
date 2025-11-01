package lotto;

public enum WinningResult {
    FIRST("1등", "6개 번호 일치", 6, 2_000_000_000),
    SECOND("2등", "5개 번호 + 보너스 번호 일치", 5, 30_000_000),
    THIRD("3등", "5개 번호 일치", 5, 1_500_000),
    FOURTH("4등", "4개 번호 일치", 5, 50_000),
    FIFTH("5등", "3개 번호 일치", 5, 5_000),
    NONE("", "", 0, 0),
    ;
    private final String label;
    private final String condition;
    private final int matchCount;
    private final long prize;

    WinningResult(String label, String condition, int matchCount, long prize) {
        this.label = label;
        this.condition = condition;
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static WinningResult of(int matchCount, boolean matchBonusBall) {
        if (checkSecond(matchCount, matchBonusBall)) {
            return SECOND;
        }
        for (WinningResult result : WinningResult.values()) {
            if (result.matchCount == matchCount) {
                return result;
            }
        }
        return NONE;
    }

    private static boolean checkSecond(int matchCount, boolean matchBonusBall) {
        return matchCount == WinningResult.SECOND.getMatchCount()
                && matchBonusBall;
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
