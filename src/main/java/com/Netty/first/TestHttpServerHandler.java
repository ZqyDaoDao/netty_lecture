package com.Netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URL;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    //构造了一个hello word的返回
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
       if(msg instanceof HttpRequest){
           HttpRequest httpRequest = (HttpRequest)msg;

           System.out.println("请求方法名：" +httpRequest.method());
           URI url = new URI(httpRequest.uri());
           if("/favicon.ico".equals(url.getPath())){
               System.out.println("请求favicon.ico");

           }

           ByteBuf content = Unpooled.copiedBuffer("hello,world", CharsetUtil.UTF_8);
           FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                   HttpResponseStatus.OK, content);

           response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
           response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
           ctx.writeAndFlush(response);
       }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler add");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
       System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }
}
