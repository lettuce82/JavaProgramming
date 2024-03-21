package main.java.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class User implements Runnable{
    static int count = 0;
    Socket socket;
    
    public User(Socket socket) {
        this.socket = socket;
        count++;
    }

    @Override
    public void run() {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Connected : " + socket.getInetAddress().getHostAddress()); //상대편 주소
            String line = "";

            Thread sendThread = new Thread(() -> {
                String sendData = "";
                while ((sendData = sc.nextLine()) != null) {
                    try {
                        socket.getOutputStream().write((sendData + "\n").getBytes());
                    } catch (IOException e) {
                        System.out.println("연결된 클라이언트가 없습니다.");
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            sendThread.start();
            
            while (!(line = input.readLine()).equals("exit")) {
                System.out.println(line);
                socket.getOutputStream().write((line + "\n").getBytes());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
