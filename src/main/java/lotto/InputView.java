package lotto;

import java.util.List;

public interface InputView {
    long readPurchaseCost();

    List<Integer> readWinningNumbers();

    Integer readBonusNumber();
}
