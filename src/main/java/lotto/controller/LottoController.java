package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.error.ErrorHandler;
import lotto.model.Lotto;
import lotto.model.LottoMoney;
import lotto.model.LottoNumber;
import lotto.model.NumberGenerator;
import lotto.model.WinningLotto;
import lotto.model.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;


public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        LottoMoney lottoMoney = ErrorHandler.illegalArgument(() ->
                new LottoMoney(inputView.readPurchaseCost()));

        List<Lotto> lottoTickets = ErrorHandler.illegalArgument(() ->
                issueTickets(numberGenerator, lottoMoney.purchaseTicket()));
        outputView.printTickets(getTicketsDto(lottoTickets));

        WinningLotto winningLotto = ErrorHandler.illegalArgument(() ->
                new WinningLotto(inputView.readWinningNumbers(), inputView.readBonusNumber()));
        List<WinningResult> results = getResults(lottoTickets, winningLotto);
        outputView.printWinningResult(results);

        Long totalRevenue = getRevenue(lottoTickets, winningLotto);
        double revenueRate = lottoMoney.getRevenueRate(totalRevenue);
        outputView.printRevenueRate(revenueRate);
    }

    private List<Lotto> issueTickets(NumberGenerator numberGenerator, int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = numberGenerator.uniqueNumbers(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE,
                    Lotto.SIZE);
            lottoTickets.add(new Lotto(numbers));
        }
        return lottoTickets;
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
