package edu.hw1;

public final class Task8 {
    private static final int[] DI = {
            -2, -1, 1, 2, 2, 1, -1, -2
    };
    private static final int[] DJ = {
            1, 2, 2, 1, -1, -2, -2, -1
    };
    private static final int BOARD_SIZE = 8;
    private static final int POSSIBLE_MOVES = 8;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < POSSIBLE_MOVES; ++k) {
                        if (willKnigthBeCaptured(board, i + DI[k], j + DJ[k])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean willKnigthBeCaptured(int[][] board, int i, int j) {
        return i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] == 1;
    }
}
