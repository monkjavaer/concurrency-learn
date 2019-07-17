package com.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/15 0015 21:49
 */
public class NioServer implements Runnable{

        @Override
        public void run() {
            try {
                //1、打开ServerSocketChannel，监听客户端的链接
                ServerSocketChannel serverSocket = ServerSocketChannel.open();
                //2、绑定监听端口
                serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),8888));
                //3、false,设置为非阻塞模式
                serverSocket.configureBlocking(false);
                //4、创建Selector，Selector是NIO的多路复用器，Selector会不断轮询注册在它上面的通道Channel,
                //找出就绪状态的Channel(Channel通道发生读、写事件),
                Selector selector = Selector.open();
                //5、注册通道Channel到多路复用器Selector，并说明关注点SelectionKey.OP_ACCEPT，监听ACCEPT事件
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
                //6、Selector轮询就绪的Channel
                while (true) {
                    // 阻塞等待就绪的 Channel，这是关键点之一
                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                //SelectionKey可以获取就绪状态的Channel
                                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                                //7、多路复用器Selector监听到有新的客户端连接，完成TCP三次握手建立连接。
                                SocketChannel clientSocketChannel = serverSocketChannel.accept();
                                //8、设置客户端SocketChannel为非阻塞模式
                                clientSocketChannel.configureBlocking(false);
                                clientSocketChannel.write(Charset.defaultCharset().encode("Hello world!"));
                            }
                        }
                        iter.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
