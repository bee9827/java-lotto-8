package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    public static final Lotto DEFAULT_LOTTO = new Lotto(getDefaultNumbers());

    public static List<Integer> getDefaultNumbers() {
        return List.of(1, 2, 3, 4, 5, 6);
    }

    public static Stream<Arguments> matchCount() {
        return Stream.of(
                Arguments.of(getDefaultNumbers(), 6),
                Arguments.of(List.of(1, 2, 3, 4, 5, 45), 5),
                Arguments.of(List.of(1, 2, 3, 4, 44, 45), 4),
                Arguments.of(List.of(1, 2, 3, 43, 44, 45), 3),
                Arguments.of(List.of(1, 2, 42, 43, 44, 45), 2),
                Arguments.of(List.of(1, 41, 42, 43, 44, 45), 1),
                Arguments.of(List.of(40, 41, 42, 43, 44, 45), 0)
        );
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("matchCount() : 동일한 숫자의 개수를 리턴한다.")
    @ParameterizedTest
    @MethodSource
    void matchCount(List<Integer> numbers, int expectedMatchCount) {
        Lotto other = new Lotto(numbers);

        int matchCount = DEFAULT_LOTTO.matchCount(other);

        assertThat(matchCount).isEqualTo(expectedMatchCount);
    }

    @ParameterizedTest
    @CsvSource({
            "1,true",
            "6,true",
            "10,false",
            "45,false",
    })
    void contains(int number, boolean expected) {
        LottoNumber lottoNumber = new LottoNumber(number);
        boolean contains = DEFAULT_LOTTO.contains(lottoNumber);

        assertThat(contains).isEqualTo(expected);
    }
}
