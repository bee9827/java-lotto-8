package lotto.error;

public class ErrorHandler {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public static void illegalArgument(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            printError(e.getMessage());
        }
    }

    private static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
