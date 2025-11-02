package lotto.model;

import java.util.List;
import lotto.error.LottoErrorCode;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        super(numbers);
        validateBonusNumberDuplicate(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumberDuplicate(Integer bonusNumber) {
        LottoNumber lottoNumber = new LottoNumber(bonusNumber);
        if (super.contains(lottoNumber)) {
            throw new IllegalArgumentException(LottoErrorCode.BONUS_NUMBER_DUPLICATED.getMessage());
        }
    }

    public WinningResult matching(Lotto lotto) {
        return WinningResult.of(matchCount(lotto), matchBonusNumber(lotto));
    }

    private boolean matchBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
