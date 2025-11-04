package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> uniqueNumbers(int minRange, int maxRange, int Size) {
        return Randoms.pickUniqueNumbersInRange(minRange, maxRange, Size);
    }
}
