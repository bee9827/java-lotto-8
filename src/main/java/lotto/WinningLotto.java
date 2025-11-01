package lotto;

import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, LottoNumber bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
    }

    public WinningResult matching(Lotto lotto) {
        return WinningResult.of(matchCount(lotto), matchBonusNumber(lotto));
    }

    public boolean matchBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
