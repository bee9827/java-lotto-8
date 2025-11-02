package lotto.model;

import lotto.error.LottoErrorCode;

public class LottoMoney {
    public static final int UNIT = 1000;
    public static final double PERCENTAGE = 100;
    private static final int DEFAULT_USED_MONEY = 0;
    private static final int REMAINING = 0;

    private long remainedMoney;
    private long usedMoney;

    public LottoMoney(long rest) {
        validateUnit(rest);
        this.remainedMoney = rest;
        usedMoney = DEFAULT_USED_MONEY;
    }

    private void validateUnit(long money) {
        if (money < UNIT || checkInvalidUnit(money)) {
            throw new IllegalArgumentException(LottoErrorCode.MONEY_UNIT.getMessage());
        }
    }

    private boolean checkInvalidUnit(long money) {
        return money % UNIT != REMAINING;
    }

    public int purchaseTicket() {
        long usingMoney = remainedMoney;
        remainedMoney -= usingMoney;
        usedMoney += usingMoney;
        return (int) usingMoney / UNIT;
    }

    public double getRevenueRate(Long revenue) {
        if (usedMoney == DEFAULT_USED_MONEY) {
            throw new IllegalStateException(LottoErrorCode.MONEY_NOT_USED.getMessage());
        }
        return revenue.doubleValue() / usedMoney * PERCENTAGE;
    }

    public long getRemainedMoney() {
        return remainedMoney;
    }
}
