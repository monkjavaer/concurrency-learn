package com.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author monkjavaer
 * @date 2019/7/12 13:45
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }

    public void run() throws Exception {
        //NioEventLoopGroup是一个处理I/O操作的多线程事件循环
        //"boss"：接收一个传入连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //"worker" : 当boss接收连接并把接收的连接注册给worker，work就开始处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap是一个帮助类，可以设置服务器
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //NioServerSocketChannel用于实例化新通道来接收传入的连接
                    .channel(NioServerSocketChannel.class)
                    //ChannelInitializer用于配置新通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //通过ChannelPipeline添加处理类ChannelHandler
                            //通常有很多处理类，可以将这个内部类new ChannelInitializer提为一个独立的类
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    //ChannelOption和ChannelConfig可以设置各种参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //option()用于接受传入连接的NioServerSocketChannel,childOption()用于父ServerChannel接受的通道
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();


            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
