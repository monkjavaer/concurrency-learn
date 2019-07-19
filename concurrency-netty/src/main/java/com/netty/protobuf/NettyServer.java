package com.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DelimiterBasedFrameDecoder,分隔符作为码流结束标志，解码
 * @author monkjavaer
 * @date 2019/7/18 14:56
 */
public class NettyServer {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    public static int PORT = 8080;
    public static void connect(){
        //配置两个服务端的NIO线程组，一个用于接收客服端的链接，另一个用于进行SocketChannel的网络读写。
        //NioEventLoopGroup是一个处理I/O操作的多线程事件循环
        //"boss"：接收一个传入连接
        EventLoopGroup boss = new NioEventLoopGroup();
        //"worker" : 当boss接收连接并把接收的连接注册给worker，work就开始处理
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //ServerBootstrap是一个帮助类，可以设置服务器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    //NioServerSocketChannel用于实例化新通道来接收传入的连接
                    .channel(NioServerSocketChannel.class)
                    //配置日志
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //ChannelInitializer用于配置新通道
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //解码器
                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            pipeline.addLast(new ProtobufDecoder(UserInfoProto.UserInfo.getDefaultInstance()));
                            //编码器
                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    })
                    //ChannelOption和ChannelConfig可以设置各种参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            //异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture f = bootstrap.bind(PORT).sync();
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyServer.connect();
    }
}
