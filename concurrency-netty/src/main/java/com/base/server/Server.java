package com.base.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author monkjavaer
 * @date 2019/7/15 13:37
 */
public class Server {
    /**
     * 日志对象
     */
    private static Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        // 创建IO调配组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 创建执行工作组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            //使用Netty实现的线程池
            DefaultEventExecutorGroup executorGroup = new DefaultEventExecutorGroup(4, new DefaultThreadFactory("unv-server-thread"));
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 传入的处理通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 自定义解码器支持宇视协议
                            ch.pipeline().addLast("decoder", new UnvDecode(4096000, 4, 4, 0, 0));
                            // 超时时间
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(90));
                            // 添加过车的处理
                            ch.pipeline().addLast(executorGroup, "vehicleHandler", new ServerHandler());
                        }
                    })
                    // 设置TCP连接数
                    .option(ChannelOption.SO_BACKLOG, 1024).option(ChannelOption.SO_RCVBUF, 1024 * 64)
                    .option(ChannelOption.SO_SNDBUF, 1024 * 64)
                    .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 64 * 1024)
                    .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 32 * 1024)
                    //Boss线程内存池配置.
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    //Work线程内存池配置.
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                    // TCP保持心跳长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind andstart to accept incoming connections.
            ChannelFuture futureServer = b.bind(5197).sync();

            System.out.println("TCP服务启动成功。");
            // Wait untilthe server socket is closed.
            // In this example,this does not happen, but you can do that to
            // gracefully
            // shut downyour server.
            // 挂起服务
            futureServer.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("异常信息:" + e.getMessage(), e);
        } finally {
            // 优雅关闭
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
