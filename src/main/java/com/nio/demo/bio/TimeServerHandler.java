package com.nio.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by kplu on 1/27/15.
 */
public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStreamReader isReader = new InputStreamReader(this.socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isReader);

            String body = null;
            while(true){
                body = bufferedReader.readLine();
                if(null != body && body.equals("what's the time?")){
                    System.out.println(socket.getPort());
                    System.out.println("Time server receive: " + body);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(System.currentTimeMillis());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
