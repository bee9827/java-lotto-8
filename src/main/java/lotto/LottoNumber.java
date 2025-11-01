package lotto;

import java.util.Objects;

public class LottoNumber {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int number;

    public LottoNumber(int number) {
        validateAll(number);
        this.number = number;
    }

    private void validateAll(int number) {
        validateRange(number);
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_NUMBER.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LottoNumber that)) {
            return false;
        }

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
