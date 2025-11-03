package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Scanner;
import lotto.util.Parser;

public class InputView {
    public long readPurchaseCost() {
        System.out.println("구입금액을 입력해 주세요.");
        return Parser.toLong(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String values = Console.readLine();
        Scanner scanner = new Scanner(System.in);

        return Parser.split(values).stream()
                .map(Parser::toInteger)
                .toList();
    }

    public Integer readBonusNumber() {
        System.out.println(System.lineSeparator() + "보너스 번호를 입력해 주세요.");
        return Parser.toInteger(Console.readLine());
    }

    public void close() {
        Console.close();
    }
}
