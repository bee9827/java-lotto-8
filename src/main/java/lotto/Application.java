package lotto;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputViewImpl();
        OutputView outputView = new OutputViewImpl();
        LottoMachine lottoMachine = new RandomLottoMachine();

        Controller controller = new Controller(inputView, outputView, lottoMachine);
        controller.run();
    }
}
