package lotto;

import java.util.List;

public interface OutputView {
    void printTickets(List<Lotto> lottoTickets);

    void printWinningResult(List<WinningResult> results);

    void printRevenueRate(double revenueRate);

    void printError(String message);
}
