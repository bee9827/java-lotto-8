package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.controller.dto.LottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class LottoTest {
    public static final Lotto DEFAULT_LOTTO = new Lotto(getDefaultNumbers());

    public static List<Integer> getDefaultNumbers() {
        return List.of(1, 2, 3, 4, 5, 6);
    }

    public static Stream<Arguments> matchCount() {
        return Stream.of(
                Arguments.of(new Lotto(getDefaultNumbers()), 6),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), 5),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 44, 45)), 4),
                Arguments.of(new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                Arguments.of(new Lotto(List.of(1, 2, 42, 43, 44, 45)), 2),
                Arguments.of(new Lotto(List.of(1, 41, 42, 43, 44, 45)), 1),
                Arguments.of(new Lotto(List.of(40, 41, 42, 43, 44, 45)), 0)
        );
    }

    @DisplayName("matchCount(Lotto) : 동일한 숫자의 개수를 리턴한다.")
    @ParameterizedTest(name = "DEFAULT_LOTTO - ({0}) , result: {1}")
    @MethodSource
    void matchCount(Lotto other, int expectedMatchCount) {

        int matchCount = DEFAULT_LOTTO.matchCount(other);

        assertThat(matchCount).isEqualTo(expectedMatchCount);
    }

    @DisplayName("contains(int): 숫자가 포함 되어 있는지 확인한다")
    @ParameterizedTest(name = "DEFAULT_LOTTO - ({0}), result: {1}")
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

    @DisplayName("getSortedNumber(): 정렬된 로또 번호를 반환한다.")
    @Test
    void getSortedNumbers() {
        Lotto lotto = new Lotto(getDefaultNumbers().reversed());
        LottoDto expected = new LottoDto(getDefaultNumbers());

        assertThat(lotto.getSortedNumbers()).isEqualTo(expected);
    }

    @Nested
    @DisplayName("new Lotto():")
    class NewLotto {
        @DisplayName("[예외] - 로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
        @Test
        void numbersSizeIsNot6_thenThrowException() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("[예외] - 로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        @Test
        void duplicateNumbers_thenThrowException() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("[예외] - 로또 번호 콜렉션이 null 이라면 예외가 발생한다. ")
        @ParameterizedTest
        @NullSource
        void numbersIsNull_thenThrowException(List<Integer> numbers) {
            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
