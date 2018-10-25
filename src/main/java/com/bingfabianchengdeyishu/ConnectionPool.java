package com.bingfabianchengdeyishu;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 模拟从连接池中获取、使用和释放连接的过程，而客户端获取连接的过程被设定为等待超时的模式，也就是在
 * 1000毫秒内如果无法获取到可用连接，将会返回给客户端一个null
 * @author tangquanbin
 * @date 2018/10/25 13:15
 */
public class ConnectionPool {

    /**
     * 双向队列来维护连接
     */
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    /**
     * 构造函数初始化连接的最大上限
     * @param initialSize 连接数
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 当连接使用完成后，需要调用releaseConnection(Connection)方法将连接放回线程池
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            //先对调用对象加锁
            synchronized (pool) {
                // 添加后需要进行通知，这样其他消费者能够感知到链接池中已经归还了一个链接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接Connection，在mills内无法获取到连接，将会返回null
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {

        //先对调用对象加锁
        synchronized (pool) {
            // 完全超时
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }

                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

}
