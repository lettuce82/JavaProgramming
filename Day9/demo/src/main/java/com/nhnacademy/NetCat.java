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

public class NetCat implements Runnable {
    Socket socket;
    Queue<String> receiveQueue = new LinkedList<>();
    Queue<String> sendQueue = new LinkedList<>();
    static List<NetCat> clientList = new LinkedList<>();

    public NetCat(Socket socket, List<NetCat> clientList) {
        this.socket = socket;
        this.clientList = clientList;
    }

    public NetCat(Socket socket) {
        this.socket = socket;
    }

    public void send(String message) {
        synchronized (sendQueue) {
            sendQueue.add(message);
        }
    }

    public List getClientList() {
        return clientList;
    }

    public boolean isEmptyReceiveQueue() {
        synchronized (receiveQueue) {
            return receiveQueue.isEmpty();
        }
    }

    public boolean isServer() {
        synchronized (clientList) {
            return getClientList().isEmpty();
        }
    }

    public String receive() {
        synchronized (receiveQueue) {
            return receiveQueue.poll();
        }
    }

    public void run() {
        System.out.println(clientList.size());
        try (BufferedReader inputRemote = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter outputRemote = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                
                Thread receiver = new Thread(() -> {
                    try {
                        String line;
                        while ((line = inputRemote.readLine()) != null) {
                            synchronized(receiveQueue) {
                                receiveQueue.add(line);
                                
                            }
                        }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    
                Thread sender = new Thread(() -> {
                    
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            
                            synchronized(sendQueue) {
                                if (!sendQueue.isEmpty()) {
                                    outputRemote.write(sendQueue.poll());
                                    outputRemote.flush(); //-> println() : newLine을 보내주지만 buffered는 개행 문자가 있어야 보내지는데... 안 됐었나? 암튼 보내는 시점이 이상해서 flush()를 적으면 버퍼를 비우라는 거기 때문에 바로 보내짐
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    
                receiver.start();
                sender.start();
    
                receiver.join();
                sender.join();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
        }
    }
}
