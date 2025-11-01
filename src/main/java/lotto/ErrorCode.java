package lotto;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER("로또 번호는 %d~%d 값 이어야 합니다. 현재 값 : %d", 3),

    INVALID_ARGS("발생한 에러: %s, 필요한 가변 인자는 %d개 입니다. 현재 값 : %d", 3);

    private final String message;
    private final int argsSize;

    ErrorCode(String message, int argsSize) {
        this.message = message;
        this.argsSize = argsSize;
    }

    public String format(Object... args) {
        if (args.length != argsSize) {
            throw new IllegalStateException(INVALID_ARGS.format(message, argsSize, args.length));
        }
        return String.format(message, args);
    }
}
