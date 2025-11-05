package lotto.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lotto.model.Lotto;

public class LottoRepository implements Repository<Lotto> {
    private final List<Lotto> lottos = new ArrayList<>();

    @Override
    public void add(Lotto lotto) {
        lottos.add(lotto);
    }

    @Override
    public void addAll(Collection<Lotto> c) {
        lottos.addAll(c);
    }

    @Override
    public void delete(Lotto lotto) {
        lottos.remove(lotto);
    }

    @Override
    public List<Lotto> findAll() {
        return lottos;
    }

    @Override
    public void clear(){
        lottos.clear();
    }
}
