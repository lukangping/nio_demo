package com.nio.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by roger.lu on 2017/12/13.
 */
public class NIOClient {

    private Selector selector;

    public void init(String ip, int port) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(ip, port));

        this.selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    public void connect() throws IOException {

        while (true) {

            selector.select();

            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    System.out.println("client connected.");
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("what is the time?").getBytes()));

                    channel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {

                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    channel.read(buffer);
                    String read = new String(buffer.array()).trim();
                    System.out.println("client receive: " + read);

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NIOClient nioClient = new NIOClient();
        nioClient.init("127.0.0.1", 8081);
        nioClient.connect();
    }
}
