package com.nhnacademy;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private List<PrintWriter> clients = new ArrayList<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            // 서버 콘솔 입력을 받아 처리하는 스레드
            Thread consoleInputThread = new Thread(() -> {
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String consoleInput;
                    while ((consoleInput = consoleReader.readLine()) != null) {
                        System.out.println("서버 입력: " + consoleInput);
                        // 원하는 처리를 수행
                        // 예를 들어, 모든 클라이언트에게 서버에서 입력한 메시지를 보낼 수 있습니다.
                        synchronized (clients) {
                            for (PrintWriter client : clients) {
                                client.println(consoleInput);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            consoleInputThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                synchronized (clients) {
                    clients.add(out); // 클라이언트 리스트에 추가
                }

                // 클라이언트에게 환영 메시지 전송
                out.println("Welcome to the server!");

                // 클라이언트 처리를 별도의 스레드로 실행
                Thread clientThread = new Thread(() -> handleClient(clientSocket, out));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket, PrintWriter out) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message from client: " + message);
                // 모든 클라이언트에게 메시지 전송
                synchronized (clients) {
                    for (PrintWriter client : clients) {
                        client.println(message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 클라이언트 연결이 끊겼을 때의 처리
            synchronized (clients) {
                clients.remove(out); // 클라이언트 리스트에서 제거
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(1234);
    }
}
