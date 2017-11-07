package com.Netty.second;

import com.Netty.first.TestServerinitalizer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGrop = new NioEventLoopGroup();//不断接受客户端的消息，不做处理
        EventLoopGroup workerGrop = new NioEventLoopGroup();//处理boss传过来的消息，进行相应的业务处理
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGrop,workerGrop).channel(NioServerSocketChannel.class).
                    childHandler(new MyServerInitializer());//childHandler 子处理器
            ChannelFuture channelFuture =serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGrop.shutdownGracefully();
            workerGrop.shutdownGracefully();
        }

    }
    }
