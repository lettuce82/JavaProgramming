package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
//import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class SimpleNC {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("l", true, "Listen");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("l")) {
                List<Thread> clientHandlerList = new LinkedList<>();
                List<NetCat> netcatList = new LinkedList<>();

                //keyboard
                Thread inputAgent = new Thread(()-> {
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    
                    try {
                        while ((line = input.readLine()) != null) {
                            synchronized(netcatList) {
                                for (NetCat netCat : netcatList) {
                                    netCat.send(line + "\n");
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                //monitors
                Thread outputAgent = new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        synchronized(netcatList) {
                            for (NetCat netCat : netcatList) {
                                if (!netCat.isEmptyReceiveQueue()) {
                                    String line = netCat.receive();
                                    System.out.println(line);
                                }
                            }
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ignore) {
                            Thread.currentThread().interrupt();
                        }
                    }
                });

                inputAgent.start();
                outputAgent.start();

                ServerSocket serverSocket = new ServerSocket(1234);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        NetCat netCat = new NetCat(serverSocket.accept());
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