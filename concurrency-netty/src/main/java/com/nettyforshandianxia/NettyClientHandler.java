package com.nettyforshandianxia;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 逻辑处理器继承 ChannelInboundHandlerAdapter
 *
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
        // ctx.alloc() 获取一个 ByteBuf 的内存管理器 ByteBufAllocator,再获取二进制抽象 ByteBuf
        //Netty 中数据是以 ByteBuf 为单位
        ByteBuf buffer = ctx.alloc().buffer();

        // 指定发送的字符串的字符集为 utf-8
        byte[] bytes = "你好服务器 !".getBytes(StandardCharsets.UTF_8);

        // 填充数据到 ByteBuf
        buffer.writeBytes(bytes);

        //向服务端写数据
        ctx.channel().writeAndFlush(buffer);
    }

    /**
     * 接收到发来的数据之后被回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        //获取缓冲区可读字节数
        int readableBytes = byteBuf.readableBytes();
        byte[] bytes = new byte[readableBytes];
        byteBuf.readBytes(bytes);
        LOGGER.info("readableBytes is{},client received message:{}", readableBytes, new String(bytes, StandardCharsets.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("server exceptionCaught,{}",cause.getMessage());
        ctx.close();
    }

    public static void main(String[] args) {
        System.out.println(3<<1);
    }
}
