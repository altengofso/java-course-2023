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
                        int ti = i + di[k];
                        int tj = j + dj[k];
                        if (ti >= 0 && ti < 8 && tj >= 0 && tj < 8 && board[ti][tj] == 1) {
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
