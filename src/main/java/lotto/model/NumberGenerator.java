package lotto.model;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {
    List<Integer> uniqueNumbers(int minRange, int maxRange, int Size);
}
