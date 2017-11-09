package com.Zhangqy.HeartBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HeartBeatsClient {
    private  int port;
    private  String address;
    ChannelFuture future;

    public HeartBeatsClient(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HeartBeatsClientChannelInitializer());

        try {
            future = bootstrap.connect(address,port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            group.shutdownGracefully();

            if (null != future) {
                if (future.channel() != null && future.channel().isOpen()) {
                    future.channel().close();
                }
            }
            System.out.println("准备重连");
            start();
            System.out.println("重连成功");
        }

    }

    public static void main(String[] args) {
        HeartBeatsClient client = new HeartBeatsClient(7788,"127.0.0.1");
        client.start();
    }
}