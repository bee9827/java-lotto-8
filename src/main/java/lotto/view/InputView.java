package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.util.Parser;

public class InputView {
    public static final String DEFAULT_DELIMITER = ",";

    public Long readCost() {
        System.out.println("구입금액을 입력해 주세요.");
        return Parser.toLong(Console.readLine());
    }

    public List<Integer> readWinningLottoNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Parser.splitNumbers(Console.readLine(), DEFAULT_DELIMITER);
    }

    public Integer readBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        return Parser.toInteger(Console.readLine());
    }
}
