package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    public static Stream<Arguments> of() {
        return Stream.of(
                Arguments.of(Rank.FIRST, 6L, false),
                Arguments.of(Rank.SECOND, 5L, true),
                Arguments.of(Rank.THIRD, 5L, false),
                Arguments.of(Rank.FOURTH, 4L, false),
                Arguments.of(Rank.FIFTH, 3L, false),
                Arguments.of(Rank.NONE, 2L, false),
                Arguments.of(Rank.NONE, 1L, false),
                Arguments.of(Rank.NONE, 0L, false),
                Arguments.of(Rank.NONE, 2L, true),
                Arguments.of(Rank.NONE, 1L, true),
                Arguments.of(Rank.NONE, 0L, true),
                Arguments.of(Rank.FOURTH, 4L, true),
                Arguments.of(Rank.FIFTH, 3L, true)
        );
    }

    @ParameterizedTest(name = "{0} {1}개 번호 일치 + 보너스 번호 일치 여부: {2}")
    @MethodSource
    @DisplayName("당첨 개수와 보너스볼 일치 여부로 당첨 등수를 반환한다.")
    void of(Rank rank, Long matchCount, Boolean matchBonus) {
        assertThat(Rank.of(matchCount, matchBonus)).isEqualTo(rank);
    }
}
