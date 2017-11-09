package com.Zhangqy.SecondDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class HWClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());

        // 客户端的handler
        //先调用handler在ChannnelActive方法中调用fireChannelActive会激活handler1
        pipeline.addLast("handler", new HWClienthandler());
        pipeline.addLast("handler1", new BaseClientHandler());

    }
}
