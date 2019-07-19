package com.netty.linebasedframedecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChannelHandler.Sharable 标注一个channel handler可以被多个channel安全地共享
 * ChannelInboundHandlerAdapter实现了ChannelInboundHandler
 * 回调事件处理类
 *
 * @author monkjavaer
 * @date 2019/7/18 15:36
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);

    /**
     * 新的连接被建立时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("client {} connected.", ctx.channel().remoteAddress());
    }

    private int count = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        LOGGER.info("server received message {}：{}", ++count, message);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client!\n", CharsetUtil.UTF_8));
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
