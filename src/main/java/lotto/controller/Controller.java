package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.LottoMachine;
import lotto.WinningLotto;
import lotto.dto.WinningStatistics;
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
        List<Lotto> lottos = ErrorHandler.runWithRetry(() ->
                lottoMachine.purchaseLotto(inputView.readCost()), outputView);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = ErrorHandler.runWithRetry(() ->
                lottoMachine.createWinningLotto(inputView.readWinningLottoNumbers(), inputView.readBonusNumber()), outputView);

        WinningStatistics winningStatistics = new WinningStatistics(
                lottoMachine.getRanks(winningLotto, lottos),
                lottoMachine.revenueRate(winningLotto, lottos));
        outputView.printStatus(winningStatistics);
    }
}
