package lotto;

import lotto.controller.LottoController;
import lotto.model.NumberGenerator;
import lotto.model.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoController lottoController = new LottoController(inputView, outputView, numberGenerator);

        lottoController.run();
    }
}
