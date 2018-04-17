package com.nio.demo.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by roger.lu on 2017/12/18.
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel actived.");

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("roger");
        builder.setProductName("python");
        SubscribeReqProto.SubscribeReq subscribeReq = builder.build();

        ctx.writeAndFlush(subscribeReq);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeRespProto.SubscribeResp subscribeResp = (SubscribeRespProto.SubscribeResp) msg;
        System.out.println("client get response: " + subscribeResp.toString());

    }

}
