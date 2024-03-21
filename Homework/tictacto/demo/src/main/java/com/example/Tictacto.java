package com.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tictacto {
    private static final int SIZE = 3;
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private static final char EMPTY = ' ';

    private static char[][] board = new char[SIZE][SIZE];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (true) {
            playerMove();
            if (isWinner(PLAYER)) {
                System.out.println("플레이어 승리!");
                break;
            } else if (isBoardFull()) {
                System.out.println("무승부!");
                break;
            }

            computerMove();
            if (isWinner(COMPUTER)) {
                System.out.println("컴퓨터 승리!");
                break;
            } else if (isBoardFull()) {
                System.out.println("무승부!");
                break;
            }

            displayBoard();
        }
    }

    private static void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, EMPTY);
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char c : row) {
                System.out.print(c + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playerMove() {
        int row, col;
        while (true) {
            try {
                System.out.print("행 번호 (0-2) 입력: ");
                row = scanner.nextInt();
                System.out.print("열 번호 (0-2) 입력: ");
                col = scanner.nextInt();
                if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != EMPTY) {
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                } else {
                    board[row][col] = PLAYER;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }
        }
    }

    private static void computerMove() {
        int row, col;
        while (true) {
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
            if (board[row][col] == EMPTY) {
                board[row][col] = COMPUTER;
                break;
            }
        }
    }

    private static boolean isWinner(char player) {
        // 가로줄 체크
        for (char[] row : board) {
            if (row[0] == player && row[1] == player && row[2] == player) {
                return true;
            }
        }

        // 세로줄 체크
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // 대각선 체크
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}