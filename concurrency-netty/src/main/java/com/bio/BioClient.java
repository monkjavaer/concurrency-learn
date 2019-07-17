package com.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author monkjavaer
 * @date 2019/7/17 15:06
 */
public class BioClient {
    public static final int PORT = 8888;
    public static final String IP = "127.0.0.1";

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            socket = new Socket(IP,PORT);
            socket.setSoTimeout(5000);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("client");
            printWriter.flush();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while (true){
                message = bufferedReader.readLine();
                if (message == null|| "".equals(message)){
                    break;
                }
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert printWriter != null;
                printWriter.close();
                assert bufferedReader != null;
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
