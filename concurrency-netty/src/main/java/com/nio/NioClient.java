package com.nio;

import com.netty.decodeandencode.server.CreateUnvProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author monkjavaer
 * @date 2019/7/18 13:05
 */
public class NioClient {
    private static Logger LOGGER = LoggerFactory.getLogger(CreateUnvProtocol.class);
    private static int PORT = 9011;
    private static String[] messages = {"这是服务器"};

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), PORT));
            for (String msg : messages) {
                ByteBuffer myBuffer = ByteBuffer.allocate(1024);
                myBuffer.put(msg.getBytes());
                myBuffer.flip();
                socketChannel.write(myBuffer);
            }
            LOGGER.info("Closing Client connection...");
            socketChannel.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
