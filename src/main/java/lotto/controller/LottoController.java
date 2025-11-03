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

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        try {
            LottoMoney lottoMoney = new LottoMoney(inputView.readPurchaseCost());
            List<Lotto> lottoTickets = lottoMachine.issueTickets(lottoMoney.purchaseTicket());
            outputView.printTickets(getTicketsDto(lottoTickets));

            WinningLotto winningLotto = new WinningLotto(inputView.readWinningNumbers(), inputView.readBonusNumber());
            List<WinningResult> results = getResults(lottoTickets, winningLotto);
            outputView.printWinningResult(results);

            double revenueRate = lottoMoney.getRevenueRate(getRevenue(lottoTickets, winningLotto));
            outputView.printRevenueRate(revenueRate);
        } finally {
            inputView.close();
        }
    }

    private List<LottoDto> getTicketsDto(List<Lotto> lottoTickets) {
        return lottoTickets.stream()
                .map(Lotto::getSortedNumbers)
                .map(LottoDto::new)
                .toList();
    }

    private List<WinningResult> getResults(List<Lotto> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::matching)
                .toList();
    }

    private Long getRevenue(List<Lotto> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::revenue)
                .reduce(0L, Long::sum);
    }
}
