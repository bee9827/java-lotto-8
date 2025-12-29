package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.Rank;
import lotto.dto.WinningStatistics;

public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatus(WinningStatistics winningStatistics) {
        Map<Rank, Long> ranks = winningStatistics.ranks();

        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank rank : getRanksOrder()) {
            String condition = getCondition(rank);
            Long prize = rank.getPrize();
            Long count = ranks.getOrDefault(rank, 0L);
            System.out.printf("%s (%,d원) - %d개%n", condition, prize, count);
        }
        System.out.printf("총 수익률은 %.1f%%입니다.%n", winningStatistics.revenueRate());
        System.out.println("---");
    }

    private String getCondition(Rank rank) {
        String condition = rank.getMatchCount() + "개 일치";
        if (rank == Rank.SECOND) {
            condition += ", 보너스 볼 일치";
        }
        return condition;
    }

    private List<Rank> getRanksOrder() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIFTH);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.THIRD);
        ranks.add(Rank.SECOND);
        ranks.add(Rank.FIRST);

        return ranks;
    }
}
