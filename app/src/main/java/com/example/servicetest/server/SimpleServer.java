package com.example.servicetest.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(6666)){
            System.out.println("Listening on "+serverSocket.getLocalSocketAddress());
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
            System.out.println("Incoming connection from " + clientSocket.getRemoteSocketAddress());
            Scanner sc = new Scanner(System.in);

            while (true){
                String s = sc.nextLine();
                if (s.equals("-quit")){
                    break;
                }
                clientSocket.getOutputStream().write(s.getBytes());
            }

        }
    }
}
