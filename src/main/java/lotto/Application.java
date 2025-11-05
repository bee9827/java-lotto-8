package lotto;

import lotto.controller.LottoController;
import lotto.model.NumberGenerator;
import lotto.model.RandomNumberGenerator;
import lotto.repository.LottoRepository;
import lotto.repository.WinningLottoRepository;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService(new WinningLottoRepository(), new LottoRepository());
        LottoController lottoController = new LottoController(inputView, outputView, lottoService, numberGenerator);

        lottoController.run();
    }
}
