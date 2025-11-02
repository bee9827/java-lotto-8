package lotto.view;

import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.model.Lotto;
import lotto.model.WinningResult;

public class OutputViewImpl implements OutputView {
    @Override
    public void printTickets(List<LottoDto> lottoTickets) {
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
