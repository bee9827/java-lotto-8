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
            throw new IllegalArgumentException("로또 번호는 %d개여야 합니다.".formatted(NUMBER_LENGTH));
        }
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private long getDistinctCount(List<Integer> numbers) {
        return numbers.stream().distinct().count();
    }

    public long matchCount(Lotto other) {
        return other.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    protected boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
