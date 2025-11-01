package lotto;

import lotto.controller.Controller;
import lotto.model.LottoMachine;
import lotto.model.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.InputViewImpl;
import lotto.view.OutputView;
import lotto.view.OutputViewImpl;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputViewImpl();
        OutputView outputView = new OutputViewImpl();
        LottoMachine lottoMachine = new RandomLottoMachine();

        Controller controller = new Controller(inputView, outputView, lottoMachine);
        controller.run();
    }
}
