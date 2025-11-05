package lotto.controller;

import lotto.error.ErrorHandler;
import lotto.model.LottoMoney;
import lotto.model.NumberGenerator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;


public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final NumberGenerator numberGenerator;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService,
                           NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        LottoMoney lottoMoney = ErrorHandler.illegalArgument(() ->
                new LottoMoney(inputView.readPurchaseCost()));

        ErrorHandler.illegalArgument(() ->
                lottoService.addLottoTickets(numberGenerator, lottoMoney.purchaseTicket()));
        outputView.printTickets(lottoService.getTickets());

        ErrorHandler.illegalArgument(() ->
                lottoService.addWinningLotto(inputView.readWinningNumbers(), inputView.readBonusNumber()));
        outputView.printWinningResult(lottoService.getResults());

        Long totalRevenue = lottoService.getRevenue();
        double revenueRate = lottoMoney.getRevenueRate(totalRevenue);
        outputView.printRevenueRate(revenueRate);
    }
}
