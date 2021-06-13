package ch03;

public class TicTacToe {

    private Character[][] board = {{'\0', '\0', '\0'},
            {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};

    public void play(int x, int y) throws Exception {
        // x 조건
        checkValue(x, "X값이 올바르지 않습니다");
        // y 조건
        checkValue(y, "Y값이 올바르지 않습니다");
        // 올바른 곳에 두었는지 확인
        putBoard(x, y);
    }

    private void putBoard(int x, int y) throws Exception {
        if (board[x - 1][y - 1] == '\0') {
            board[x - 1][y - 1] = 'X';
        } else {
            throw new Exception("같은 자리에 두번 놓을 수 없습니다");
        }
    }

    private void checkValue(int y, String s) throws Exception {
        if (y < 1 || 3 < y) {
            throw new Exception(s);
        }
    }
}
