package lotto.controller;

import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoMoney;
import lotto.model.WinningLotto;
import lotto.model.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public Controller(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        try {
            LottoMoney lottoMoney = new LottoMoney(inputView.readPurchaseCost());
            List<Lotto> lottoTickets = purchaseTickets(lottoMoney);

            WinningLotto winningLotto = createWinningLotto();
            printResults(lottoTickets, winningLotto, lottoMoney);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private List<Lotto> purchaseTickets(LottoMoney lottoMoney) {
        List<Lotto> lottoTickets = lottoMachine.issueTickets(lottoMoney.purchaseTicket());
        List<LottoDto> ticketsDto = lottoTickets.stream()
                .map(Lotto::getSortedNumbers)
                .toList();
        outputView.printTickets(ticketsDto);
        return lottoTickets;
    }

    private void printResults(List<Lotto> lottoTickets, WinningLotto winningLotto, LottoMoney lottoMoney) {
        List<WinningResult> results = lottoTickets.stream()
                .map(winningLotto::matching)
                .toList();
        Long revenue = results.stream()
                .map(WinningResult::getPrize)
                .reduce(0L, Long::sum);

        outputView.printWinningResult(results);
        outputView.printRevenueRate(lottoMoney.getRevenueRate(revenue));
    }

    private WinningLotto createWinningLotto() {
        List<Integer> numbers = inputView.readWinningNumbers();
        Integer bonusNumber = inputView.readBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }
}
