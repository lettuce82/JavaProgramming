package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam04 {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(1234)) {
            while (!Thread.currentThread().isInterrupted()) {
                try(Socket socket = serverSocket.accept()) {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Connected : " + socket.getInetAddress().getHostAddress()); //상대편 주소
                    String line = "";
                    
                    while (!(line = input.readLine()).equals("exit")) {
                        System.out.println(line);
                        socket.getOutputStream().write((line + "\n").getBytes());
                    }
                } catch (NullPointerException e) {
                    
                }
            }
        } catch (IOException e) {
            System.out.println("연결 실패 : " + e.getMessage());
        } 
    }
}
