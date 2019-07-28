package com.nettyforshandianxia;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author monkjavaer
 * @date 2019/7/18 17:26
 */
public class NettyServer {
    private static final int BEGIN_PORT = 8888;

    public static void main(String[] args) {
        //bossGroup表示监听端口，accept 新连接的线程组
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        //workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建了一个引导类 ServerBootstrap，这个类将引导进行服务端的启动工作
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
            serverBootstrap
                    //给引导类配置两大线程组，指定线程模型
                    .group(boosGroup, workerGroup)
                    //指定服务端的 IO 模型为NIO
                    .channel(NioServerSocketChannel.class)
                    //配置日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                    .childAttr(clientKey, "clientValue")
                    //option() 给服务端channel设置一些属性
                    //SO_BACKLOG表示系统用于临时存放已完成三次握手的请求的队列的最大长度，
                    // 如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    //childOption()可以给每条连接设置一些TCP底层相关的属性
                    //ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //true表示关闭，false表示开启,
                    // 如果要求高实时性，有数据就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //调用childHandler()方法，给引导类创建一个ChannelInitializer，
                    // 这里主要定义后续每条连接的数据读写，业务处理逻辑
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            System.out.println(ch.attr(clientKey).get());
                            //通过ChannelPipeline添加处理类ChannelHandler
                            //通常有很多处理类，可以将这个内部类new ChannelInitializer提为一个独立的类
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            //异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
//            ChannelFuture f = serverBootstrap.bind(BEGIN_PORT).sync();

            bind(serverBootstrap, BEGIN_PORT);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            boosGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
        }
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        //Lambda写法
/*        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                bind(serverBootstrap, port + 1);
            }
        });*/

        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功!");
                } else {
                    System.err.println("端口绑定失败,自动绑定递增端口!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });

    }
}
