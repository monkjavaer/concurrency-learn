package com.base.client;

import com.base.server.CreateUnvXml;
import com.base.server.UnvProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2019/7/15 11:15
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 断线重连
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                System.err.println("服务端链接不上，开始重连操作...");
                try {
                    TestClient.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 3, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            writeAndFlushData(ctx);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }

    private void writeAndFlushData(ChannelHandlerContext ctx){
        UnvProtocol unvProtocol = new UnvProtocol();
        unvProtocol.setiCmdType(211);
        unvProtocol.setiVersion(2);
        unvProtocol.setStrXml(CreateUnvXml.buildBodyXml());
        ArrayList<byte[]> list = getImageFromURL("http://192.168.19.254:8888/group1/M00/00/00/wKgT_ly1irKAalgAAB0dWRQIHso793.jpg");
        unvProtocol.setiImageNum(1);
        unvProtocol.setoArrayList(list);
        ctx.writeAndFlush(unvProtocol);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public static ArrayList<byte[]> getImageFromURL(String urlPath) {
        ArrayList<byte[]> list = new ArrayList<>();
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else {
                data = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
        list.add(data);
        return list;
    }

    /**
     * 读取InputStream数据，转为byte[]数据类型
     *
     * @param is InputStream数据
     * @return 返回byte[]数据
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
