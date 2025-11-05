package lotto.view;

import java.util.Map;
import lotto.model.WinningRank;

public class WinningResult {
    private final Map<WinningRank, Long> result;

    public WinningResult(Map<WinningRank, Long> result) {
        this.result = result;
    }

    public Long getCount(WinningRank rank) {
        return result.getOrDefault(rank, 0L);
    }
}
