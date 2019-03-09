package com.example.servicetest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket(InetAddress.getLocalHost(), 6666);

        try {
            InputStream s = client.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = s.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
