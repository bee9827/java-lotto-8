package lotto.view;

import java.util.List;

public interface InputView {
    long readPurchaseCost();

    List<Integer> readWinningNumbers();

    Integer readBonusNumber();

    void close();
}
