package lotto;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static final String DEFAULT_DELIMITER = ",";

    public static long toLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(LottoErrorCode.NUMBER_FORMAT.getMessage());
        }
    }

    public static Integer toInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(LottoErrorCode.NUMBER_FORMAT.getMessage());
        }
    }

    public static List<String> split(String values) {
        return Arrays.stream(values.split(DEFAULT_DELIMITER))
                .map(String::trim)
                .toList();
    }
}
