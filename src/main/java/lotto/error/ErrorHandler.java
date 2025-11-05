package lotto.error;

import java.util.function.Supplier;

public class ErrorHandler {
    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final int TRY_COUNT = 10;

    public static void illegalArgument(Runnable runnable) {
        int tryCount = TRY_COUNT;
        while (--tryCount > 0) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new IllegalArgumentException("반복 횟수 초과");
    }

    public static <T> T illegalArgument(Supplier<T> supplier) {
        int tryCount = TRY_COUNT;
        while (--tryCount > 0) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new IllegalArgumentException("반복 횟수 초과");
    }

    private static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
