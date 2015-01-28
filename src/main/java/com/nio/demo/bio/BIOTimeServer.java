package com.nio.demo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kplu on 1/27/15.
 */
public class BIOTimeServer {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8081);
        Socket socket;
        while(true){
            socket = server.accept();
            new Thread(new TimeServerHandler(socket)).start();
        }

    }

}
