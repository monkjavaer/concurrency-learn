package com.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author monkjavaer
 * @date 2019/7/17 14:00
 */
public class BioServerHandler implements Runnable {
    private Socket socket;

    public BioServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("server");
            printWriter.flush();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String cientMessage;
            while (true){
                cientMessage = bufferedReader.readLine();
                if (cientMessage == null|| "".equals(cientMessage)){
                    break;
                }
                System.out.println(cientMessage);
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
