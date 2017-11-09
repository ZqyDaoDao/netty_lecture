package com.Netty.Four;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;

            if(event.state() == IdleState.READER_IDLE){
                eventType = "读空闲";
            }else if(event.state() == IdleState.WRITER_IDLE){
                eventType = "写空闲";
            }else{
                eventType = "读写空闲";
            }

            System.out.println(ctx.channel().remoteAddress()+"超时实践 " +eventType);
            ctx.channel().close();
        }
    }
}
