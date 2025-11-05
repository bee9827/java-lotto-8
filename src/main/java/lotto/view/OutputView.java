package lotto.view;

import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.model.WinningRank;

public class OutputView {
    public void printTickets(List<LottoDto> lottoTickets) {
        System.out.println(ticketsFormat(lottoTickets));
    }

    public void printWinningResult(List<WinningRank> results) {
        System.out.print(winningResultFormat(results));
    }

    public void printRevenueRate(double revenueRate) {
        System.out.println(revenueRateFormat(revenueRate));
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

    private String winningResultFormat(List<WinningRank> results) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator())
                .append("당첨 통계").append(System.lineSeparator())
                .append("---").append(System.lineSeparator());
        for (WinningRank result : WinningRank.getValues()) {
            if (result == WinningRank.NONE) {
                continue;
            }
            sb.append("%s (%,d원) - %d개%n"
                    .formatted(result.getCondition(), result.getPrize(), getCount(results, result)));
        }
        return sb.toString();
    }

    private int getCount(List<WinningRank> results, WinningRank result) {
        return results.stream()
                .filter(r -> r == result)
                .toList()
                .size();
    }

    private String revenueRateFormat(double revenueRate) {
        return String.format("총 수익률은 %,.1f%%입니다.", revenueRate);
    }
}
