package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    public static final WinningLotto DEFAULT_WINNING_LOTTO
            = new WinningLotto(LottoTest.getDefaultNumbers(), getDEFAULT_BONUS_NUMBER());

    private static Integer getDEFAULT_BONUS_NUMBER() {
        return 7;
    }

    public static Stream<Arguments> matching() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), WinningResult.FIRST),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), WinningResult.SECOND),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), WinningResult.THIRD),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 44, 45)), WinningResult.FOURTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 43, 44, 45)), WinningResult.FIFTH),
                Arguments.of(new Lotto(List.of(1, 2, 42, 43, 44, 45)), WinningResult.NONE),
                Arguments.of(new Lotto(List.of(1, 41, 42, 43, 44, 45)), WinningResult.NONE),
                Arguments.of(new Lotto(List.of(40, 41, 42, 43, 44, 45)), WinningResult.NONE)
        );
    }

    @DisplayName("new WinningLotto(List,Integer): [예외] - 중복된 보너스번호")
    @Test
    void constructor() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("matching: 각 등수에 맞는 Enum을 리턴한다")
    @ParameterizedTest(name = "({0}), result: {1}")
    @MethodSource
    void matching(Lotto lotto, WinningResult expected) {
        WinningResult result = DEFAULT_WINNING_LOTTO.matching(lotto);

        assertThat(result).isEqualTo(expected);
    }
}
