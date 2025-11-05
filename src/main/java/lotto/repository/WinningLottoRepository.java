package lotto.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotto.model.WinningLotto;

public class WinningLottoRepository implements Repository<WinningLotto> {
    private final List<WinningLotto> winningLottos = new ArrayList<WinningLotto>();

    @Override
    public void add(WinningLotto winningLotto) {
        winningLottos.add(winningLotto);
    }

    @Override
    public void addAll(Collection<WinningLotto> c) {
        winningLottos.addAll(c);
    }

    @Override
    public void delete(WinningLotto winningLotto) {
        winningLottos.remove(winningLotto);
    }

    @Override
    public List<WinningLotto> findAll() {
        return winningLottos.stream()
                .toList();
    }

    @Override
    public void clear(){
        winningLottos.clear();
    }
}
