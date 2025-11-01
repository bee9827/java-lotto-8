package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputViewImpl implements InputView {
    @Override
    public long readPurchaseCost() {
        System.out.println(ViewFormatter.readPurchaseCost());
        return Parser.toLong(Console.readLine());
    }

    @Override
    public List<Integer> readWinningNumbers() {
        System.out.println(ViewFormatter.readWinningNumbers());
        String values = Console.readLine();

        return Parser.split(values).stream()
                .map(Parser::toInteger)
                .toList();
    }

    @Override
    public Integer readBonusNumber() {
        return Parser.toInteger(Console.readLine());
    }
}
