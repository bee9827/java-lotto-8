package lotto;

import java.util.Objects;

public class LottoNumber {
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;
    private final Integer number;

    public LottoNumber(Integer number) {
        if (number < NUMBER_MIN || number > NUMBER_MAX) {
            throw new IllegalArgumentException(
                    "로또 번호는 %d에서 %d 사이의 숫자여야 합니다.".formatted(NUMBER_MIN, NUMBER_MAX));
        }
        this.number = number;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LottoNumber that)) {
            return false;
        }

        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public Integer getNumber() {
        return number;
    }
}
