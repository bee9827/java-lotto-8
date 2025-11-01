package lotto;

public enum ErrorCode {
    LOTTO_NUMBER_RANGE("로또 번호는 %d ~ %d 값 이어야 합니다.".formatted(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)),
    LOTTO_SIZE("로또 번호는 %d개여야 합니다.".formatted(Lotto.SIZE)),
    LOTTO_DUPLICATED("로또 번호는 중복될 수 없습니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
