package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import main.java.com.example.User;

public class Server {
    static int port = 1234;
    static int userCount = 0;
    static String host = "localhost";
    public static void main(String[] args) {
        
        if (args != null) {
            if (args[1].equals("-l")) {
                serverMode();
            } else {
                clientMode();
            }
        }
    }

    public static void serverMode() {
        System.out.println("Server Waiting...");
        try(ServerSocket serverSocket = new ServerSocket(port); Scanner sc = new Scanner(System.in)) {
            
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = new Socket(host, port);
                User user = new User(socket);

                //user.start
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void clientMode() {
        try (Socket socket = new Socket(host, port); Scanner sc = new Scanner(System.in)) {
            System.out.println("서버에 연결되었습니다.");

            Thread inputThread = new Thread(() -> {
                try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line = "";
                    while ((line = input.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (NullPointerException | IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            inputThread.start();
            String line = "";
            while (!(line = sc.nextLine()).equals("exit")) {
                socket.getOutputStream().write((line + "\n").getBytes());
            }

        } catch (ConnectException e) {
            System.err.println(host + ":" + port + "에 연결할 수 없습니다.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}