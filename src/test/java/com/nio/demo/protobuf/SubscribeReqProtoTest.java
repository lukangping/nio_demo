package com.nio.demo.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by roger.lu on 2017/12/18.
 */
public class SubscribeReqProtoTest {


    @Test
    public void testProtoBuf() throws InvalidProtocolBufferException {

        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("roger");
        builder.setProductName("python");
        builder.addAddress("address1");
        builder.addAddress("address2");
        builder.addAddress("address3");

        SubscribeReqProto.SubscribeReq subscribeReq = builder.build();

        String res = subscribeReq.toString();
        System.out.println(res);

        SubscribeReqProto.SubscribeReq parsedReq = SubscribeReqProto.SubscribeReq.parseFrom(subscribeReq.toByteArray());
        System.out.println(parsedReq.getSubReqID());
        System.out.println(parsedReq.getUserName());
        System.out.println(parsedReq.getProductName());


    }

}