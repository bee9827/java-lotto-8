package lotto;

public class LottoMoney {
    public static final int UNIT = 1000;
    public static final int PERCENTAGE = 100;
    private final long money;

    public LottoMoney(long money) {
        validateUnit(money);
        this.money = money;
    }

    private void validateUnit(long money) {
        if (money < UNIT || money % UNIT != 0) {
            throw new IllegalArgumentException(LottoErrorCode.MONEY_UNIT.getMessage());
        }
    }

    public int purchaseTicket() {
        return (int) money / UNIT;
    }

    public double getRevenueRate(Long revenue) {
        return revenue.doubleValue() / money * PERCENTAGE;
    }

    public long getMoney() {
        return money;
    }
}
