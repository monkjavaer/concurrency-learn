package com.nettyforshandianxia;

import com.netty.decodeandencode.server.AnalyzeUnvProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2019/7/18 17:26
 */
public class NettyClient {
    /**
     * 日志对象
     */
    private static Logger LOGGER = LoggerFactory.getLogger(AnalyzeUnvProtocol.class);

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                //配置日志
                .handler(new LoggingHandler(LogLevel.INFO))
                // 绑定自定义属性到 channel
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 设置TCP底层属性
                //CONNECT_TIMEOUT_MILLIS 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                //SO_KEEPALIVE 表示是否开启 TCP 底层心跳机制，true 为开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        //ch.pipeline()返回的是ChannelPipeline，使用了责任链模式
                        ch.pipeline()
                                //添加逻辑处理器
                                .addLast(new NettyClientHandler());
                    }
                });

        // 4.建立连接
        connect(bootstrap, "127.0.0.1", 8888, MAX_RETRY);
    }

    /**
     * 连接和重连机制，实现了指数退避重连
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {

        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    LOGGER.info("连接服务器成功!");
                } else if (retry == 0) {
                    LOGGER.error("重连次数已用完，放弃连接！");
                } else {
                    // 第几次重连
                    int order = (MAX_RETRY - retry) + 1;
                    // 本次重连的间隔
                    int delay = 1 << order;
                    LOGGER.error(new Date() + ": 连接失败，第" + order + "次重连……");
                    //定时任务是调用 bootstrap.config().group().schedule(),
                    // 其中 bootstrap.config() 这个方法返回的是 BootstrapConfig，他是对 Bootstrap 配置参数的抽象，
                    // 然后 bootstrap.config().group() 返回的就是我们在一开始的时候配置的线程模型 workerGroup，
                    // 调 workerGroup 的 schedule 方法即可实现定时任务逻辑。
                    bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
                }
            }
        });
    }
}
