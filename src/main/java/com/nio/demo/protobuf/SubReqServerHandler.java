package com.nio.demo.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by roger.lu on 2017/12/18.
 */
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeReqProto.SubscribeReq subscribeReq = (SubscribeReqProto.SubscribeReq) msg;
        System.out.println("server receive request: " + subscribeReq.toString());

        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subscribeReq.getSubReqID());
        builder.setDesc("success order.");
        builder.setRespCode(0);
        SubscribeRespProto.SubscribeResp subscribeResp = builder.build();
        ctx.writeAndFlush(subscribeResp);
        System.out.println("server response: " + subscribeResp.toString());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
