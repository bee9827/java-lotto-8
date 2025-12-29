package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @DisplayName("new Lotto(): ")
    @Nested
    class Constructor {
        @ParameterizedTest(name = "로또 번호 개수: {0}")
        @DisplayName("로또_번호의_개수가_6개가_아니면_예외가_발생한다")
        @CsvSource({
                "7,1,2,3,4,5,6",
                "4,1,2,3"}
        )
        void 로또_번호의_개수가_6개가_아니면_예외가_발생한다(String numbers) {
            assertThatThrownBy(() -> new Lotto(getNumbers(numbers)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "예외 값: {0}")
        @DisplayName("로또 번호가 1~45가 아니라면 예외가 발생한다.")
        @CsvSource({
                "0,1,2,3,4,5",
                "46,1,2,3,4,5"
        })
        void numbersInvalidRange(String numbers) {
            assertThatThrownBy(() -> new Lotto(getNumbers(numbers)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        private List<Integer> getNumbers(String numbers) {
            return Arrays.stream(numbers.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        @Test
        void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
}
