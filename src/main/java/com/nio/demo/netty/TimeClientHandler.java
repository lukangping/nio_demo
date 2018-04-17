package com.nio.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * Created by roger.lu on 2017/12/14.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("client channel registered.");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        byte[] bytes = "query time order".getBytes();
        ByteBuf message = Unpooled.buffer(bytes.length);
        message.writeBytes(bytes);

        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String read = new String(bytes, StandardCharsets.UTF_8);

        System.out.println("time client get :" + read);

    }
}
