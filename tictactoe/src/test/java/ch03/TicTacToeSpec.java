package ch03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @BeforeEach
    void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    void whenXOutsideBoardThenRuntimeException() {
        Assertions.assertThrows(Exception.class, () ->{
            ticTacToe.play(5,2);
        });
    }

    @Test
    void 같은자리에_한번_이상_두게되면_예외발생() throws Exception {

        ticTacToe.play(2,1);

        Assertions.assertThrows(Exception.class, () ->{
            ticTacToe.play(2,1);
        });


    }
}
