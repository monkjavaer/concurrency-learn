package com.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author monkjavaer
 * @date 2019/7/17 13:55
 */
public class BioServer {
    public static final int PORT = 8888;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            Socket socket = null;
            ThreadFactory namedThreadFactory = new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setName("demo-pool-%d");
                    return t;
                }
            };
            ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

            //主线程死循环等待新连接到来
            while(!Thread.currentThread().isInterrupted()){
                socket = serverSocket.accept();
                singleThreadPool.execute(new BioServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
