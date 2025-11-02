package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMoneyTest {
    @DisplayName("purchaseTicket(): ")
    @ParameterizedTest(name = "{0}.purchaseTicket(), result: {1}")
    @CsvSource({
            "1000,1",
            "10000,10"
    })
    void purchaseTicket(long money, int expectedPurchaseTicket) {
        LottoMoney lottoMoney = new LottoMoney(money);

        assertThat(lottoMoney.purchaseTicket()).isEqualTo(expectedPurchaseTicket);
    }

    @DisplayName("getRevenueRate(long): ")
    @ParameterizedTest(name = "{0}.getRevenueRate({1}), result: {2}")
    @CsvSource({
            "1000,1000,100",
            "1000,2000,200",
            "1000,0,0",
            "10000,1000,10"
    })
    void getRevenueRate(long money, long revenue, double revenueRate) {
        LottoMoney lottoMoney = new LottoMoney(money);
        lottoMoney.purchaseTicket();
        assertThat(lottoMoney.getRevenueRate(revenue)).isEqualTo(revenueRate);
    }

    @DisplayName("new Money(long): 생성에 성공한다.")
    @Nested
    class constructor {
        @ParameterizedTest(name = "({0})")
        @CsvSource({
                "1000",
                "2000",
                "3000"
        })
        void money(long money) {
            LottoMoney lottoMoney = new LottoMoney(money);

            assertThat(lottoMoney.getRemainedMoney()).isEqualTo(money);
        }

        @DisplayName("[예외] - 1000단위가 아닌 숫자라면 IllegalArgumentException을 반환한다.")
        @ParameterizedTest(name = "({0})")
        @CsvSource({
                "-1000",
                "0",
                "999",
                "1001"
        })
        void invalidUnit(long money) {
            assertThatThrownBy(() -> new LottoMoney(money))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
