package lotto;

import java.util.List;

public class OutputViewImpl implements OutputView {
    @Override
    public void printTickets(List<Lotto> lottoTickets) {
        System.out.println(ViewFormatter.printTickets(lottoTickets));
    }

    @Override
    public void printWinningResult(List<WinningResult> results) {
        System.out.print(ViewFormatter.printWinningResult(results));
    }

    @Override
    public void printRevenueRate(double revenueRate) {
        System.out.println(ViewFormatter.printRevenueRate(revenueRate));
    }

    @Override
    public void printError(String message) {
        System.out.println(ViewFormatter.printError(message));
    }
}
