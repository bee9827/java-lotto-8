package lotto.view;

import java.util.List;
import lotto.controller.dto.LottoDto;
import lotto.model.WinningRank;

public class OutputView {
    public void printTickets(List<LottoDto> lottoTickets) {
        System.out.println(ticketsFormat(lottoTickets));
    }

    public void printWinningResult(WinningResult results) {
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

    private String winningResultFormat(WinningResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator())
                .append("당첨 통계").append(System.lineSeparator())
                .append("---").append(System.lineSeparator());
        for (WinningRank rank : getRanks()) {
            sb.append("%s (%,d원) - %,d개%n"
                    .formatted(getCondition(rank), rank.getPrize(), result.getCount(rank)));
        }
        return sb.toString();
    }

    private String getCondition(WinningRank rank) {
        String conditionFormat = rank.getMatchCount() + "개 일치";
        if (rank == WinningRank.SECOND) {
            conditionFormat += ", 보너스 볼 일치";
        }
        return conditionFormat;
    }

    private List<WinningRank> getRanks() {
        return List.of(WinningRank.FIFTH, WinningRank.FOURTH, WinningRank.THIRD, WinningRank.SECOND, WinningRank.FIRST);
    }

    private String revenueRateFormat(double revenueRate) {
        return String.format("총 수익률은 %,.1f%%입니다.", revenueRate);
    }
}
