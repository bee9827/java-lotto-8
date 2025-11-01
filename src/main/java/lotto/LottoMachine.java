package lotto;

import java.util.List;

public interface LottoMachine {
    List<Lotto> issueTickets(int n);
}
