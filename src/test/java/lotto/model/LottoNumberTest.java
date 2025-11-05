package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoNumberTest {
    @ParameterizedTest(name = "{0} equals {1}, result: {2}")
    @CsvSource(value = {
            "1,1,true",
            "2,1,false",
            "1,2,false"
    })
    void isMatch(int LottoNumber, int otherLottoNumber, boolean expected) {
        //given
        LottoNumber first = new LottoNumber(LottoNumber);
        LottoNumber second = new LottoNumber(otherLottoNumber);

        //when
        boolean match = first.equals(second);

        //then
        assertThat(match).isEqualTo(expected);
    }

    @Nested
    @DisplayName("new LottoNumber(int) : ")
    class constructor {
        @DisplayName("int 타입 기본 생성에 성공한다.")
        @ParameterizedTest(name = "new LottoNumber({0})")
        @CsvSource(value = {
                "1",
                "2",
                "44",
                "45",
        })
        void intConstructor(int value) {
            LottoNumber lottoNumber = new LottoNumber(value);

            assertThat(lottoNumber.getValue()).isEqualTo(value);
        }

        @DisplayName("[예외] 범위 밖의 값이면 IllegalArgument를 반환한다.")
        @ParameterizedTest(name = "new LottoNumber({0})")
        @CsvSource({
                "0",
                "-1",
                "46"
        })
        void invalidRange(int value) {
            assertThatThrownBy(() -> new LottoNumber(value))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
