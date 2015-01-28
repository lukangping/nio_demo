package com.nio.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by kplu on 1/28/15.
 */
public class BIOTimeClient {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("127.0.0.1", 8081);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("what's the time?");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("The time client receive: " + reader.readLine());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
