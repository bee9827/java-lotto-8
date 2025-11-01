package lotto;

import java.util.List;

public class ViewFormatter {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public static String readPurchaseCost() {
        return "구입금액을 입력해 주세요.";
    }

    public static String readWinningNumbers() {
        return "당첨 번호를 입력해 주세요.";
    }

    public static String readBonusNumber() {
        return "보너스 번호를 입력해 주세요.";
    }

    public static String printTickets(List<Lotto> lottoTickets) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator())
                .append(lottoTickets.size())
                .append("개를 구매했습니다.")
                .append(System.lineSeparator());
        for (Lotto lotto : lottoTickets) {
            sb.append(lotto.toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String printWinningResult(List<WinningResult> results) {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계")
                .append(System.lineSeparator())
                .append("---")
                .append(System.lineSeparator());
        for (WinningResult result : WinningResult.getValues()) {
            if (result == WinningResult.NONE) {
                continue;
            }
            sb.append("%s (%,d원) - %d개%n"
                    .formatted(result.getCondition(), result.getPrize(), getCount(results, result)));
        }
        return sb.toString();
    }

    private static int getCount(List<WinningResult> results, WinningResult result) {
        return results.stream()
                .filter(r -> r == result)
                .toList()
                .size();
    }

    public static String printRevenueRate(double revenueRate) {
        return String.format("총 수익률은 %.1f%%입니다.", revenueRate);
    }

    public static String printError(String message) {
        return ERROR_PREFIX + message;
    }
}
