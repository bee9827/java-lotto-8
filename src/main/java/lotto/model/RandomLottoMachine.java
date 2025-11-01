package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomLottoMachine implements LottoMachine {
    @Override
    public List<Lotto> issueTickets(int n) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tickets.add(issueTicket());
        }

        return tickets;
    }

    private Lotto issueTicket() {
        List<Integer> numbers = getLottoNumbers();
        return new Lotto(numbers);
    }

    private List<Integer> getLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE, Lotto.SIZE);
    }
}
