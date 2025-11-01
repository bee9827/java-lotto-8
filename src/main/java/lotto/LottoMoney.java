package lotto;

public class LottoMoney {
    public static final int UNIT = 1000;
    public static final double PERCENTAGE = 100;
    private long remainedMoney;
    private long usedMoney;

    public LottoMoney(long rest) {
        validateUnit(rest);
        this.remainedMoney = rest;
        usedMoney = 0;
    }

    private void validateUnit(long money) {
        if (money < UNIT || money % UNIT != 0) {
            throw new IllegalArgumentException(LottoErrorCode.MONEY_UNIT.getMessage());
        }
    }

    public int purchaseTicket() {
        long usingMoney = remainedMoney;
        remainedMoney = 0;
        usedMoney += usingMoney;
        return (int) usingMoney / UNIT;
    }

    public double getRevenueRate(Long revenue) {
        if (usedMoney == 0) {
            throw new IllegalStateException(LottoErrorCode.MONEY_UNIT.getMessage());
        }
        return revenue.doubleValue() / usedMoney * PERCENTAGE;
    }

    public long getRemainedMoney() {
        return remainedMoney;
    }
}
