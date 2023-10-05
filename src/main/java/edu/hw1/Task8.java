package edu.hw1;

public final class Task8 {
    private Task8() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean knightBoardCapture(int[][] board) {
        int[] di = new int[] {
                -2, -1, 1, 2, 2, 1, -1, -2
        };
        int[] dj = new int[] {
                1, 2, 2, 1, -1, -2, -2, -1
        };
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < 8; ++k) {
                        if (checkCapture(board, i + di[k], j + dj[k])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkCapture(int[][] board, int i, int j) {
        return i >= 0 && i < 8 && j >= 0 && j < 8 && board[i][j] == 1;
    }
}
