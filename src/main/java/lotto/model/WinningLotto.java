package lotto.model;

import java.util.List;
import lotto.error.LottoErrorCode;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> lotto, Integer bonusNumber) {
        this.lotto = new Lotto(lotto);
        validateBonusNumberDuplicate(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumberDuplicate(Integer bonusNumber) {
        LottoNumber lottoNumber = new LottoNumber(bonusNumber);
        if (lotto.contains(lottoNumber)) {
            throw new IllegalArgumentException(LottoErrorCode.BONUS_NUMBER_DUPLICATED.getMessage());
        }
    }

    public WinningResult matching(Lotto lotto) {
        return WinningResult.of(lotto.matchCount(this.lotto), matchBonusNumber(lotto));
    }

    public long revenue(Lotto lotto) {
        WinningResult result = matching(lotto);
        return result.getPrize();
    }

    private boolean matchBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
