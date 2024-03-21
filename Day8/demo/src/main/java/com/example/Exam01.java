package com.example;

import java.io.Console;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Exam01 {
    public static void main(String[] args) {
            // 메서드 활용 + 짝꿍 ip로 접속하기
            // try {
            //     Socket socket = new Socket("10.201.191.183", 8080);
            //     System.out.println("LocalAddress : " + socket.getLocalAddress());
            //     System.out.println("LocalPort : " + socket.getLocalPort());
            //     System.out.println("Remote address : " + socket.getRemoteSocketAddress());
            //     System.out.println("Remote address : " + socket.getPort());
            //     System.out.println("Port " + 8080 + " 열려 있습니다.");
            
            //     socket.close();
            // } catch (UnknownHostException e) {
            //     System.err.println("Host를 찾을 수 없습니다. : " + e.getMessage()); 
            //     //host 오류
            // } catch(IOException e) {
            //     System.err.println("Socket 생성에 오류가 발생하였습니다. : " + e.getMessage()); 
            //     //port 번호 오류
            // }
            
        String host = "localhost";
        int port = 12345;
        Console console = System.console();
        
        try {
            Socket socket = new Socket(host, port);
            while (true) {
                System.out.println("서버에 연결되었습니다.");
                System.out.print("보낼 데이터를 입력하세요 : ");
                String data = console.readLine();
                if (data.equals("exit")) {
                    break;
                } else {
                    socket.getOutputStream().write((data + "\n").getBytes());
                }
            }
            socket.close();
        } catch (ConnectException e) {
            System.err.println(host + ":" + port + "에 연결할 수 없습니다.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
