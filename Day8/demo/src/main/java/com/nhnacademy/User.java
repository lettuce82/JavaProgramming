package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class User implements Runnable{
    static int count = 0;
    Socket socket;
    
    public User(Socket socket) {
        this.socket = socket;
        count++;
    }

    public Socket getSocket() {
        return this.socket;
    }

    @Override
    public void run() {
        
    }
}
