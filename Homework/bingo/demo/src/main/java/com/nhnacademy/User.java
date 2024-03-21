package com.nhnacademy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class User {
    private final String name;
    int[][] bingoBoard;
    boolean[][] selectedNumbers;
    private final Socket socket;

    public User(String name, int[][] bingoBoard, Socket socket) {
        this.name = name;
        this.bingoBoard = bingoBoard;
        this.selectedNumbers = new boolean[bingoBoard.length][bingoBoard[0].length];
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public int[][] getBingoBoard() {
        return bingoBoard;
    }

    public void setBingoBoard(int[][] bingoBoard) {
        this.bingoBoard = bingoBoard;
    }

    public boolean[][] getSelectedNumbers() {
        return selectedNumbers;
    }

    // 사용자가 숫자 선택하기
    public void selectNumber(int number) {
        for (int i = 0; i < getBingoBoard().length; i++) {
            for (int j = 0; j < getBingoBoard()[i].length; j++) {
                if (getBingoBoard()[i][j] == number) {
                    selectedNumbers[i][j] = true;
                    return;
                }
            }
        }
    }

    // 사용자의 빙고판 출력하기
    public void printBingoBoard() {
        try (OutputStream outputStream = this.getSocket().getOutputStream()) {
            try {
                outputStream.write(("Bingo Board for " + name + ":\n").getBytes());
                for (int i = 0; i < getBingoBoard().length; i++) {
                    for (int j = 0; j < getBingoBoard()[i].length; j++) {
                        int num = bingoBoard[i][j];
                        if (num < 10) {
                            outputStream.write((" ").getBytes());
                        }
                        if (selectedNumbers[i][j]) {
                            outputStream.write(("[X] ").getBytes());
                        } else {
                            outputStream.write((num + " ").getBytes());
                        }
                    }
                    outputStream.write(("\n").getBytes());
                }
                System.out.println();
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
            } catch (IOException e) {
                
                e.printStackTrace();
        }
        
    }
    
}
