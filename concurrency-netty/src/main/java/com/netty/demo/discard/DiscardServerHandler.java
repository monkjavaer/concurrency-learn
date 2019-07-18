package com.netty.demo.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 *
 * 丢弃消息服务处理类。
 * ChannelInboundHandlerAdapter实现了ChannelInboundHandler
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 重写事件处理方法channelRead(),会在接收到消息时被调用。
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //丢弃数据，这里接收到的消息是ByteBuf类型，
        //ByteBuf实现了ReferenceCounted接口，是一个引用计数对象，必须显示的通过调用release()方法释放。
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.print("搜到消息："+(char) in.readByte());
                System.out.flush();
            }
        } finally {
            //使用工具类：ReferenceCountUtil ,也可以用in.release()
            ReferenceCountUtil.release(msg);
        }

    }

    /**
     * 发生异常时关闭连接
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
