package lotto.model;

import java.util.Objects;
import lotto.error.LottoErrorCode;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    private final int value;

    public LottoNumber(int value) {
        validateAll(value);
        this.value = value;
    }

    private void validateAll(int number) {
        validateRange(number);
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(LottoErrorCode.NUMBER_RANGE.getMessage());
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LottoNumber that)) {
            return false;
        }

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(value, o.value);
    }
}
