package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class UniqueRandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate(int min, int max, int size) {
        return Randoms.pickUniqueNumbersInRange(min, max, size);
    }
}
