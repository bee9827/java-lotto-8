package lotto;

import java.util.List;

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
            List<Lotto> lottoTickets = lottoMachine.issueTickets(lottoMoney.purchaseTicket());
            outputView.printTickets(lottoTickets);

            List<Integer> numbers = inputView.readWinningNumbers();
            Integer bonusNumber = inputView.readBonusNumber();
            WinningLotto winningLotto = new WinningLotto(numbers, bonusNumber);

            List<WinningResult> results = lottoTickets.stream()
                    .map(winningLotto::matching)
                    .toList();
            Long revenue = results.stream()
                    .map(WinningResult::getPrize)
                    .reduce(0L, Long::sum);

            outputView.printWinningResult(results);
            outputView.printRevenueRate(lottoMoney.getRevenueRate(revenue));
        }catch (IllegalArgumentException e){
            outputView.printError(e.getMessage());
        }
    }
}
