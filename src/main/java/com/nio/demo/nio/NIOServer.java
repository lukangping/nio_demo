package com.nio.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by roger.lu on 2017/12/13.
 */
public class NIOServer {

    private Selector selector;

    public void init(int port) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

    }

    public void listen() throws IOException {
        while (true) {

            selector.select();
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("server is ready.").getBytes()));
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);

                    String read = new String(buffer.array()).trim();
                    System.out.println("server receive: " + read);

                    String time = System.currentTimeMillis() + "";
                    ByteBuffer byteBuffer = ByteBuffer.wrap(time.getBytes());
                    channel.write(byteBuffer);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        NIOServer nioServer = new NIOServer();
        nioServer.init(8081);
        nioServer.listen();
    }
}
