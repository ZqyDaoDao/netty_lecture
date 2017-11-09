package com.Zhangqy.FourDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NewServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof Message) {
            Message msg = (Message)o;
            System.out.println("Client->Server:"+channelHandlerContext.channel().remoteAddress()+" send "+msg.getMsgBody());
        }
    }
}