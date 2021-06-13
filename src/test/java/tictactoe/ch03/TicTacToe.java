package tictactoe.ch03;

public class TicTacToe {

    public void play(int x, int y) throws Exception {
        // x 조건
        if (x < 1 || 3 < x){
            throw new Exception("x가 board 밖에 있습니다.");
        }

        // y 조건
        if (y < 1 || 3 < y){
            throw new Exception("y가 board 밖에 있습니다.");
        }
    }
}
