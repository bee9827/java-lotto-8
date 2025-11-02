package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.util.Parser;

public class InputViewImpl implements InputView {
    @Override
    public long readPurchaseCost() {
        System.out.println("구입금액을 입력해 주세요.");
        return Parser.toLong(Console.readLine());
    }

    @Override
    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String values = Console.readLine();

        return Parser.split(values).stream()
                .map(Parser::toInteger)
                .toList();
    }

    @Override
    public Integer readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Parser.toInteger(Console.readLine());
    }

    @Override
    public void close() {
        Console.close();
    }
}
