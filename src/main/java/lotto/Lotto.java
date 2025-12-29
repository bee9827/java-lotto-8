package lotto;

import java.util.List;

public class Lotto {
    public static final int NUMBER_LENGTH = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private long getDistinctCount(List<Integer> numbers) {
        return numbers.stream().distinct().count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }
}
