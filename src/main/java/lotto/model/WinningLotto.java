package lotto.model;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        super(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public WinningResult matching(Lotto lotto) {
        return WinningResult.of(matchCount(lotto), matchBonusNumber(lotto));
    }

    private boolean matchBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
