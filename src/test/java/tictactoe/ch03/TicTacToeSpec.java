package tictactoe.ch03;

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
}
