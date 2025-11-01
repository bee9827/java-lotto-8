package lotto;

public enum ErrorCode {
    INVALID_LOTTO_NUMBER("로또 번호는 %d ~ %d 값 이어야 합니다.".formatted(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)),
    INVALID_LOTTO_SIZE("로또 번호는 %d개여야 합니다.".formatted(Lotto.SIZE)),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
