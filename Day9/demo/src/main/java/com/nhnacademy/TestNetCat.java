// NetCat.java
package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestNetCat implements Runnable {
    private Socket socket;
    private Queue<String> receiveQueue = new LinkedList<>();
    private static List<NetCat> clientList = new LinkedList<>();

    public TestNetCat(Socket socket, List<NetCat> clientList) {
        this.socket = socket;
        this.clientList = clientList;
    }

    public void send(String message) {
        synchronized (socket) {
            try {
                BufferedWriter outputRemote = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                outputRemote.write(message);
                outputRemote.newLine(); // 새 줄 추가
                outputRemote.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

    public boolean isEmptyReceiveQueue() {
        synchronized (receiveQueue) {
            return receiveQueue.isEmpty();
        }
    }

    public String receive() {
        synchronized (receiveQueue) {
            return receiveQueue.poll();
        }
    }

    public void run() {
        try (BufferedReader inputRemote = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            Thread receiver = new Thread(() -> {
                try {
                    String line;
                    while ((line = inputRemote.readLine()) != null) {
                        synchronized (receiveQueue) {
                            receiveQueue.add(line);
                        }
                        // Send received message to all clients
                        synchronized (clientList) {
                            for (NetCat client : clientList) {
                                client.send(line + "\n");
                            }
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });

            receiver.start();
            receiver.join();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
