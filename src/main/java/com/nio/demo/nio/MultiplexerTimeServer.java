package com.nio.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by kplu on 1/28/15.
 */
public class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    public MultiplexerTimeServer() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(8082));
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Time server start.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            selector.select();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleInput(SelectionKey key) {

        if(key.isValid()){
            if(key.isAcceptable()){
            }
        }

    }
}
