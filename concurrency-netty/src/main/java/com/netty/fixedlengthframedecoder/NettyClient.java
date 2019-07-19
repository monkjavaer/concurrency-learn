package com.netty.fixedlengthframedecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FixedLengthFrameDecoder 固定长度解码器
 * @author monkjavaer
 * @date 2019/7/18 17:17
 */
public class NettyClient {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    public static String IP = "127.0.0.1";
    public static int PORT = 8080;

    public static void main(String[] args) {
        EventLoopGroup client = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new FixedLengthFrameDecoder(24));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });

            ChannelFuture f = bootstrap.connect(IP,PORT).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.shutdownGracefully();
        }
    }
}
