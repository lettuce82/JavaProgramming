package com.nhnacademy;


public class Bingo {
    private final int[][] board;
    private final boolean[][] marked;

    public Bingo(int[][] board) {
        this.board = board;
        this.marked = new boolean[board.length][board[0].length];
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean[][] getMarked() {
        return marked;
    }

    // 숫자 선택하기
    public void selectNumber(int number) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == number) {
                    marked[i][j] = true;
                    return;
                }
            }
        }
    }

    // 빙고 여부 확인하기
    public boolean checkBingo() {
        // 가로, 세로, 대각선으로 빙고 여부를 확인하는 로직 구현
        return checkRows() || checkCols() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < board.length; i++) {
            boolean bingo = true;
            for (int j = 0; j < board[i].length; j++) {
                if (!marked[i][j]) {
                    bingo = false;
                    break;
                }
            }
            if (bingo) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCols() {
        for (int j = 0; j < board[0].length; j++) {
            boolean bingo = true;
            for (int i = 0; i < board.length; i++) {
                if (!marked[i][j]) {
                    bingo = false;
                    break;
                }
            }
            if (bingo) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        boolean leftDiagonalBingo = true;
        boolean rightDiagonalBingo = true;

        for (int i = 0; i < board.length; i++) {
            if (!marked[i][i]) {
                leftDiagonalBingo = false;
            }
            if (!marked[i][board.length - 1 - i]) {
                rightDiagonalBingo = false;
            }
        }

        return leftDiagonalBingo || rightDiagonalBingo;
    }

    // 빙고판 출력하기
    // public void printBoard() {
    //     System.out.println("Bingo Board:");
    //     for (int i = 0; i < board.length; i++) {
    //         for (int j = 0; j < board[i].length; j++) {
    //             if (marked[i][j]) {
    //                 System.out.print("[X] ");
    //             } else {
    //                 System.out.print(board[i][j] + " ");
    //             }
    //         }
    //         System.out.println();
    //     }
    //     System.out.println();
    // }
}
