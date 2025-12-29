package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static Long toLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요");
        }
    }

    public static Integer toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요");
        }
    }

    public static List<String> split(String target, String regex) {
        try {
            return Arrays.stream(target.split(regex))
                    .map(String::trim)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("숫자와 구분자만(" + regex + ") 입력해 주세요");
        }
    }

    public static List<Integer> splitNumbers(String numbers, String regex) {
        return split(numbers, regex)
                .stream()
                .map(Parser::toInteger)
                .toList();
    }
}
