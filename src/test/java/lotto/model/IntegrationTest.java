package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import lotto.controller.LottoController;
import lotto.repository.LottoRepository;
import lotto.repository.WinningLottoRepository;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

    private OutputStream output;
    private InputStream input;

    @BeforeEach
    void init() throws IOException {
        String fakeInput = "5000\n1,2,3,4,5,6\n7";
        input = new ByteArrayInputStream(fakeInput.getBytes(StandardCharsets.UTF_8));
        output = new ByteArrayOutputStream();
        System.setIn(input);
        System.setOut(new PrintStream(output));
    }

    @Test
    void test() {
        NumberGenerator fixedNumberGenerator = getDefaultLottoGenerator();
        LottoService lottoService = new LottoService(new WinningLottoRepository(), new LottoRepository());
        LottoController lottoController = new LottoController(new InputView(), new OutputView(), lottoService,
                fixedNumberGenerator);

        lottoController.run();

        assertThat(output.toString())
                .contains("5개를 구매했습니다.",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 6]",
                        "[1, 2, 3, 4, 5, 6]",
                        "3개 일치 (5,000원) - 0개",
                        "4개 일치 (50,000원) - 0개",
                        "5개 일치 (1,500,000원) - 0개",
                        "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                        "6개 일치 (2,000,000,000원) - 5개",
                        "총 수익률은 200,000,000.0%입니다.");
    }

    private NumberGenerator getDefaultLottoGenerator() {
        return (min, max, length) -> LottoTest.getDefaultNumbers();
    }
}
