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
    private static final int DEFAULT_BONUS_NUMBER = 7;
    private static final WinningLotto DEFAULT_WINNING_LOTTO
            = new WinningLotto(LottoTest.getDefaultNumbers(), getDefaultBonusNumbers());

    public static int getDefaultBonusNumbers() {
        return DEFAULT_BONUS_NUMBER;
    }

    public static WinningLotto getDefaultWinningLotto() {
        return DEFAULT_WINNING_LOTTO;
    }

    public static Stream<Arguments> matching() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), WinningRank.FIRST),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), WinningRank.SECOND),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), WinningRank.THIRD),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 44, 45)), WinningRank.FOURTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 43, 44, 45)), WinningRank.FIFTH),
                Arguments.of(new Lotto(List.of(1, 2, 42, 43, 44, 45)), WinningRank.NONE),
                Arguments.of(new Lotto(List.of(1, 41, 42, 43, 44, 45)), WinningRank.NONE),
                Arguments.of(new Lotto(List.of(40, 41, 42, 43, 44, 45)), WinningRank.NONE)
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
    void matching(Lotto lotto, WinningRank expected) {
        WinningRank result = DEFAULT_WINNING_LOTTO.matching(lotto);

        assertThat(result).isEqualTo(expected);
    }
}
