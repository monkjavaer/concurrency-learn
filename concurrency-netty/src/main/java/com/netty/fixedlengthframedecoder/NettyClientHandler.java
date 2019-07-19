package com.netty.fixedlengthframedecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author monkjavaer
 * @date 2019/7/18 17:26
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);

    /**
     * 新的连接被建立时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("server {} connected.", ctx.channel().remoteAddress());
        ByteBuf byteBuf = null;
        byte[] message = "hello server!i am client".getBytes();
        for(int i = 0;i<100;i++){
            byteBuf = Unpooled.buffer(message.length);
            byteBuf.writeBytes(message);
            ctx.writeAndFlush(byteBuf);
        }
    }

    private int count = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        LOGGER.info("client received message {}：{}", ++count, message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("server exceptionCaught,{}",cause.getMessage());
        ctx.close();
    }
}
