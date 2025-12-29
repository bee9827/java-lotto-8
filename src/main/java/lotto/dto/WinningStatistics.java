package lotto.dto;

import java.util.Map;
import lotto.Rank;

public record WinningStatistics(
        Map<Rank, Long> ranks,
        float revenueRate
) {
}
