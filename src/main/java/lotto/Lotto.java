package lotto;

import java.util.List;

public class Lotto {
    public static final int NUMBER_LENGTH = 6;
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (numbersNotInRange(numbers)) {
            throw new IllegalArgumentException("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.");
        }
        if (getDistinctCount(numbers) != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean numbersNotInRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < NUMBER_MIN || number > NUMBER_MAX);
    }

    private long getDistinctCount(List<Integer> numbers) {
        return numbers.stream().distinct().count();
    }
}
