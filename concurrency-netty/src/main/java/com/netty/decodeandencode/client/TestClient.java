package com.netty.decodeandencode.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author monkjavaer
 * @date 2019/7/15 11:00
 */
public class TestClient {

    public final static int PORT = 5197;
    public final static String IP = "127.0.0.1";

    public static void connect(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ClientEncoder());
                            p.addLast(new ClientHandler());
                        }
                    });
            // Make the connection attempt.
            ChannelFuture f = bootstrap.connect(TestClient.IP, TestClient.PORT);
            f.addListener(new ConnectionListener());
            f.channel().closeFuture().sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //需要大于重连时间间隔3秒
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
