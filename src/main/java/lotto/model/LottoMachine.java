package lotto.model;

import java.util.List;

@FunctionalInterface
public interface LottoMachine {
    List<Lotto> issueTickets(int n);
}
