package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Netcat {
    static int port = 1234;
    static String host = "localhost";
    static Set<PrintWriter> clientWriters = new HashSet<>();
    public static void main(String[] args) {
        serverMode(); 
    }

    public static void serverMode() {
        System.out.println("Server Waiting...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                PrintWriter clientWriter = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(clientWriter); // 클라이언트 출력 스트림 추가

                // 클라이언트로부터 데이터를 읽는 스레드 생성
                Thread clientReadThread = new Thread(() -> {
                    try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        String line;
                        while ((line = input.readLine()) != null) {
                            System.out.println("From Client: " + line);
                            if (line.equals("exit")) {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("클라이언트와의 통신 오류: " + e.getMessage());
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.err.println("소켓 닫기 오류: " + e.getMessage());
                        }
                    }
                });
                clientReadThread.start();

                // 서버에서 사용자 입력을 처리하는 스레드 생성
                Thread serverInputThread = new Thread(() -> {
                    try (Scanner sc = new Scanner(System.in)) {
                        String line;
                        while (!(line = sc.nextLine()).equals("exit")) {
                            // 모든 클라이언트에게 메시지 보내기
                            for (PrintWriter writer : clientWriters) {
                                writer.println("From Server: " + line);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("클라이언트에게 메시지를 보내는 중 오류 발생: " + e.getMessage());
                    }
                });
                serverInputThread.start();
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }
    public static void clientMode() {
        try (Socket socket = new Socket(host, port); Scanner sc = new Scanner(System.in)) {
            System.out.println("서버에 연결되었습니다.");

            Thread inputThread = new Thread(() -> {
                System.out.println(1111);
                try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    System.out.println(22222);
                    String line = "";
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (NullPointerException | IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            inputThread.start();

            System.out.println(33333);
            String line = "";
            while (!(line = sc.nextLine()).equals("exit")) {
                System.out.println(44444);
                socket.getOutputStream().write((line + "\n").getBytes());
            }

        } catch (ConnectException e) {
            System.err.println(host + ":" + port + "에 연결할 수 없습니다.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}