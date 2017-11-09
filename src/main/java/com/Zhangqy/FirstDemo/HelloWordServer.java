package com.Zhangqy.FirstDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloWordServer {
    private int port;

    public HelloWordServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap()
                .group(boss,work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());

        try{
            ChannelFuture future = server.bind(port).sync();
            future.channel().closeFuture().sync();
        }   catch (Exception e){
            e.printStackTrace();
        }   finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    public static void main(String[] args){
        HelloWordServer helloWordServer = new HelloWordServer(7788);
        helloWordServer.start();
    }
}
