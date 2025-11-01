package lotto;

public enum LottoErrorCode {
    NUMBER_RANGE("로또 번호는 %d ~ %d 값 이어야 합니다.".formatted(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)),
    NUMBERS_SIZE("로또 번호는 %d개여야 합니다.".formatted(Lotto.SIZE)),
    NUMBERS_DUPLICATED("로또 번호는 중복될 수 없습니다."),
    MONEY_UNIT("로또 돈은 %d 단위로 입력해 주세요".formatted(LottoMoney.UNIT)),
    MONEY_NOT_USED("사용된 돈이 없습니다."),

    NUMBER_FORMAT("숫자를 입력해 주세요"),
    ;
    private final String message;

    LottoErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
