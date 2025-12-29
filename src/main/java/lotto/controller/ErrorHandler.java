package lotto.controller;

import java.util.function.Supplier;
import lotto.view.OutputView;

public class ErrorHandler {
    public static void runWithRetry(Runnable runnable, OutputView outputView) {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException exception) {
                outputView.printError(exception.getMessage());
            }
        }
    }

    public static <T> T runWithRetry(Supplier<T> supplier, OutputView outputView) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printError(exception.getMessage());
            }
        }
    }

}
