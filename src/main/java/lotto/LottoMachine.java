package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {
    public static final Long MONEY_UNIT = 1_000L;

    private final NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> purchaseLotto(Long cost) {
        if (cost % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("%,d 단위로 입력해 주세요".formatted(MONEY_UNIT));
        }
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < cost / MONEY_UNIT; i++) {
            lottos.add(purchaseLotto());
        }
        return lottos;
    }

    public WinningLotto createWinningLotto(List<Integer> lotto, Integer bonusNumber) {
        return new WinningLotto(new Lotto(lotto), new LottoNumber(bonusNumber));
    }

    public float revenueRate(WinningLotto winningLotto, List<Lotto> lottos) {
        if(lottos.isEmpty()) return 0;
        long revenue = getRevenue(winningLotto, lottos);
        return (float) revenue / (lottos.size() * MONEY_UNIT) * 100;
    }

    private long getRevenue(WinningLotto winningLotto, List<Lotto> lottos) {
        return getRanks(winningLotto, lottos)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrize() * entry.getValue())
                .reduce(0L, Long::sum);
    }

    public Map<Rank, Long> getRanks(WinningLotto winningLotto, List<Lotto> lottos) {
        return lottos.stream()
                .collect(Collectors.groupingBy(
                        lotto -> Rank.of(winningLotto.matchCount(lotto), winningLotto.matchBonus(lotto)),
                        Collectors.counting()));
    }

    private Lotto purchaseLotto() {
        return new Lotto(numberGenerator.generate(LottoNumber.NUMBER_MIN, LottoNumber.NUMBER_MAX, Lotto.NUMBER_LENGTH));
    }
}
