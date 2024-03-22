package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "localhost"; // 서버 주소
        final int serverPort = 1234; // 서버 포트

        try (
            Socket socket = new Socket(serverAddress, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // 서버로부터 메시지 수신을 담당하는 스레드
            Thread receiver = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("서버: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiver.start();

            // 사용자 입력을 서버로 전송
            String userInput;
            while ((userInput = stdin.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
