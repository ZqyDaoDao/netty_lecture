package com.Zhangqy.SecondDemo;

import com.Zhangqy.FirstDemo.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HWClient {
    private int port;
    private String address;

    public HWClient(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HWClientChannelInitializer());

        try{
            ChannelFuture future = bootstrap.connect(address,port).sync();
            future.channel().writeAndFlush("hello,Netty Server, I am Client");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args){
        HWClient hwClient = new HWClient(7788,"127.0.0.1");
        hwClient.start();
    }
}
