package com.Zhangqy.FourDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class NewEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        if(message == null){
            throw new Exception("未获得消息内容");
        }


        String msgBody = message.getMsgBody();
        byte[] b = msgBody.getBytes(Charset.forName("utf-8"));
        byteBuf.writeByte(message.getType());
        byteBuf.writeByte(b.length);
        byteBuf.writeBytes(b);


    }
}