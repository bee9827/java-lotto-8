package lotto.model;

import java.util.List;

public interface LottoMachine {
    List<Lotto> issueTickets(int n);
}
