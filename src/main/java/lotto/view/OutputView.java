package lotto.view;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.WinningResult;

public interface OutputView {
    void printTickets(List<Lotto> lottoTickets);

    void printWinningResult(List<WinningResult> results);

    void printRevenueRate(double revenueRate);

    void printError(String message);
}
