package com.Zhangqy.FirstDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloWordClient {
    private int port;
    private String address;

    public HelloWordClient(int port, String address) {
        this.port = port;
        this.address = address;
    }
    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());
        try {
            Channel channel = bootstrap.connect(address,port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            for(;;){
                String msg = reader.readLine();
                if (msg == null){
                    continue;
                }
                channel.writeAndFlush(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        HelloWordClient client = new HelloWordClient(7788,"127.0.0.1");
        client.start();
    }
}
