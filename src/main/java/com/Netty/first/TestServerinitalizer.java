package com.Netty.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


public class TestServerinitalizer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();//管道
        pipeline.addLast("httpServerCodec", new HttpServerCodec());//外部响应的编码
        pipeline.addLast("testHttpServerHander", new TestHttpServerHandler());
    }
}
