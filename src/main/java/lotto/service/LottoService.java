package lotto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.controller.dto.LottoDto;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.NumberGenerator;
import lotto.model.WinningLotto;
import lotto.repository.LottoRepository;
import lotto.repository.WinningLottoRepository;
import lotto.view.WinningResult;

public class LottoService {
    private final WinningLottoRepository winningLottoRepository;
    private final LottoRepository lottoRepository;

    public LottoService(WinningLottoRepository winningLottoRepository, LottoRepository lottoRepository) {
        this.winningLottoRepository = winningLottoRepository;
        this.lottoRepository = lottoRepository;
    }

    public void addWinningLotto(List<Integer> integers, Integer integer) {
        WinningLotto winningLotto = new WinningLotto(integers, integer);
        winningLottoRepository.add(winningLotto);
    }

    public void addLottoTickets(NumberGenerator numberGenerator, int i) {
        List<Lotto> lottos = issueTickets(numberGenerator, i);
        lottoRepository.addAll(lottos);
    }

    private List<Lotto> issueTickets(NumberGenerator numberGenerator, int count) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = numberGenerator.uniqueNumbers(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE,
                    Lotto.SIZE);
            lottoTickets.add(new Lotto(numbers));
        }
        return lottoTickets;
    }

    public List<LottoDto> getTickets() {
        return getAllLotto().stream()
                .map(Lotto::getSortedNumbers)
                .map(LottoDto::new)
                .toList();
    }

    public WinningResult getResults() {
        return new WinningResult(getAllLotto().stream()
                .map(getLastWinningLotto()::matching)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting())));
    }

    public Long getRevenue() {
        return getAllLotto().stream()
                .map(getLastWinningLotto()::revenue)
                .reduce(0L, Long::sum);
    }

    private WinningLotto getLastWinningLotto() {
        return winningLottoRepository.findAll()
                .getLast();
    }

    private List<Lotto> getAllLotto() {
        return lottoRepository.findAll();
    }
}
