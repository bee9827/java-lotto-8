package lotto.view;

import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.model.WinningResult;

public class OutputViewImpl implements OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Override
    public void printTickets(List<LottoDto> lottoTickets) {
        System.out.println(ticketsFormat(lottoTickets));
    }

    @Override
    public void printWinningResult(List<WinningResult> results) {
        System.out.print(winningResultFormat(results));
    }

    @Override
    public void printRevenueRate(double revenueRate) {
        System.out.println(revenueRateFormat(revenueRate));
    }

    @Override
    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    private String ticketsFormat(List<LottoDto> lottoTickets) {
        StringBuilder sb = new StringBuilder();
        sb.append("%n%d개를 구매했습니다.%n".formatted(lottoTickets.size()));
        for (LottoDto lotto : lottoTickets) {
            sb.append(lotto.numbers().toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    private String winningResultFormat(List<WinningResult> results) {
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

    private int getCount(List<WinningResult> results, WinningResult result) {
        return results.stream()
                .filter(r -> r == result)
                .toList()
                .size();
    }

    private String revenueRateFormat(double revenueRate) {
        return String.format("총 수익률은 %.1f%%입니다.", revenueRate);
    }
}
