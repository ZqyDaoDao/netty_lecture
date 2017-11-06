package com.Netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    //构造了一个hello word的返回
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
       if(msg instanceof HttpRequest){
           ByteBuf content = Unpooled.copiedBuffer("hello,world", CharsetUtil.UTF_8);
           FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                   HttpResponseStatus.OK, content);

           response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
           response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
           ctx.writeAndFlush(response);
       }

    }
}
