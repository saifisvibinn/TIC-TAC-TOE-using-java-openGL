import java.util.Arrays;

public class Game {
    private char[][] board;
    private boolean xTurn;
    private int xWins;
    private int oWins;

    public Game() {
        board = new char[3][3];
        resetBoard();
        xTurn = true;
        xWins = 0;
        oWins = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public void makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) { // Boundary check
            if (board[row][col] == ' ') {
                board[row][col] = xTurn ? 'X' : 'O';
                xTurn = !xTurn;
                if (checkWin()) {
                    if (!xTurn) {
                        xWins++;
                    } else {
                        oWins++;
                    }
                    resetBoard();
                }
            }
        }
    }

    public boolean checkWin() {
        return checkRows() || checkCols() || checkDiagonals();
    }

    public void resetBoard() {
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        xTurn = true;
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }
}
