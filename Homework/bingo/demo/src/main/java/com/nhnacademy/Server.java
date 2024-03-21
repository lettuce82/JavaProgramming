package com.nhnacademy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static int port = 1234;
    private final Bingo bingo;
    private User user1;
    private User user2;

    public static void main(String[] args) throws IOException {
        int[][] board = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        Server server = new Server(board);
        server.start();
    }

    // 생성자를 통해 빙고 게임 보드를 전달 받음
    public Server(int[][] board) {
        this.bingo = new Bingo(board);
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server waiting...");

            // 두 명의 참가자 생성
            Socket socket1 = serverSocket.accept();
            user1 = new User("Player 1", bingo.getBoard(), socket1);
            Socket socket2 = serverSocket.accept();
            user2 = new User("Player 2", bingo.getBoard(), socket2);

            // 각 참가자의 숫자 입력
            inputNumbers(user1);
            inputNumbers(user2);

            // 게임 시작
            playGame(user1, user2);
        }
    }

    private void inputNumbers(User user) throws IOException {
        InputStream inputStream = user.getSocket().getInputStream();
        Scanner scanner = new Scanner(inputStream);
        OutputStream outputStream = user.getSocket().getOutputStream();
        outputStream.write(("Enter numbers for your Bingo board, " + user.getName() + ":\n").getBytes());
        outputStream.flush();

        int[][] bingoBoard = user.getBingoBoard();
        for (int i = 0; i < bingoBoard.length; i++) {
            for (int j = 0; j < bingoBoard[i].length; j++) {
                int number = scanner.nextInt();
                bingoBoard[i][j] = number;
            }
        }
        user.setBingoBoard(bingoBoard);
    }

    private void playGame(User user1, User user2) throws IOException {
        while (!isGameEnded(user1, user2)) {
            // 각 참가자의 빙고판 출력
            user1.printBingoBoard();
            user2.printBingoBoard();
    
            // 차례에 따라 숫자 입력 받고 빙고 여부 확인
            inputNumberAndCheckBingo(user1);
            if (bingo.checkBingo()) {
                OutputStream outputStream = user1.getSocket().getOutputStream();
                outputStream.write(("Enter numbers for your Bingo board, " + user1.getName() + ":\n").getBytes());
                System.out.println(user1.getName() + " wins!");
                break;
            }
    
            inputNumberAndCheckBingo(user2);
            if (bingo.checkBingo()) {
                OutputStream outputStream = user2.getSocket().getOutputStream();
                outputStream.write(("Enter numbers for your Bingo board, " + user2.getName() + ":\n").getBytes());
                break;
            }
        }
    }
    
    private boolean isGameEnded(User user1, User user2) {
        return user1.getSocket().isClosed() || user2.getSocket().isClosed();
    }
    
    

    private void inputNumberAndCheckBingo(User user) throws IOException {
        Socket socket = user.getSocket();
        if (!socket.isClosed()) { // 클라이언트 소켓이 닫혔는지 확인
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("Select a number, " + user.getName() + ":\n").getBytes());
            outputStream.flush();
    
            int number = scanner.nextInt();
            bingo.selectNumber(number);
        } else {
            System.out.println("Socket is closed for user: " + user.getName());
        }
    }
}
