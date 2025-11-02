package lotto.model;

import java.util.HashSet;
import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.error.LottoErrorCode;

public class Lotto {
    public static final int SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = toLottoNumbers(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers == null || numbers.size() != SIZE) {
            throw new IllegalArgumentException(LottoErrorCode.NUMBERS_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (isDuplicate(numbers)) {
            throw new IllegalArgumentException(LottoErrorCode.NUMBERS_DUPLICATED.getMessage());
        }
    }

    private boolean isDuplicate(List<Integer> numbers) {
        return new HashSet<>(numbers)
                .size() != numbers.size();
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    protected int matchCount(Lotto other) {
        return numbers.stream()
                .filter(other::contains)
                .toList()
                .size();
    }

    protected boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public LottoDto getSortedNumbers() {
        return new LottoDto(
                numbers.stream()
                        .sorted()
                        .map(LottoNumber::getValue)
                        .toList()
        );
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
