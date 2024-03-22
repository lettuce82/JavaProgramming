// SimpleNC.java

package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class TestSimpleNC {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("l", true, "Listen");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (args[0].equals("l")) {
                List<Thread> clientHandlerList = new LinkedList<>();
                List<NetCat> netcatList = new LinkedList<>();

                // Keyboard input
                Thread inputAgent = new Thread(() -> {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;

                    try {
                        while ((line = input.readLine()) != null) {
                            synchronized (netcatList) {
                                for (NetCat netCat : netcatList) {
                                    netCat.send(line + "\n");
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Start keyboard input thread
                inputAgent.start();

                ServerSocket serverSocket = new ServerSocket(1234);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Server waiting...");
                        NetCat netCat = new NetCat(serverSocket.accept());
                        System.out.println("client enter!");
                        Thread thread = new Thread(netCat);
                        thread.start();
                        clientHandlerList.add(thread);
                        netcatList.add(netCat);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                serverSocket.close();
            } else {
                try (Socket socket = new Socket("localhost", 1234)) {
                    NetCat netCat = new NetCat(socket);
                    Thread thread = new Thread(netCat);
                    thread.start();
                    thread.join();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (org.apache.commons.cli.ParseException | IOException e) {
        }
    }
}
