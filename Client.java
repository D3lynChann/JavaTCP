package TCPs;

import java.net.Socket;

public class Client extends Thread {
    // 服务器地址和端口地址
    public static final String IP = "localhost";
    public static final int PORT = 8000;

    public static void main(String[] args) {
        handle();
    }

    private static void handle() {
        try {
            // 实例化一个socket，并指定服务器地址和端口
            Socket client = new Socket(IP, PORT);
            // 两个线程，分别负责读写任务
            new Thread(new ReadHandlerThread(client)).start();
            new Thread(new WriteHandlerThread(client)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


