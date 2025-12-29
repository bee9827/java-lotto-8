package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static final Long MONEY_UNIT = 1_000L;

    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchaseLotto(int cost) {
        if (cost % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("%,d 단위로 입력해 주세요".formatted(cost));
        }
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < cost / MONEY_UNIT; i++) {
            lottos.add(purchaseLotto());
        }
        return lottos;
    }

    public float revenueRate(Long cost, Long revenue) {
        return (float) revenue / cost * 100;
    }

    private Lotto purchaseLotto() {
        return new Lotto(numberGenerator.generate(LottoNumber.NUMBER_MIN, LottoNumber.NUMBER_MAX, Lotto.NUMBER_LENGTH));
    }
}
